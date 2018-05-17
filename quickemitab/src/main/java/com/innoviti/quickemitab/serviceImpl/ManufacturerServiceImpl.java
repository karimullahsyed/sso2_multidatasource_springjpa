package com.innoviti.quickemitab.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.ManufacturerResponse;
import com.innoviti.quickemitab.repository.config.ManufacturerRepository;
import com.innoviti.quickemitab.service.ManufacturerService;

@Service
@Transactional("configManager")
public class ManufacturerServiceImpl implements ManufacturerService {

	private static Logger logger = LoggerFactory.getLogger(ManufacturerServiceImpl.class);
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Override
	public List<ManufacturerResponse> fetchManufacturerListBasedOnUserIdAndBankId(Integer userId,Integer issuerBankCode) {
		logger.info("Entering :: ManufacturerServiceImpl :: fetchManufacturerListBasedOnUserIdAndBankId method");
		ManufacturerResponse manufacturerResponse = null;
		List<ManufacturerResponse> manufacturerList =  new ArrayList<>();
		List<Object[]> objectList = manufacturerRepository.fetchManufacturerBasedOnUserIdAndBankId(userId,issuerBankCode);
		if(objectList.isEmpty()) {
			 throw new NotFoundException("Manufacturer code not found for : "+ userId );
		}
		for (Object[] manufacturerData : objectList) {
			manufacturerResponse = new ManufacturerResponse();
		    manufacturerResponse.setManufacturerCode(String.valueOf(manufacturerData[0]));
		    manufacturerResponse.setManufacturerDisplayName(String.valueOf(manufacturerData[1]));
		    manufacturerList.add(manufacturerResponse);
		}
		logger.info("Exiting :: ManufacturerServiceImpl :: fetchManufacturerListBasedOnUserIdAndBankId method");
		return manufacturerList;
	}

}
