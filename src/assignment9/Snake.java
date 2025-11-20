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
	public BodySegment changeX(BodySegment segment, double x){
		segment.setX(segment.getX()+x);
		return segment;
	}
	public BodySegment changeY(BodySegment segment, double y){
		segment.setY(segment.getY()+y);
		return segment;
	}
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public BodySegment moveToward(double xOther, double yOther, BodySegment segment) {
		double xVector = xOther - segment.getX();
		double yVector = yOther - segment.getY();
		if (xVector>.01||xVector<-.01){
			xVector = (xVector/Math.abs(xVector))*MOVEMENT_SIZE;
		}
		else {
			xVector=0;
		}
		if (yVector>.01||yVector<-.01){
			yVector = (yVector/Math.abs(yVector))*MOVEMENT_SIZE;
		}
		else{
			yVector=0;
		}
		if (Math.abs(xVector)>Math.abs(yVector)){
			this.changeX(segment, xVector);
		}
		else{
			this.changeY(segment, yVector);
		}
		return segment;
	}
	public void move() {
		segments.set(segments.size()-1, this.changeX(segments.getLast(), deltaX));
		segments.set(segments.size()-1, this.changeY(segments.getLast(), deltaY));
		for (int i = segments.size()-2; i >= 0; i--) {
			segments.set(i, moveToward(segments.get(i+1).getX(), segments.get(i+1).getY(), segments.get(i)));
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
		if (Math.sqrt(Math.pow(f.getX()-segments.getLast().getX(),2)+Math.pow(f.getY()-segments.getLast().getY(),2))<=(SEGMENT_SIZE+Food.FOOD_SIZE)){
			segments.add(new BodySegment(segments.getFirst().getX()+SEGMENT_SIZE, segments.getFirst().getY(), SEGMENT_SIZE));
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
		if ((segments.getLast().getX()>1)||(segments.getLast().getX()<0)){
		return false;
	}
	if((segments.getLast().getY()>1)||(segments.getLast().getY()<0)){
		return false;
		
	}
		return true;
	}
}
