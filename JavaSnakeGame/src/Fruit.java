import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Fruit extends GraphicsObject implements IDrawable{
	
	
	public Fruit(Point coords,Color color,int size) {
		
		this.color = color;
		this.size = size;
		this.coords = coords;
		
	}
	
	public Color getColor() {
		
		return this.color;
	}
	
	public void setCoords(Point coords) {
		
		this.coords = coords;
		
	}
	
	public Point getCoords() {
		
		return new Point(this.coords.x,this.coords.y);
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(this.color);
		g.fillOval(this.coords.x, this.coords.y, this.size, this.size);
		
	}

}
