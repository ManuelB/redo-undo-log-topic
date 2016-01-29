package de.blechschmidt.example.web.rest;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import de.blechschmidt.example.entities.Thing;

@Stateless
@Path("/demo")
public class DemoCreation {
	@PersistenceContext
	EntityManager em;

	@GET
	public String get() {
		Thing entity = new Thing();
		String uuid = UUID.randomUUID().toString();
		entity.setId(uuid);
		entity.setName(uuid);
		em.persist(entity);
		return "Create entity with id: " + uuid;
	}
}
