package com.namlee.examples.spring_examples.model;

public class Hello {
	private String greeting;

	public Hello() {
	}

	public Hello(String greeting) {
		this.greeting = greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return this.greeting;
	}
}