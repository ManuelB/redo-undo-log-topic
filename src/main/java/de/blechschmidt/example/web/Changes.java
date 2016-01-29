package de.blechschmidt.example.web;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@Singleton
@JMSDestinationDefinitions({ @JMSDestinationDefinition(name = JMS.redoUndo, destinationName = "redoUndo", description = "This topic will have all changes from entities.", interfaceName = "javax.jms.Topic"), })
@Startup
public class Changes {

	private static Logger log = Logger.getLogger(Changes.class.getName());
	
	@PostConstruct
	public void init() {
		log.info("Application started");
	}

}
