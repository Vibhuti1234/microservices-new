package com.vibhuti.inventoryservice.service;

import java.util.List;

import com.vibhuti.inventoryservice.dto.InventoryResponse;

public interface InventoryService {

	List<InventoryResponse> isInStock(List<String> skuCodes);

}
