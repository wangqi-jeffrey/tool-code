package com.neo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.dao.WarningItemDao;
import com.neo.entity.WarningItem;

@Service
public class WarningItemService {
	@Autowired
	private WarningItemDao warningItemDao;
	
	public WarningItem getWarningItemByName(String itemName) {
		return warningItemDao.getWarningItemByName(itemName);
	}
}
