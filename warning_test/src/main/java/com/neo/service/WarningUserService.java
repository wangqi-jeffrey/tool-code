package com.neo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.dao.WarningUserDao;
import com.neo.entity.WarningUser;

@Service
public class WarningUserService {

	@Autowired
	private WarningUserDao warningUserDao;
	
	public List<WarningUser> getWarningUserByName(Integer itemId,
			List<String> groupNames) {
		return warningUserDao.getWarningUserByName(itemId, groupNames);
	}

}
