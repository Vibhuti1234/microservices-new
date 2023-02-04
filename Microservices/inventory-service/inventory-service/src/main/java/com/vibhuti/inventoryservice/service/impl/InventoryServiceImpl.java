package com.vibhuti.inventoryservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vibhuti.inventoryservice.dto.InventoryResponse;
import com.vibhuti.inventoryservice.repository.InventoryRepository;
import com.vibhuti.inventoryservice.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCodes) {
		/*log.info("Start the wait");
			Thread.sleep(10000);
		log.info("End the wait");*/
		return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
				.map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity()>0).build()).collect(Collectors.toList());
	}

}
