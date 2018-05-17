package com.innoviti.quickemitab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innoviti.quickemitab.model.CategoryResponse;
import com.innoviti.quickemitab.service.CategoryService;

/**
 * @author Karim
 *
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/fetchCategoryList/{userId}/{issuerBankCode}/{manufactoreCode}")
	public ResponseEntity<List<CategoryResponse>> fetchCategoryList(@PathVariable Integer userId,@PathVariable Integer issuerBankCode,@PathVariable String manufactoreCode) {
		logger.info("Entering :: CategoryController :: fetchCategoryList method");
		List<CategoryResponse> categoryList = categoryService.fetchCategoryListBasedOnUserIdBankIdAndManufactureCode(userId,issuerBankCode,manufactoreCode);
		logger.info("Exiting :: CategoryController :: fetchCategoryList method");
		return new ResponseEntity<List<CategoryResponse>>(categoryList, HttpStatus.OK);
	}

}
