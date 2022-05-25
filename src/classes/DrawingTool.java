package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawingTool extends ColoredCanvasObject {
	
	private ArrayList<Position> points = new ArrayList<>();
	
	
	public DrawingTool(Size size, Position position, Color color) {
		super(size, position, color);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(getColor());
		
		for (int i = 1; i < points.size(); i++) {
			Position p1 = points.get(i-1);
			Position p2 = points.get(i);
			
			g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		super.renderSelection(g);
		
	}
	
	private void updateSize() {
		Position maxX = null;
		Position minX = null;
		Position maxY = null;
		Position minY = null;
		
		for (Position p : points) {
			if (maxX == null || maxX.getX() < p.getX()) {
				maxX = p;
			}
			if (minX == null || minX.getX() > p.getX()) {
				minX = p;
			}
			if (maxY == null || maxY.getY() < p.getY()) {
				maxY = p;
			}
			if (minY == null || minY.getY() > p.getY()) {
				minY = p;
			}
			
		}
		
		super.setSize(new Size(maxX.getX() - minX.getX(), maxY.getY() - minY.getY()));
		super.setPosition(new Position(minX.getX(), minY.getY()));
	}
	
	public void addPoint(Position p) {
		this.points.add(p);
		
		updateSize();
	}
	
	public void setPosition(Position position) {
		Position change = new Position(position.getX() - getPosition().getX(), position.getY() - getPosition().getY());
	
		for (Position p : points) {
			p.setX(p.getX() + change.getX());
			p.setY(p.getY() + change.getY());
			
		}
		
		super.setPosition(position);
	}
	
	
	public ArrayList<Position> getPoints() {
		
		return points;
	}
	
	
	

}
