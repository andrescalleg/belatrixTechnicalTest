package com.belatrix.technicaltest.factory.executor;

import com.belatrix.technicaltest.model.MessageType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class LoggerFileTest {

	@Spy
	private final  LogToFileExecutor logToFileExecutor = new LogToFileExecutor();

	private LogToFileExecutor logToFileExceptionExecutor;

	private static final String ERROR = "4";

	@Before
	public void setup() {
		logToFileExceptionExecutor = new LogToFileExecutor();
		ReflectionTestUtils.setField(logToFileExecutor, "fileCreationName","fileCreationName");
		ReflectionTestUtils.setField(logToFileExecutor, "logFileFolder","logFileFolder");
	}

	@Test
	public void callLoggerFileInfo() throws Exception {
		String message = logToFileExecutor.execute("message", MessageType.MESSAGE);
		Assert.assertEquals(message,  MessageType.MESSAGE.getValue());
	}

	@Test
	public void callLoggerFileError() throws Exception {
		String message = logToFileExecutor.execute("message", MessageType.ERROR);
		Assert.assertEquals(message,  MessageType.ERROR. getValue());
	}

	@Test
	public void callLoggerFileWarn() throws Exception {
		String message = logToFileExecutor.execute("message", MessageType.WARNING);
		Assert.assertEquals(message,  MessageType.WARNING.getValue());
	}

	@Test
	public void callLoggerFileException() throws Exception {
		String message = logToFileExceptionExecutor.execute("message", MessageType.WARNING);
		Assert.assertEquals(message, ERROR);
	}

}
