package com.innoviti.quickemitab.service;

import java.util.List;

import com.innoviti.quickemitab.model.IssuerBankResponse;
import com.innoviti.quickemitab.model.IssuerBinResponse;

public interface IssuerBankService {

	public List<IssuerBankResponse> findByuserId(Integer userId);
	
	public IssuerBinResponse findByIssuerBin(Integer issuerBinNo,Integer issuerBankCode);
}
