package com.innoviti.quickemitab.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.innoviti.quickemitab.exception.NotFoundException;
import com.innoviti.quickemitab.model.EmiTxnProcessRequest;
import com.innoviti.quickemitab.model.EmiTxnProcessResponse;
import com.innoviti.quickemitab.service.EmiTxnProcessService;

@RestController
@RequestMapping(value = "/emiTxnProcess")
public class EmiTxnProcessController {
	
	private static Logger logger = LoggerFactory.getLogger(EmiTxnProcessController.class);
	
	@Autowired
	private EmiTxnProcessService emiTxnProcessService;
	
	
	@PostMapping(value = "/saveEmiTxnProcessDetails")
	public ResponseEntity<EmiTxnProcessResponse> saveEmiTxnProcessDetails(@RequestPart("jsonEmiTxnProcessRequest")String jsonEmiTxnProcessRequest,@RequestPart("files") MultipartFile[] files) throws JsonParseException, JsonMappingException, IOException {
		logger.info("Entering :: EmiTxnProcessController :: saveEmiTxnProcessDetails method");
		EmiTxnProcessRequest emiTxnProcessRequest = new ObjectMapper().readValue(jsonEmiTxnProcessRequest, EmiTxnProcessRequest.class);
		if (files != null && files.length > 0) {
			try {
				for (MultipartFile file : files) {
					if (file.getOriginalFilename().contains("signature")) {
						emiTxnProcessRequest.setSignature(file.getBytes());
						logger.info(" saveEmiTxnProcessDetails method :: signature");
					}
					if (file.getOriginalFilename().contains("attachfile")) {
						emiTxnProcessRequest.setChargeSlip(file.getBytes());
						logger.info(" saveEmiTxnProcessDetails method :: attachFile");
					} else if (file.getOriginalFilename().contains("takesnap")) {
						emiTxnProcessRequest.setChargeSlip(file.getBytes());
						logger.info(" saveEmiTxnProcessDetails method :: takeSnap");
					}
				}
			} catch (Exception e) {
				throw new NotFoundException("failed to upload");
			}
		} else {
			throw new NotFoundException("Unable to upload. File is empty");
		}
		EmiTxnProcessResponse emiTxnProcessResponse = emiTxnProcessService.saveEmiTxnProcessDetails(emiTxnProcessRequest);
		logger.info("Exiting :: EmiTxnProcessController :: saveEmiTxnProcessDetails method");
		return new ResponseEntity<EmiTxnProcessResponse>(emiTxnProcessResponse, HttpStatus.OK);
	}

}
