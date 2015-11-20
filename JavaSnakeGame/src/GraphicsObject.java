import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class GraphicsObject {

	protected Point coords;
	protected Color color;
	protected int size;
	
	public abstract void draw(Graphics g);
	
}
