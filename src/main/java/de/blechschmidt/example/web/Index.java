package de.blechschmidt.example.web;

import javax.enterprise.inject.Model;

@Model
public class Index {

	// setted by pretty-config.xml
	private String subscription;

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
}
