package de.blechschmidt.example.entities.listener;

import java.io.Serializable;
import java.util.Calendar;

public class Log implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3372037808556865139L;

	private Calendar timestamp;
	private Action action; 
	private Object newValue;
	private Object oldValue;
	
	public Calendar getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	public Object getNewValue() {
		return newValue;
	}
	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}
	public Object getOldValue() {
		return oldValue;
	}
	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
}
