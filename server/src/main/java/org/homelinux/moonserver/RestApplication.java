package org.homelinux.moonserver;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

import javax.ws.rs.core.Application;

import org.homelinux.moonserver.task.CheckForNewMailTask;

public class RestApplication extends Application {

	// Alle 15 Minuten wird das Postfach auf neue Mails geprueft
	private static final long CHECK_FOR_NEW_REPORTING_INTERVAL = 15 * 60 * 1000;

	public RestApplication() {
		startCheckForNewMailTask();
	}

	private void startCheckForNewMailTask() {
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new CheckForNewMailTask(), 0, CHECK_FOR_NEW_REPORTING_INTERVAL);
	}

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		classes.add(Abfrage.class);
		classes.add(BuchenAktualisierung.class);

		return classes;
	}
}
