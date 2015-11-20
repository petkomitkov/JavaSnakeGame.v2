import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Dimension;

public class snakeGame extends Canvas implements Runnable, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int GAME_H = 17;
	private final int GAME_W = 17;
	private final int PLAYGROUND_W = 23;
	private final int PLAYGROUND_H = 23;
	
	public final Dimension DIMENSION = new Dimension(640,480);
	
	private LinkedList<Point> snake;
	private Point apple;
	private int direction = Direction.NO_DIRECTION;
	
	private Thread runThread; //multitask
	private Graphics globalGraphics;
	private int score = 0;
	

	
	public void paint(Graphics g)
	{
		this.setPreferredSize(DIMENSION);
		snake = new LinkedList<Point>();
		GenerateDefaultSnake();
		PlaceFruit();
		globalGraphics = g.create();
		this.addKeyListener(this);
		if (runThread == null)
		{
			runThread = new Thread(this);
			runThread.start();
		}
	}
	
	public void GenerateDefaultSnake()
	{
		score = 0;
		snake.clear();
		
		snake.add(new Point(0,2));
		snake.add(new Point(0,1));
		snake.add(new Point(0,0));
		direction = Direction.NO_DIRECTION;
	}
	
	public void Draw(Graphics g)
	{
		g.clearRect(0, 0, GAME_W * PLAYGROUND_W + 10, GAME_H * PLAYGROUND_H + 20);
		//create a new image
		BufferedImage buffer = new BufferedImage(GAME_W * PLAYGROUND_W + 10, GAME_H * PLAYGROUND_H + 20, BufferedImage.TYPE_INT_ARGB);
		
		DrawFruit(g);
		DrawGrid(g);
		DrawSnake(g);
		DrawScore(g);
		
		//flip
		g.drawImage(buffer, 0, 0, GAME_W * PLAYGROUND_W + 10,  GAME_H * PLAYGROUND_H + 20, this);
	}
	
	public void Move()
	{
		Point head = snake.peekFirst(); // Vzima glavata na zmiqta
		Point newPoint = head;			// kopira glavata v nova promenliva
		switch (direction) {
		case Direction.NORTH:
			newPoint = new Point(head.x, head.y - 1);
			break;
		case Direction.SOUTH:
			newPoint = new Point(head.x, head.y + 1);
			break;
		case Direction.WEST:
			newPoint = new Point(head.x - 1, head.y);
			break;
		case Direction.EAST:
			newPoint = new Point(head.x + 1, head.y);
			break;
		}
		
		snake.remove(snake.peekLast());  // "reje" opashkata na zmiqta
		
		if (newPoint.equals(apple))
		{
			score+=15;
			
			Point addPoint = (Point) newPoint.clone();
			
			switch (direction) {
			case Direction.NORTH:
				newPoint = new Point(head.x, head.y - 1);
				break;
			case Direction.SOUTH:
				newPoint = new Point(head.x, head.y + 1);
				break;
			case Direction.WEST:
				newPoint = new Point(head.x - 1, head.y);
				break;
			case Direction.EAST:
				newPoint = new Point(head.x + 1, head.y);
				break;
			}
			
			snake.push(addPoint);
			PlaceFruit();
			
		}
		else if (newPoint.x < 0 || newPoint.x > (PLAYGROUND_W - 1))
		{
			GenerateDefaultSnake();
			return;
		}
		else if (newPoint.y < 0 || newPoint.y > (PLAYGROUND_H - 1))
		{
			GenerateDefaultSnake();
			return;
		}
		else if (snake.contains(newPoint))
		{		
			GenerateDefaultSnake();
			return;
		}
		snake.push(newPoint);
	}
	
	public void DrawScore(Graphics g)
	{
		g.drawString("Score: " + score, 0, GAME_W * PLAYGROUND_W + 10);
	}
	
	public void DrawGrid(Graphics g)
	{
		g.drawRect(0, 0, PLAYGROUND_W * GAME_W, PLAYGROUND_H * GAME_H);
		for (int x = GAME_W; x < PLAYGROUND_W * GAME_W; x+=GAME_W)
		{
			g.drawLine(x, 0, x, GAME_H * PLAYGROUND_H);
		}
		for (int y = GAME_H; y < PLAYGROUND_H * GAME_H; y+=GAME_H)
		{
			g.drawLine(0, y, PLAYGROUND_W * GAME_W, y);
		}
	}
	
	public void DrawSnake(Graphics g)
	{
		g.setColor(Color.BLUE);
		for (Point p : snake)
		{
			g.fillRect(p.x * GAME_W, p.y * GAME_H, GAME_W, GAME_H);
		}
		g.setColor(Color.BLACK);
	}
	
	public void DrawFruit(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(apple.x * GAME_W, apple.y * GAME_H, GAME_W, GAME_H);
		g.setColor(Color.BLACK);
	}

	public void PlaceFruit()
	{
		Random rand = new Random();
		int randomX = rand.nextInt(PLAYGROUND_W);
		int randomY = rand.nextInt(PLAYGROUND_H);
		Point randomPoint = new Point(randomX, randomY);
		while (snake.contains(randomPoint))
		{
			randomX = rand.nextInt(PLAYGROUND_W);
			randomY = rand.nextInt(PLAYGROUND_H);
			randomPoint = new Point(randomX, randomY);
		}
		apple = randomPoint;
	}
	
	@Override
	public void run() {
		while (true)
		{
			Move();
			Draw(globalGraphics);
			
			try
			{
				Thread.currentThread();
				Thread.sleep(100);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		System.out.println("key presssed");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if (direction != Direction.SOUTH)
				direction = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			if (direction != Direction.NORTH)
				direction = Direction.SOUTH;
			break;
		case KeyEvent.VK_RIGHT:
			if (direction != Direction.WEST)
				direction = Direction.EAST;
			break;
		case KeyEvent.VK_LEFT:
			if (direction != Direction.EAST)
				direction = Direction.WEST;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		System.out.println("key released");
		
	}
}