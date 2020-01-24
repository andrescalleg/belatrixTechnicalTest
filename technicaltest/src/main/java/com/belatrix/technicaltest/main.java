package com.belatrix.technicaltest;

import java.util.HashMap;
import java.util.Map;
import com.belatrix.technicaltest.logger.JobLogger;

public class main {

	public static void main (String [ ] args) throws Exception {
		Map dbParams = new HashMap<String,String>();
		dbParams.put("userName", "userName");
		dbParams.put("password", "password");
		dbParams.put("logFileFolder", "/");
		JobLogger jobLog = new JobLogger(true,true,true,
				true,true,true, dbParams);

		jobLog.logMessage("texto de prueba",true,false,false);
	}
}
