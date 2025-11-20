package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<BodySegment>();
		BodySegment firstSegment = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(firstSegment);
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	public BodySegment changeValX(BodySegment segment, double x){
		segment.setLastX(segment.getX());
		segment.setChangeX(segment.getX()-x);
		segment.setX(x);
		return segment;
	}
	public BodySegment changeValY(BodySegment segment, double y){
		segment.setLastY(segment.getY());
		segment.setChangeY(segment.getX()-y);
		segment.setY(y);
		return segment;
	}
	public BodySegment changeX(BodySegment segment, double x){
		segment.setLastX(segment.getX());
		segment.setChangeX(x);
		segment.setX(segment.getX()+x);
		return segment;
	}
	public BodySegment changeY(BodySegment segment, double y){
		segment.setLastY(segment.getY());
		segment.setChangeY(y);
		segment.setY(segment.getY()+y);
		return segment;
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		segments.set(0, this.changeX(segments.getFirst(), deltaX));
		segments.set(0, this.changeY(segments.getFirst(), deltaY));
		for (int i = 1; i<segments.size(); i++){
			segments.set(i, this.changeValX(segments.get(i), segments.get(i-1).getLastX()));
			segments.set(i, this.changeValY(segments.get(i), segments.get(i-1).getLastY()));
			//segments.set(i, this.changeX(segments.get(i), segments.get(i-1).getChangeX()));
			//segments.set(i, this.changeY(segments.get(i), segments.get(i-1).getChangeY()));
		}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		if (Math.sqrt(Math.pow(f.getX()-segments.getFirst().getX(),2)+Math.pow(f.getY()-segments.getFirst().getY(),2))<=(SEGMENT_SIZE+Food.FOOD_SIZE)){
			segments.add(new BodySegment(segments.getLast().getLastX(), segments.getLast().getLastY(), SEGMENT_SIZE));
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		if ((segments.getFirst().getX()>1)||(segments.getFirst().getX()<0)){
		return false;
	}
	if((segments.getFirst().getY()>1)||(segments.getFirst().getY()<0)){
		return false;
		
	}
		return true;
	}
}
