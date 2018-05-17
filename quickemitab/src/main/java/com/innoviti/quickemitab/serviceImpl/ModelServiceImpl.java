package com.innoviti.quickemitab.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.ModelResponse;
import com.innoviti.quickemitab.repository.config.ModelRepository;
import com.innoviti.quickemitab.service.ModelService;

@Service
@Transactional("configManager")
public class ModelServiceImpl implements ModelService {

	private static Logger logger = LoggerFactory.getLogger(ModelServiceImpl.class);
	
	@Autowired
	private ModelRepository modelRepository;
	

	@Override
	public List<ModelResponse> fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCode(Integer userId,Integer issuerBankCode, String manufactureCode,String categoryCode) {
		logger.info("Entering :: ModelServiceImpl :: fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCode method");
		ModelResponse modelResponse = null;
		List<ModelResponse> categoryList =  new ArrayList<>();
		List<Object[]> objectList = modelRepository.fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCode(userId,issuerBankCode,manufactureCode,categoryCode);
		if(objectList.isEmpty()) {
			 throw new NotFoundException("model code not found for : "+userId +" and "+issuerBankCode +" and "+manufactureCode +" and " +categoryCode);
		}
		for (Object[] modelData : objectList) {
			modelResponse = new ModelResponse();
			modelResponse.setModelCode(String.valueOf(modelData[0]));
			modelResponse.setModelDisplayName(String.valueOf(modelData[1]));
			categoryList.add(modelResponse);
		}
		logger.info("Exiting :: ModelServiceImpl :: fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCode method");
		return categoryList;
	}

}
