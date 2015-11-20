import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import teest.Direction;

public class Snake implements IDrawable {
	
	private LinkedList<SnakePart> snake;
	
	public Snake(LinkedList<SnakePart> snake) {
		
		this.snake = snake;
	}
	
	public void Move(Direction dir,int gridHeight,int gridWidth) {
		
		public void Move(Direction dir) {
			
			SnakePart head = snake.peekFirst();
			SnakePart dup = head;
			
			switch (dir) {
			case UP:
				dup = new Point(head.x, head.y - 10);
				break;
			case DOWN:
				dup = new Point(head.x, head.y + 10);
				break;
			case RIGHT:
				dup = new Point(head.x + 10, head.y);
				break;
			case LEFT:
				dup = new Point(head.x + 10, head.y);
				break;
			}
		
	}

	@Override
	public void draw(Graphics g) {
		
		
		
	}
	
	

}
