package com.innoviti.quickemitab.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.EmiTxnProcessRequest;
import com.innoviti.quickemitab.model.EmiTxnProcessResponse;
import com.innoviti.quickemitab.model.ErrorCode;
import com.innoviti.quickemitab.repository.txn.EmiTxnProcess;
import com.innoviti.quickemitab.repository.txn.EmiTxnProcessRepository;
import com.innoviti.quickemitab.service.EmiTxnProcessService;
import com.innoviti.quickemitab.utils.CommonUtil;

@Service
@Transactional("transactionManager")
public class EmiTxnProcessServiceImpl implements EmiTxnProcessService {
	
	private static Logger logger = LoggerFactory.getLogger(EmiTxnProcessServiceImpl.class);
	
	@Autowired
	private EmiTxnProcessRepository emiTxnProcessRepository;

	@Override
	public EmiTxnProcessResponse saveEmiTxnProcessDetails(EmiTxnProcessRequest emiTxnProcessRequest) {
		logger.info("Entering :: EmiTxnProcessServiceImpl :: saveEmiTxnProcessDetails method");
		EmiTxnProcessResponse emiTxnProcessResponse = null;
		EmiTxnProcess emiTxnProcess = CommonUtil.copyBeanProperties(emiTxnProcessRequest, EmiTxnProcess.class);
		emiTxnProcess.setEmiDetails(CommonUtil.convertObjectToJsonStr(emiTxnProcessRequest.getEmiDetails()));
		emiTxnProcess = emiTxnProcessRepository.save(emiTxnProcess);
		if(emiTxnProcess == null) {
			 throw new NotFoundException("not able to save the emi txn details ");
		}
		emiTxnProcessResponse = new EmiTxnProcessResponse();
		emiTxnProcessResponse.setResponseCode(ErrorCode.ERROR_CODE_00);
		emiTxnProcessResponse.setResponseMessage(ErrorCode.getInstance().getMessage(ErrorCode.ERROR_CODE_00));
		logger.info("Exiting  :: EmiTxnProcessServiceImpl :: saveEmiTxnProcessDetails method");
		return emiTxnProcessResponse;
	}


}
