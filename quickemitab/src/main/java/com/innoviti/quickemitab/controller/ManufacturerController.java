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

import com.innoviti.quickemitab.model.ManufacturerResponse;
import com.innoviti.quickemitab.service.ManufacturerService;

/**
 * @author Karim
 *
 */
@RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController {

	private static Logger logger = LoggerFactory.getLogger(ManufacturerController.class);
	
	@Autowired
	private ManufacturerService manufacturerService;

	@GetMapping(value = "/fetchManufacturerList/{userId}/{issuerBankCode}")
	public ResponseEntity<List<ManufacturerResponse>> fetchManufacturerList(@PathVariable Integer userId,@PathVariable Integer issuerBankCode) {
		logger.info("Entering :: ManufacturerController :: fetchManufacturerList method");
		List<ManufacturerResponse> manufacturerRequestList = manufacturerService.fetchManufacturerListBasedOnUserIdAndBankId(userId,issuerBankCode);
		logger.info("Exiting :: ManufacturerController :: fetchManufacturerList method");
		return new ResponseEntity<List<ManufacturerResponse>>(manufacturerRequestList, HttpStatus.OK);
	}

}
