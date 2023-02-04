package com.vibhuti.orderservice.service;

import com.vibhuti.orderservice.dto.OrderRequest;
import com.vibhuti.orderservice.exception.OutOfStockException;

public interface OrderService {

  String placeOrder(OrderRequest orderRequest) throws OutOfStockException;

}
