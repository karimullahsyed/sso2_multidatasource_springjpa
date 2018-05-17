package com.innoviti.quickemitab.service;

import java.util.List;

import com.innoviti.quickemitab.model.EmiTenureResponse;

public interface EmiTenureService {

	public List<EmiTenureResponse> fetchEmiTenureListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode(Integer userId,Integer issuerBankCode,String manufactureCode,String categoryCode,String modelCode,String amount);
}
