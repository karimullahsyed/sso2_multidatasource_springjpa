package com.innoviti.quickemitab.service;

import com.innoviti.quickemitab.model.EmiTxnProcessRequest;
import com.innoviti.quickemitab.model.EmiTxnProcessResponse;

public interface EmiTxnProcessService {
	
	public EmiTxnProcessResponse saveEmiTxnProcessDetails(EmiTxnProcessRequest emiTxnProcessRequest);

}
