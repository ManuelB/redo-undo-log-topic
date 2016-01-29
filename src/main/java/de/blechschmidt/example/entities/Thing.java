package de.blechschmidt.example.entities;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.persistence.Id;

import de.blechschmidt.example.entities.listener.RedoUndoListener;

@javax.persistence.Entity
@EntityListeners(RedoUndoListener.class)
public class Thing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1515453552216770655L;

	/** Use java.util.UUID.randomUUID().toString() for generating an ID */
	@Id
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone() {
		Thing e = new Thing();
		e.setId(id);
		e.setName(name);
		return e;
	}

	public int hashCode() {
		return id.hashCode();
	}

}
