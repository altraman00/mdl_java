package com.mdl.think_in_java.interface_package;

public class Circle implements ShapeInterface {

	@Override
	public void draw() {
		System.out.println("draw Circle");
	}

	@Override
	public void erase() {
		System.out.println("erase Circle");
	}

}
