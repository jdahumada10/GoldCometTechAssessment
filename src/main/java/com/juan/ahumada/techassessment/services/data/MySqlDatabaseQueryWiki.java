package com.juan.ahumada.techassessment.services.data;

public enum MySqlDatabaseQueryWiki {
	QUERY_1("Write a query to fetch first_name from Employees and use an alias of First Name in the response.",
			"SELECT first_name AS 'First Name' FROM employee;"),
	QUERY_2("Write a query to return current date.",
			"SELECT CURDATE();"),
	QUERY_3("Write a query to insert an employee and contact with contact_relationship_type = 2.",
			"INSERT INTO contact_relationship_type (id, description, date_created)\n" +
					"VALUES (2, 'Description for Relationship Type 2', NOW())\n" +
					"ON DUPLICATE KEY UPDATE id = id;\n" +
					"\n" +
					"INSERT INTO employee (date_created, date_hired, first_name, last_name, salary)\n" +
					"VALUES (NOW(), NOW(), 'Juan', 'Ahumada', 100000);\n" +
					"\n" +
					"SET @employee_id = LAST_INSERT_ID();\n" +
					"\n" +
					"INSERT INTO contact_Info (phone_number, email, date_created, employee_id, relationship_type)\n" +
					"VALUES ('123-456-7890', 'juan.ahumada@test.com', NOW(), @employee_id, 2);\n"),
	QUERY_4("Write a query to delete employee where id = 5",
			"DELETE FROM employee WHERE id = 5;"),
	QUERY_5("Write a query to update contact_relationship_type.description to “Significant Other“ " +
			"where description = “Spouse”.",
			"UPDATE contact_relationship_type SET description = 'Significant Other' WHERE description = 'Spouse';");

	private final String question;
	private final String answer;

	MySqlDatabaseQueryWiki(String question,
	                       String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
}

