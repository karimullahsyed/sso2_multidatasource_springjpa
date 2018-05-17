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

import com.innoviti.quickemitab.model.IssuerBankResponse;
import com.innoviti.quickemitab.model.IssuerBinResponse;
import com.innoviti.quickemitab.service.IssuerBankService;

/**
 * @author Karim
 *
 */
@RestController
@RequestMapping(value = "/issuerBank")
public class IssuerBankController {

	private static Logger logger = LoggerFactory.getLogger(IssuerBankController.class);

	@Autowired
	private IssuerBankService issuerBankService;

	@GetMapping(value = "/fetchBankList/{userId}")
	public ResponseEntity<List<IssuerBankResponse>> fetchBankList(@PathVariable Integer userId) {
		logger.info("Entering :: IssuerBankController :: fetchBankList method");
		List<IssuerBankResponse> issuerBankList = issuerBankService.findByuserId(userId);
		logger.info("Exiting :: IssuerBankController :: fetchBankList method");
		return new ResponseEntity<List<IssuerBankResponse>>(issuerBankList, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/fetchBin/{issuerBinNo}/{issuerBankCode}")
	public ResponseEntity<IssuerBinResponse> fetchBin(@PathVariable Integer issuerBinNo,@PathVariable Integer issuerBankCode) {
		logger.info("Entering :: IssuerBankController :: fetchBin method");
		IssuerBinResponse issuerBin = issuerBankService.findByIssuerBin(issuerBinNo,issuerBankCode);
		logger.info("Exiting :: IssuerBankController :: fetchBin method");
		return new ResponseEntity<IssuerBinResponse>(issuerBin, HttpStatus.OK);
	}

}
