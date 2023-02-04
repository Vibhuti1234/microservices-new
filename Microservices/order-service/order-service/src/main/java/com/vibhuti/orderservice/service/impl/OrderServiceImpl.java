package com.vibhuti.orderservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.vibhuti.orderservice.dto.InventoryResponse;
import com.vibhuti.orderservice.dto.OrderLineItemsDto;
import com.vibhuti.orderservice.dto.OrderRequest;
import com.vibhuti.orderservice.events.OrderPlacedEvent;
import com.vibhuti.orderservice.exception.OutOfStockException;
import com.vibhuti.orderservice.model.Order;
import com.vibhuti.orderservice.model.OrderLineItems;
import com.vibhuti.orderservice.repository.OrderRepository;
import com.vibhuti.orderservice.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private WebClient.Builder webClientBuilder;
	@Autowired
    private  KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


	@Override
	public String placeOrder(OrderRequest orderRequest) throws OutOfStockException {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsList().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).collect(Collectors.toList());
		order.setOrderLineItemsList(orderLineItemsList);
		List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode)
				.collect(Collectors.toList());
		InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();
		boolean isAllProductsIsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
		if (!isAllProductsIsInStock) {
			throw new OutOfStockException("Not all the ordered products are in stock!");
		}
		orderRepository.save(order);
		 kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
		return "Order placed successfully"; 

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		return modelMapper.map(orderLineItemsDto, OrderLineItems.class);
	}

}
