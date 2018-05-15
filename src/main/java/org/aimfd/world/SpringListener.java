package org.aimfd.world;

import org.aimfd.base.GameSocket;
import org.aimfd.base.SpringContext;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class SpringListener implements ApplicationListener<ApplicationStartedEvent> {

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {

		SpringContext.setApplicationContext(event.getApplicationContext());

		GameSocket gameSocket = new WorldGameSocket();

		gameSocket.buildServer();
		gameSocket.setPort(8081);
		gameSocket.start();

	}

}
