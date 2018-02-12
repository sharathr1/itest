/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.flyweight;

public class CircleCreator implements Shape{
	
private String color;
private final int x=1;
private final int y=2;
private final int radius=10;


public CircleCreator() {
	super();
}
public CircleCreator(String color) {
	super();
	this.color = color;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getX() {
	return x;
}

public int getY() {
	return y;
}

public int getRadius() {
	return radius;
}


@Override
public void draw() {
	// TODO Auto-generated method stub
	System.out.println( this+" :CircleCreator [color=" + color + ", x=" + x + ", y=" + y + ", radius=" + radius );
}

//@Override
//public String toString() {
//	return "CircleCreator [color=" + color + ", x=" + x + ", y=" + y + ", radius=" + radius + "]";
//}
//

}
