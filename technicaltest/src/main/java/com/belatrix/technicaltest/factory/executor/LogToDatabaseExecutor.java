package com.belatrix.technicaltest.factory.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("logToDatabase")
public class LogToDatabaseExecutor implements CommandExecutor {

	private static final Logger logger = LoggerFactory.getLogger(LogToDatabaseExecutor.class);

	@Override
	public void execute(String messageText, MessageType messageType, Map params) throws SQLException {
		logger.info("Executing log to database");

		Properties connectionProps = new Properties();
		connectionProps.put("user", params.get("userName"));
		connectionProps.put("password", params.get("password"));
		Connection connection = DriverManager.getConnection("jdbc:" + params.get("dbms") + "://" + params.get("serverName")
				+ ":" + params.get("portNumber") + "/", connectionProps);

		Statement stmt = connection.createStatement();
		messageText = messageType.name()+" message "+DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		stmt.executeUpdate("insert into Log_Values('" + messageText + "', " + messageType.getValue() + ")");

		logger.info("Finish executing log database");
	}
}
