package io.vishal.Model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
	
	@CsvBindByName
	String id;
	
	@CsvBindByName
	String firstName;
	
	@CsvBindByName
	String lastName;

}
