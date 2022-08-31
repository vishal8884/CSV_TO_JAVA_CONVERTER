package io.vishal.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

import io.vishal.Model.Student;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CsvFileProcessor {

	public List<Student> readAndConvertCsvFile(MultipartFile file) throws IOException {
		
		log.info("readAndConvertCsvFile called");
		
		List<Student> studentList = new ArrayList<>();
		FileReader fileReader;

		try {

			File convFile = new File(file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			
			fos.write(file.getBytes());
			fos.close();
			
			fileReader = new FileReader(convFile);
			
			
			studentList = new CsvToBeanBuilder<Student>(fileReader).withType(Student.class).build().parse();
			log.debug("student list :: {}",studentList);
			
			return studentList;
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}

	}
}
