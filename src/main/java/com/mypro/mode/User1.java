package com.mypro.mode;

public class User1 extends AbstractUser {
	
	public User1(Mediator mediator) {
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("this is user1 work");
	}

}
