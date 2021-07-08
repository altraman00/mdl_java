package com.mdl.think_in_java.abstract_package;

public class Main {

	public static void main(String[] args) {
		print(new Circle());
		System.out.println("\n");
		print(new Square());

	}

	public static void print(ShapeAbstract s){
		s.draw();
		s.erase();
		s.color();
	}
	
}
