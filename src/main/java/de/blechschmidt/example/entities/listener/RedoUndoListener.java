package de.blechschmidt.example.entities.listener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

import de.blechschmidt.example.web.JMS;

public class RedoUndoListener {

	@Resource(lookup = JMS.redoUndo)
	private Topic redoUndo;
	
	@Inject
	BeanManager beanManager; //Workaround WFLY-2387
	
	private Map<Object, Object> unmodifiedObjects = new HashMap<Object, Object>();

	@javax.persistence.PostLoad
	public void PostLoad(Object o) {
		unmodifiedObjects.put(o, o);
	}

	@javax.persistence.PostUpdate
	public void PostUpdate(Object o) {
		Log log = new Log();
		log.setTimestamp(Calendar.getInstance());
		log.setAction(Action.CHANGE);
		log.setOldValue(unmodifiedObjects.get(o));
		log.setNewValue(o);
		Set<Bean<?>> beans = beanManager.getBeans(JMSContext.class);
        Bean<?> bean = beans.iterator().next();
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        JMSContext jmsContext =  (JMSContext) beanManager.getReference(bean, JMSContext.class, ctx);
		jmsContext.createProducer().send(redoUndo, log);
	}

	@javax.persistence.PostRemove
	public void PostRemove(Object o) {
		Log log = new Log();
		log.setTimestamp(Calendar.getInstance());
		log.setAction(Action.REMOVE);
		log.setOldValue(unmodifiedObjects.get(o));
		Set<Bean<?>> beans = beanManager.getBeans(JMSContext.class);
        Bean<?> bean = beans.iterator().next();
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        JMSContext jmsContext =  (JMSContext) beanManager.getReference(bean, JMSContext.class, ctx);
		jmsContext.createProducer().send(redoUndo, log);
	}

	@javax.persistence.PostPersist
	public void PostPersist(Object o) {
		Log log = new Log();
		log.setTimestamp(Calendar.getInstance());
		log.setAction(Action.ADD);
		log.setNewValue(o);
		broadcast(log);
	}

	private void broadcast(Log log) {
		Set<Bean<?>> beans = beanManager.getBeans(JMSContext.class);
        Bean<?> bean = beans.iterator().next();
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        JMSContext jmsContext =  (JMSContext) beanManager.getReference(bean, JMSContext.class, ctx);
		jmsContext.createProducer().send(redoUndo, log);
	}

}
