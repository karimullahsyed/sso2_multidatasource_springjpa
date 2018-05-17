package com.innoviti.quickemitab.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.CategoryResponse;
import com.innoviti.quickemitab.repository.config.CategoryRepository;
import com.innoviti.quickemitab.service.CategoryService;

@Service
@Transactional("configManager")
public class CategoryServiceImpl implements CategoryService {

	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public List<CategoryResponse> fetchCategoryListBasedOnUserIdBankIdAndManufactureCode(Integer userId,Integer issuerBankCode, String manufactureCode) {
		logger.info("Entering :: CategoryServiceImpl :: fetchCategoryListBasedOnUserIdBankIdAndManufactureCode method");
		CategoryResponse categoryResponse = null;
		List<CategoryResponse> categoryList =  new ArrayList<>();
		List<Object[]> objectList = categoryRepository.fetchCategoryListBasedOnUserIdBankIdAndManufactorerCode(userId,issuerBankCode,manufactureCode);
		if(objectList.isEmpty()) {
			 throw new NotFoundException("Category code not found for : "+userId +" and "+issuerBankCode +" and "+manufactureCode);
		}
		for (Object[] categoryData : objectList) {
			categoryResponse = new CategoryResponse();
			categoryResponse.setCategoryCode(String.valueOf(categoryData[0]));
			categoryResponse.setCategoryDisplayName(String.valueOf(categoryData[1]));
			categoryList.add(categoryResponse);
		}
		logger.info("Exiting :: CategoryServiceImpl :: fetchCategoryListBasedOnUserIdBankIdAndManufactureCode method");
		return categoryList;
	}

}
