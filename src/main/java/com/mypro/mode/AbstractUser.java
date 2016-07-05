package com.mypro.mode;

public abstract class AbstractUser {

	private Mediator mediator;

	public AbstractUser(Mediator mediator) {
		super();
		this.mediator = mediator;
	}

	public AbstractUser() {
		super();
	}
	
	abstract public void work();
}
