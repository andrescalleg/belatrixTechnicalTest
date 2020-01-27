package com.belatrix.technicaltest.factory.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("logToDatabase")
public class LogToDatabaseExecutor implements CommandExecutor {

	@Value("${database.url}")
	private String url;

	@Value("${database.username}")
	private String username;

	@Value("${database.password}")
	private String password;

	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String MESSAGE = " message ";
	private static final String ERROR = "4";


	private static final Logger logger = LoggerFactory.getLogger(LogToDatabaseExecutor.class);

	@Override
	public String execute(String messageText, MessageType messageType) throws SQLException {
		try{
			logger.info("Executing log to database");

			Statement stmt = createStatement();
			messageText = buildMessage(messageType, messageText);
			stmt.executeUpdate("insert into Log_Values('" + messageText + "', " + messageType.getValue() + ")");

			logger.info("Finish executing log database for message [{}]",  messageType.name());
			return messageType.getValue();
		}catch (Exception e){
			logger.error("Error executing database of message [{}]{}", messageType.name(),e);
			return ERROR;
		}


	}

	private String buildMessage(MessageType messageType, String messageText) {
		return messageType.name() +
				MESSAGE +
				DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) +
				messageText;
	}

	private Statement createStatement() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put(USER,username);
		connectionProps.put(PASSWORD, password);
		Connection connection = DriverManager.getConnection(url , connectionProps);
		return connection.createStatement();
	}

}
