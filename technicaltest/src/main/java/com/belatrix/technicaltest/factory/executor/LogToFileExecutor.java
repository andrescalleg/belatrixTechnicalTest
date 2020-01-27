package com.belatrix.technicaltest.factory.executor;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import com.belatrix.technicaltest.exception.FileBelatrixException;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("logToFile")
public class LogToFileExecutor implements CommandExecutor {

	@Value("${file.fileName}")
	private String fileCreationName;

	@Value("${file.logFileFolder}")
	private String logFileFolder;


	private static final Logger logger = LoggerFactory.getLogger(LogToFileExecutor.class);
	private static final String MESSAGE = " message ";
	private static final String ERROR = "4";

	@Override
	public String execute(String messageText, MessageType messageType) throws IOException {
		logger.info("Executing log to file");

		try{
			validateValues();

			java.util.logging.Logger fileLogger = java.util.logging.Logger.getLogger("MyLog");
			messageText = buildMessage(messageType, messageText);
			FileHandler fh =createFileHandler();
			writeFile(fileLogger, fh, messageText);
		}catch (Exception e){
			logger.error("Error executing file of message [{}]{}", messageType.name(),e);
			return ERROR;
		}


		logger.info("Finish executing log file for message [{}]", messageType.name());

		return  messageType.getValue();
	}

	private void validateValues() throws FileBelatrixException {
		if(fileCreationName == null || logFileFolder == null){
			throw new FileBelatrixException();
		}
	}

	private void writeFile(java.util.logging.Logger fileLogger, FileHandler fh, String messageText) {
		fileLogger.addHandler(fh);
		fileLogger.log(Level.INFO, messageText);
	}

	private FileHandler createFileHandler() throws IOException {
		String fileName = logFileFolder+ LocalDate.now().toString() + fileCreationName;
		File logFile = new File(fileName);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		return new FileHandler(fileName);
	}

	private String buildMessage(MessageType messageType, String messageText) {
		return messageType.name() +
				MESSAGE +
				DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) +
				messageText;
	}
}
