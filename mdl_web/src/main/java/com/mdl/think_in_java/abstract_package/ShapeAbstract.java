package com.mdl.think_in_java.abstract_package;

public abstract class ShapeAbstract implements ShapeInterface{

	public void draw(){
		System.out.println("draw ShapeAbstract");
	};
	
	public abstract void erase();

}
