import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SnakePart extends GraphicsObject implements IDrawable {
	
	private int size;
	private Point coordinates;

	public SnakePart(Point coords, Color color, int size) {
		
		this.coordinates = coords;
		this.color = color;
		this.size = size;
	}
	
	public void setCoordinates(int x,int y) {
		
		this.coordinates.x = x;
		this.coordinates.y = y;
	}
	public Point getCoordinates() {
		
		return this.coordinates;
	}
	
	public int getSize() {
		
		return this.size;
	}
	
	public void setSize(int size) {	
		
		this.size = size;
	}
	
	public Color getColor() {
		
		return this.color;
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(this.color);
		g.fillRect(coords.x, coords.y, this.size, this.size);
		
	}
}
