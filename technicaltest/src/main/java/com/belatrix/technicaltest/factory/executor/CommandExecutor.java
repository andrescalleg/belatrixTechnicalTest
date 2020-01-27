package com.belatrix.technicaltest.factory.executor;

import java.io.IOException;
import java.sql.SQLException;
import com.belatrix.technicaltest.model.MessageType;

public interface CommandExecutor{
	String execute(String message, MessageType messageType) throws SQLException, IOException;
}