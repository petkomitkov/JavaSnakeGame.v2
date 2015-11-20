import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;

public class snakePanel extends Applet{

	private snakeGame c;
	
	public void init()
	{
		c = new snakeGame();
		c.setPreferredSize(new Dimension(c.DIMENSION));
		c.setVisible(true);
		c.setFocusable(true);
		this.add(c);
		this.setVisible(true);
		this.setSize(new Dimension(c.DIMENSION));
	}
	
	public void paint(Graphics g)
	{
		this.setSize(new Dimension(c.DIMENSION));
	}
}
