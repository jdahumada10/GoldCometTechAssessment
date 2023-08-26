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
	@CsvDate("dd/MM/yy")
	private Date dateCreated;
	@CsvDate("dd/MM/yy")
	private Date dateHired;
	private Double salary;
}
