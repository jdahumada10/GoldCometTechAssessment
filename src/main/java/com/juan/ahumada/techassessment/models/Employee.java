package com.juan.ahumada.techassessment.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Setter;
import lombok.Getter;

@Entity
@Setter
@Getter
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "date_created")
	private Date dateCreated;
	@Column(name = "date_hired")
	private Date dateHired;
	@Column(name = "salary")
	private Double salary;
}
