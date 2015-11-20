import javax.swing.JPanel;
import java.awt.Graphics;

public class GameGrid extends JPanel{

	private Iterable<IDrawable> els;
	private Graphics globalGraphics;
	private int Currentscore;
	
	public void drawing() {
		repaint();
	}
	
	public void paintComponents(Graphics g){
		super.paintComponent(g);
		
		for (IDrawable object : els) {
		    object.draw(g);
		}
	}
}
