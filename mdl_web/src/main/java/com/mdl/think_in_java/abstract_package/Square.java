package com.mdl.think_in_java.abstract_package;

public class Square extends ShapeAbstract{

	@Override
	public void erase() {
		System.out.println("erase Square");
	}

	@Override
	public void color() {
		System.out.println("color Square");
	}

}
