package com.ticketnerd.orderservice.service;

import com.ticketnerd.orderservice.dto.GameResponse;
import com.ticketnerd.orderservice.dto.OrderLineItemsDto;
import com.ticketnerd.orderservice.dto.OrderRequest;
import com.ticketnerd.orderservice.model.Order;
import com.ticketnerd.orderservice.model.OrderLineItems;
import com.ticketnerd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> codes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getCode)
                .toList();

        // Call Game Service, and place order if product is in stock
        GameResponse[] gameResponses = webClient.get()
                        .uri("http://localhost:8082/api/game",
                                uriBuilder -> uriBuilder.queryParam("code", codes).build())
                        .retrieve()
                        .bodyToMono(GameResponse[].class)
                        .block();

        boolean allSeatsAvailable = Arrays.stream(gameResponses).allMatch(GameResponse::isAvailable);

        if (allSeatsAvailable) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("No seats are available for this game.");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setCode(orderLineItemsDto.getCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
