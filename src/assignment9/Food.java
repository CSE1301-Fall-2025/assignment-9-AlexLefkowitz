package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	private Color color;
	private double size;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		this.x = Math.random();
		this.y = Math.random();
		this.color = Color.RED;
		this.size = FOOD_SIZE;
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(this.color);
		StdDraw.filledCircle(this.x, this.y, size);
	}
	
}
