package com.juan.ahumada.techassessment.dtos;

import com.opencsv.bean.CsvDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	@CsvDate("yyyy-MM-dd")
	private Date dateCreated;
	@CsvDate("yyyy-MM-dd")
	private Date dateHired;
	private Double salary;
}
