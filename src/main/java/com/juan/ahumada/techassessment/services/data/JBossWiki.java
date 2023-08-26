package com.juan.ahumada.techassessment.services.data;

public enum JBossWiki {
	QUESTION_1("What is the folder structure in a typical standalone deployment of JBOSS starting with the root folder of jboss-eap-7.2?",
			"jboss-eap-7.2/\n" +
					"├── bin/\n" +
					"├── docs/\n" +
					"├── domain/\n" +
					"├── modules/\n" +
					"├── standalone/\n" +
					"├── welcome-content/\n" +
					"└── standalone.xml\n" +
					"\n" +
					"•'bin/': Contains various scripts and executables used to start, stop, and manage the JBoss EAP server.\n" +
					"•'docs/': Documentation related to JBoss EAP.\n" +
					"•'domain/': This folder is used for domain mode configuration, where multiple server instances can be managed collectively. It contains configuration files for domain controller and host controller.\n" +
					"•'modules/': Contains module definitions used by the server, including libraries, modules, and their configurations.\n" +
					"•'standalone/': This is where the configuration and data for the standalone mode reside. It includes subdirectories such as configuration, data, deployments, and others.\n" +
					"•'welcome-content/': Contains default web content that is displayed when you access the server's root URL.\n" +
					"•'standalone.xml': The main configuration file for the standalone mode. It defines various settings for the server's behavior, datasources, security, and more."),

	QUESTION_2("What are the deployment configuration file names and location of a typical standalone deployment of JBOSS.",
			"In a typical standalone deployment of JBoss EAP (Enterprise Application Platform) 7.2, the deployment configuration file names and locations are as follows:\n" +
					"\n" +
					"1. Standalone Configuration: jboss-eap-7.2/standalone/configuration/standalone.xml\n" +
					"The main configuration file for standalone deployments is named 'standalone.xml'. This file is located within the 'standalone/configuration' directory of your JBoss EAP installation. It defines the configuration settings for your standalone server instance.\n" +
					"\n" +
					"2. Deployment Configuration: your-deployment.war/META-INF/jboss-deployment-structure.xml\n" +
					"When you deploy applications to JBoss EAP, you might have specific configuration files associated with those deployments. These files are typically named 'jboss-deployment-structure.xml' and are placed in the 'META-INF' directory of your deployment archive (e.g., WAR, EAR, JAR files).\n" +
					"The 'jboss-deployment-structure.xml' file allows you to configure class loading, dependencies, module dependencies, and other deployment-specific settings.\n" +
					"\n" +
					"3. Datasource Configuration: jboss-eap-7.2/standalone/deployments/your-datasource-config.ds.xml\n" +
					"Datasource configurations can be included in the 'standalone.xml' file or in separate XML files. If you choose to use separate files, they should be placed in the 'standalone/deployments' directory and end with '.ds.xml'.\n" +
					"In this file, you can configure datasources using the '<datasources>' subsystem.\n" +
					"\n" +
					"4. Other Configuration:\n" +
					"Depending on your application and its requirements, you might also have other configuration files such as 'jboss-web.xml' for web applications, 'ejb-jar.xml' for EJB modules, and more. These files are usually placed in the appropriate locations within your deployment archives.\n" +
					"\n" +
					"Note:\n" +
					"Please note that the above paths are based on the standard conventions in JBoss EAP 7.2. Your actual paths may vary depending on your environment and any customizations you've made. Always refer to the official JBoss EAP documentation or consult your system administrator for accurate information regarding your deployment configuration."),

