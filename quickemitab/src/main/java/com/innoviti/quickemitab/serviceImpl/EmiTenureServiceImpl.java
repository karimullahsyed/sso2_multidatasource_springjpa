package com.innoviti.quickemitab.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.EmiTenureRequest;
import com.innoviti.quickemitab.model.EmiTenureResponse;
import com.innoviti.quickemitab.repository.config.EmiTenureRepository;
import com.innoviti.quickemitab.service.EmiTenureService;
import com.innoviti.quickemitab.utils.EmiCalculationUtils;

@Service
@Transactional("configManager")
public class EmiTenureServiceImpl implements EmiTenureService {

	private static Logger logger = LoggerFactory.getLogger(EmiTenureServiceImpl.class);
	
	@Autowired
	private EmiTenureRepository emiTenureRepository;
	

	@Override
	public List<EmiTenureResponse> fetchEmiTenureListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode(Integer userId,Integer issuerBankCode,String manufactureCode,String categoryCode,String modelCode,String amount) {
		logger.info("Entering :: EmiTenureServiceImpl :: fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode method");
		EmiTenureResponse emiTenureResponse = new EmiTenureResponse();
		
		EmiTenureRequest emiTenureRequest = null;
		List<EmiTenureResponse> emiTenureList =  new ArrayList<>();
		List<Object[]> objectList = emiTenureRepository.fetchEmiTenureListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode(userId,issuerBankCode,manufactureCode,categoryCode,modelCode);
		if(objectList.isEmpty()) {
			 throw new NotFoundException("emi tenure not found for : "+userId +" and "+issuerBankCode +" and "+manufactureCode +" and " +categoryCode +" and " +modelCode);
		}
		for (Object[] emiTenureData : objectList) {
			emiTenureRequest = new EmiTenureRequest();
			
			emiTenureRequest.setEmiTenureCode(String.valueOf(emiTenureData[0]));
			emiTenureRequest.setEmiTenureDisplayName(String.valueOf(emiTenureData[1]));
			emiTenureRequest.setEmiTenureMonth(String.valueOf(emiTenureData[2]));
			
			logger.info("Request ActualTxnAmount " +amount);
			
			double issuerMinEmiTxnAmount = Double.parseDouble(String.valueOf(emiTenureData[10]));
            double txnAmount = Double.parseDouble(amount)/100;
            if(!(txnAmount >= issuerMinEmiTxnAmount)){
                throw new NotFoundException("IssuerBanks Minimum Emi Amount validation failed ");
            }
			
			emiTenureRequest.setActualTxnAmount(Double.parseDouble(amount));
			emiTenureRequest.setIssuerBankCode(issuerBankCode);
			emiTenureRequest.setCashBack(String.valueOf(emiTenureData[3]));
			emiTenureRequest.setIssuerSchemeProcessingFees(String.valueOf(emiTenureData[4]));
			emiTenureRequest.setIssuerRateOfInterest(String.valueOf(emiTenureData[5]));
			emiTenureRequest.setAdvancedEmi(String.valueOf(emiTenureData[6]));
			emiTenureRequest.setIssuerDefaultCashbackFlag(String.valueOf(emiTenureData[7]));
			emiTenureRequest.setIssuerSchemeCode(String.valueOf(emiTenureData[8]));
			emiTenureRequest.setIssuerBankCode(Integer.valueOf(String.valueOf(emiTenureData[9])));
			
			EmiCalculationUtils.calculateEmiProcessingFee(emiTenureRequest, emiTenureResponse);
			EmiCalculationUtils.calculateEmiCashbackAmount(emiTenureRequest, emiTenureResponse);
			EmiCalculationUtils.calculateEmiAmountAndInterestAmount(emiTenureRequest, emiTenureResponse);
			EmiCalculationUtils.calculateLoanAmount(emiTenureRequest, emiTenureResponse);
			emiTenureResponse.setIssuerBankCode(emiTenureRequest.getIssuerBankCode());
			emiTenureResponse.setIssuerSchemeCode(emiTenureRequest.getIssuerSchemeCode());
			emiTenureList.add(emiTenureResponse);
		}
		logger.info("Exiting :: EmiTenureServiceImpl :: fetchModelListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode method");
		return emiTenureList;
	}
}
