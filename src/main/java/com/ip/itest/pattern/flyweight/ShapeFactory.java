/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.flyweight;

import java.util.HashMap;

public class ShapeFactory {
	public static final HashMap<String, Shape> shapemap = new HashMap<String, Shape>();

	public static Shape getShape(String shapeType) {
		Shape shape = null;
		if(shapeType.equalsIgnoreCase("circle")){
			shape=(CircleCreator)shapemap.get("circle");
			if(shape==null){
				shape= new CircleCreator();
				shapemap.put("circle", shape);
				System.out.println("New Object Created");
			}
		}
		return shape;
	}
}
