package com.innoviti.quickemitab.service;

import java.util.List;

import com.innoviti.quickemitab.model.ModelResponse;

public interface ModelService {

	public List<ModelResponse> fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCode(Integer userId,Integer issuerBankCode,String manufactureCode,String categoryCode);
}
