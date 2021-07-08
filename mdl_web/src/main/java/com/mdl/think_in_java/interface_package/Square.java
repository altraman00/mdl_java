package com.mdl.think_in_java.interface_package;

public class Square implements ShapeInterface {

	@Override
	public void draw() {
		System.out.println("draw Square");
	}

	@Override
	public void erase() {
		System.out.println("erase Square");
	}

}
