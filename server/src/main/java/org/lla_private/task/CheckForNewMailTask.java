package org.lla_private.task;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckForNewMailTask extends TimerTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckForNewMailTask.class);

	@Override
	public void run() {
		LOGGER.debug("Checking for new reporting mail");

	}

}
