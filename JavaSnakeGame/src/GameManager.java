import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameManager implements KeyListener{

	private Snake snake;
	private boolean isGameOver;
	private Direction dir;
	private GameGrid gr;
	
	public GameManager(Snake snake,Grid gr) {
		this.snake = snake;
		isGameOver = false;
		dir = dir.RIGHT;
		this.gr = gr;
	}
	public void PlayGame() {
		
		while(isGameOver == false) {
			gr.drawing();
			snake.Move(dir);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if (dir != dir.DOWN)
				dir = dir.UP;
			break;
		case KeyEvent.VK_DOWN:
			if (dir != dir.UP)
				dir = dir.DOWN;
			break;
		case KeyEvent.VK_RIGHT:
			if (dir != dir.RIGHT)
				dir = dir.LEFT;
			break;
		case KeyEvent.VK_LEFT:
			if (dir != dir.LEFT)
				dir = dir.RIGHT;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

