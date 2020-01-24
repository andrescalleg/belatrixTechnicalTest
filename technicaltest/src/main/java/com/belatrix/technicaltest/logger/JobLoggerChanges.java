package com.belatrix.technicaltest.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.belatrix.technicaltest.factory.LoggerBelatrixFactory;
import com.belatrix.technicaltest.factory.executor.CommandExecutor;
import com.belatrix.technicaltest.model.LoggerAction;
import com.belatrix.technicaltest.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLoggerChanges {

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	private static boolean logToDatabase;
	private static Map dbParams;
	private static Logger logger;

	@Autowired
	private LoggerBelatrixFactory loggerFactory;

	JobLoggerChanges() {
		logger = Logger.getLogger("MyLog");
		logError = true;
		logMessage = true;
		logWarning = true;
		logToDatabase = true;
		logToFile = true;
		logToConsole = true;
		Map dbParamsMap = new HashMap<String,String>();
		dbParamsMap.put("userName", "userName");
		dbParamsMap.put("password", "password");
		dbParamsMap.put("logFileFolder", "/");
		dbParams = dbParamsMap;
	}

	public void logMessage(String messageText, MessageType messageType, LoggerAction action) throws Exception {

		Optional<CommandExecutor> executor = loggerFactory.getValidateExecutor(action);
		executor.get().execute(messageText,messageType);

		messageText.trim();
		if (messageText == null || messageText.length() == 0) {
			return;
		}
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		/*if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}*/

		/*Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName"));
		connectionProps.put("password", dbParams.get("password"));

		connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
				+ ":" + dbParams.get("portNumber") + "/", connectionProps);*/

		int t = 0;
		if (MessageType.MESSAGE.equals(messageType)) {
			t = 1;
		}

		if (MessageType.ERROR.equals(messageType)) {
			t = 2;
		}

		if (MessageType.WARNING.equals(messageType)) {
			t = 3;
		}

		//Statement stmt = connection.createStatement();

		String l = null;
		File logFile = new File(dbParams.get("logFileFolder") + "logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}

		FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "logFile.txt");
		ConsoleHandler ch = new ConsoleHandler();

		if ((MessageType.ERROR.equals(messageType)) && logError) {
			l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if ((MessageType.WARNING.equals(messageType)) && logWarning) {
			l = l + "warning " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if ((MessageType.MESSAGE.equals(messageType)) && logMessage) {
			l = l + "message " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if(logToFile) {
			logger.addHandler(fh);
			logger.log(Level.INFO, messageText);
		}

		if(logToConsole) {
			logger.addHandler(ch);
			logger.log(Level.INFO, messageText);
		}

		/*if(logToDatabase) {
			stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
		}*/
	}
}
