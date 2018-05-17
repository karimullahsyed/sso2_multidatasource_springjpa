package com.innoviti.quickemitab.service;

import java.util.List;

import com.innoviti.quickemitab.model.CategoryResponse;

public interface CategoryService {

	public List<CategoryResponse> fetchCategoryListBasedOnUserIdBankIdAndManufactureCode(Integer userId,Integer issuerBankCode,String merchantCode);
}
