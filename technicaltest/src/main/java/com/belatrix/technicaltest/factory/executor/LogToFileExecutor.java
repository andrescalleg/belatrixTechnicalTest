package com.belatrix.technicaltest.factory.executor;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("logToFile")
public class LogToFileExecutor implements CommandExecutor {

	private static final Logger logger = LoggerFactory.getLogger(LogToFileExecutor.class);
	private static final String FILE_NAME = "logFile.txt";
	private static final String LOG_FILE_PARAM = "logFileFolder";
	private static final String MESSAGE = " message ";

	@Override
	public void execute(String messageText, MessageType messageType, Map params) throws IOException {
		logger.info("Executing log to file");

		java.util.logging.Logger fileLogger = java.util.logging.Logger.getLogger("MyLog");
		messageText = messageType.name()+MESSAGE+ DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		String fileName = params.get(LOG_FILE_PARAM) + LocalDate.now().toString() + FILE_NAME;
		File logFile = new File(fileName);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		FileHandler fh = new FileHandler(fileName);

		fileLogger.addHandler(fh);
		fileLogger.log(Level.INFO, messageText);

		logger.info("Finish executing log file");
	}
}
