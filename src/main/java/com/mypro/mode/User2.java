package com.mypro.mode;

public class User2 extends AbstractUser {

	
	public User2(Mediator mediator) {
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("this is user2 work");
	}

}
