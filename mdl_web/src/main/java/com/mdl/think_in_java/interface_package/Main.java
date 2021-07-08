package com.mdl.think_in_java.interface_package;

public class Main {

	public static void main(String[] args) {
		print(new Circle());
		print(new Square());
	}
	
	public static void print(ShapeInterface s){
		s.draw();
		s.erase();
	}

}