	QUESTION_3("What file names and locations are used to monitor JBOSS activity of a typical standalone deployment of JBOSS.",
			"In JBoss EAP 7.2, for monitoring and logging activity, you can configure the logging subsystem to generate log files that capture various events and activities within the server. The main configuration file for logging is standalone.xml, and you can set up different log handlers, formatters, and log levels according to your needs. Here's a general overview of the log-related configuration:\n" +
					"\n" +
					"1. Log Configuration in standalone.xml:\n" +
					"The main logging configuration is typically done in the 'standalone.xml' configuration file. You can find it in the 'standalone/configuration' directory of your JBoss EAP installation.\n" +
					"2. Log Handlers and Formatters:\n" +
					"Inside the 'standalone.xml' file, you can configure various log handlers and formatters. A log handler defines where the log messages are written (e.g., to a file, to the console, to a remote server), and a formatter defines how the log messages are formatted.\n" +
					"• Common log handlers include:\n" +
					"• 'FILE': Writes log messages to a file.\n" +
					"• 'CONSOLE': Prints log messages to the console.\n" +
					"• 'ASYNC_FILE': Writes log messages to a file asynchronously.\n" +
					"3. Log File Locations:\n" +
					"By default, log files are written to the 'standalone/log' directory within your JBoss EAP installation. The log filenames are often based on the handler name or specific attributes you configure.\n" +
					"For example, if you configure a 'FILE' log handler named 'server-log', the corresponding log file might be:\n" +
					"'jboss-eap-7.2/standalone/log/server-log.log'\n" +
					"4. Log Levels:\n" +
					"Log levels determine the severity of the messages that are logged. Common log levels include 'DEBUG', 'INFO', 'WARN', 'ERROR', and 'FATAL'. You can configure log levels for specific categories or packages in the 'standalone.xml' file.\n" +
					"5. Access Log:\n" +
					"For monitoring access to web applications, you can configure an access log. This logs information about incoming HTTP requests and responses.\n" +
					"6. Audit Log:\n" +
					"If you need to log security-related events, you can configure an audit log. This logs activities related to authentication, authorization, and security-related operations.\n" +
					"\n" +
					"Note:\n" +
					"Please note that the configuration details can be quite extensive and are specific to your application and monitoring needs. It's recommended to refer to the official JBoss EAP 7.2 documentation for detailed information on configuring logging and monitoring."),

	QUESTION_4("Describe the steps you would use to deploy an application (.war file) to a typical standalone deployment of JBOSS.",
			"Deploying a web application (WAR file) to JBoss EAP 7.2 involves a few steps. Here's a general outline of the process:\n" +
					"\n" +
					"1. Prepare Your Application:\n" +
					"   Ensure your web application is packaged as a standard Java WAR (Web Archive) file. This should include all necessary components like HTML, JSP, servlets, Java classes, configuration files, and any required libraries.\n" +
					"\n" +
					"2. Access the JBoss Management Console:\n" +
					"   The JBoss Management Console is a web-based interface for managing and configuring the JBoss server. Open a web browser and navigate to 'http://localhost:9990/console' (assuming the server is running locally and on the default port).\n" +
					"\n" +
					"3. Log In to the Management Console:\n" +
					"   Log in using the management user credentials. These credentials are set during the installation of JBoss EAP.\n" +
					"\n" +
					"4. Navigate to \"Deployments\" Section:\n" +
					"   In the Management Console, find and click on the \"Deployments\" section. This is where you can manage the deployment of your application.\n" +
					"\n" +
					"5. Upload and Deploy Your WAR:\n" +
					"   In the \"Deployments\" section, you'll find an option to \"Add Content.\" Click on it and upload your WAR file from your local machine.\n" +
					"\n" +
					"6. Choose Runtime Name and Enable Deployment:\n" +
					"   After uploading, you'll see your WAR listed. Choose a runtime name for your application (this will be part of the URL) and check the \"Enabled\" checkbox. This will enable automatic deployment of your application.\n" +
					"\n" +
					"7. Save Changes:\n" +
					"   Click the \"Save\" button to confirm the deployment settings.\n" +
					"\n" +
					"8. Start the Deployment:\n" +
					"   Back on the \"Deployments\" page, click the \"Start\" button next to your application. This will initiate the deployment process.\n" +
					"\n" +
					"9. Monitor Deployment Progress:\n" +
					"   The deployment process might take a moment. You can monitor the progress in the console. Once the deployment is successful, you should see a message indicating that the deployment was enabled and started.\n" +
					"\n" +
					"10. Access Your Application:\n" +
					"   Your application should now be accessible at the URL formed by combining the server address, port, and runtime name you configured. For example, if your runtime name is 'myapp', and your server is running at 'http://localhost:8080', you can access your application at 'http://localhost:8080/myapp'.\n" +
					"\n" +
					"Note:\n" +
					"Please remember that this is a general guide, and there might be variations based on your specific setup or environment. Always refer to the official JBoss EAP documentation for accurate and up-to-date instructions on deploying applications.");

	private final String question;
	private final String answer;

	JBossWiki(String question,
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
