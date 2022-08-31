package io.vishal.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.vishal.Model.Student;
import io.vishal.service.CsvFileProcessor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CsvController {
	
	@Autowired
	private CsvFileProcessor csvFileProcessor;

	@PostMapping("/uploadCsv")
	public ResponseEntity<List<Student>> uploadCsv(@RequestParam MultipartFile file){
		log.info("upload csv() called");
		
		List<Student> studentList;
		
		try {
     	  studentList = csvFileProcessor.readAndConvertCsvFile(file);
		
		} catch (IOException e) {
			log.error("Ioexception occured :: {}",ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error("Exception occured :: {}",ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(studentList);
	}
}
