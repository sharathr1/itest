/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.flyweight;

public class FlyWeightDesign {
	public static void main(String[] args) {
		System.out.println("Red");
		for (int i = 0; i < 10; i++) {
			CircleCreator c = (CircleCreator) ShapeFactory.getShape("circle");
			c.setColor("Red");
			c.draw();

		}
		System.out.println("Green");

		for (int i = 0; i < 11; i++) {
			CircleCreator c = (CircleCreator) ShapeFactory.getShape("circle");
			c.setColor("Green");
			c.draw();

		}
	}
}
