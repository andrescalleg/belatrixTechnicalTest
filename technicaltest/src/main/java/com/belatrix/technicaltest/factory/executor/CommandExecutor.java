package com.belatrix.technicaltest.factory.executor;

import com.belatrix.technicaltest.model.MessageType;

public interface CommandExecutor{
	void execute(String message,  MessageType messageType);
}