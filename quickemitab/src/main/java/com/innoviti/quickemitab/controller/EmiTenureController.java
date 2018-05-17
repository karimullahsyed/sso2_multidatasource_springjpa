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

import com.innoviti.quickemitab.model.EmiTenureResponse;
import com.innoviti.quickemitab.service.EmiTenureService;

/**
 * @author Karim
 *
 */
@RestController
@RequestMapping(value = "/emiTenure")
public class EmiTenureController {

	private static Logger logger = LoggerFactory.getLogger(EmiTenureController.class);
	
	@Autowired
	private EmiTenureService emiTenureService;

	@GetMapping(value = "/fetchEmiTenureList/{userId}/{issuerBankCode}/{manufactoreCode}/{categoryCode}/{modelCode}/{amount}")
	public ResponseEntity<List<EmiTenureResponse>> fetchEmiTenureList(@PathVariable Integer userId,@PathVariable Integer issuerBankCode,@PathVariable String manufactoreCode,
																	 @PathVariable String categoryCode,@PathVariable String modelCode,@PathVariable String amount) {
		logger.info("Entering :: EmiTenureController :: fetchEmiTenureList method");
		List<EmiTenureResponse> emiTenureList = emiTenureService.fetchEmiTenureListBasedOnUserIdBankIdManufactorerCodeCategoryCodeAndModelCode(userId,issuerBankCode,manufactoreCode,categoryCode,modelCode,amount);
		logger.info("Exiting :: EmiTenureController :: fetchEmiTenureList method");
		return new ResponseEntity<List<EmiTenureResponse>>(emiTenureList, HttpStatus.OK);
	}

}
