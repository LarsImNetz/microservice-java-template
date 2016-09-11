package org.lla_private;

import java.util.HashSet;
import java.util.Set;
// import java.util.Timer;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {

	// Alle 15 Minuten wird das Postfach auf neue Mails geprueft
//	private static final long CHECK_FOR_NEW_REPORTING_INTERVAL = 15 * 60 * 1000;

	public RestApplication() {
//		startCheckForNewMailTask();
	}

//	private void startCheckForNewMailTask() {
//		final Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new org.lla_private.task.CheckForNewMailTask(), 0, CHECK_FOR_NEW_REPORTING_INTERVAL);
//	}

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		classes.add(Abfrage.class);

		return classes;
	}
}
