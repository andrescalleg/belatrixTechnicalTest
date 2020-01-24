package com.belatrix.technicaltest.factory.executor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import com.belatrix.technicaltest.model.MessageType;

public interface CommandExecutor{
	void execute(String message, MessageType messageType, Map dbParams) throws SQLException, IOException;
}