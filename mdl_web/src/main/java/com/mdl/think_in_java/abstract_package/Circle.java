package com.mdl.think_in_java.abstract_package;

public class Circle extends ShapeAbstract {

	@Override
	public void erase() {
		System.out.println("erase Circle");
		
	}

	@Override
	public void draw() {
		System.out.println("draw Circle");
	}

	@Override
	public void color() {
		System.out.println("color Circle");
	}


}
