package com.innoviti.quickemitab.service;

import java.util.List;

import com.innoviti.quickemitab.model.ManufacturerResponse;

public interface ManufacturerService {

	public List<ManufacturerResponse> fetchManufacturerListBasedOnUserIdAndBankId(Integer issuerBankCode,Integer userId);
}
