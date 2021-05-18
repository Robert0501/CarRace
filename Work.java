import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Work extends JPanel implements ActionListener, KeyListener {
	private int space;
	private int width = 80;
	private int height = 70;
	private int speed;
	private int WIDTH = 500;
	private int HEIGHT = 700;
	private int move = 20;
	private int count = 1;
	private int score = 0;
	private int scoreIncrease = 1;
	private boolean colision = false;
	private ArrayList<Rectangle> ocars;
	private Rectangle car;
	private Random rand;
	Timer t;

	public Work() {
		t = new Timer(20, this);
		rand = new Random();
		ocars = new ArrayList<Rectangle>();
		car = new Rectangle(WIDTH / 2 - 90, HEIGHT - 100, width, height);
		this.space = 300;
		this.speed = 2;
		addKeyListener(this);
		setFocusable(true);
		addocars(true);
		addocars(true);
		addocars(true);
		addocars(true);
		t.start();
	}

	public void addocars(boolean first) {
		int positionx = rand.nextInt() % 2;
		int x = 0;
		int y = 0;
		int width = this.width;
		int height = this.height;

		if (positionx == 0) {
			x = WIDTH / 2 - 90;
		} else {
			x = WIDTH / 2 + 10;
		}

		if (first) {
			ocars.add(new Rectangle(x, y - 100 - (ocars.size() * space), width, height));
		} else {
			ocars.add(new Rectangle(x, ocars.get(ocars.size() - 1).y - 300, width, height));
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.gray);
		g.fillRect(WIDTH / 2 - 100, 0, 200, HEIGHT);
		g.setColor(Color.red);
		g.fillRect(car.x, car.y, car.width, car.height);
		g.setColor(Color.red);
		g.setColor(Color.blue);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Score: " + score, 20, 20);

		g.setColor(Color.magenta);
		for (Rectangle rect : ocars) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			if (colision) {
				break;
			}
		}

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		if (colision) {
			g.drawString("Game Over", HEIGHT / 4, WIDTH / 2);
		}

	}

	public void actionPerformed(ActionEvent e) {
		Rectangle rect;
		count++;
		for (int i = 0; i < ocars.size(); i++) {
			rect = ocars.get(i);
			if (count % 1000 == 0) {
				speed++;
				if (move < 50) {
					move += 10;
					scoreIncrease++;
				}
			}
			rect.y += speed;

		}
		// cars crashing with opponents
		for (Rectangle r : ocars) {
			if (r.intersects(car)) {
				colision = true;
				repaint();
				t.stop();
			}
		}

		for (int i = 0; i < ocars.size(); i++) {
			rect = ocars.get(i);
			if (rect.y + rect.height > HEIGHT) {
				ocars.remove(rect);
				addocars(false);
			}
			score += scoreIncrease;
		}

		repaint();
	}

	public void moveUp() {
		if (car.y - move < 0) {
			System.out.println("\b");
		} else {
			car.y -= move;
		}
	}

	public void moveDown() {
		if (car.y + move + car.height > HEIGHT) {
			System.out.println("\b");
		} else {
			car.y += move;
		}
	}

	public void moveLeft() {
		if (car.x - move < WIDTH / 2 - 90) {
			System.out.println("\b");
		} else {
			car.x -= move;
		}
	}

	public void moveRight() {
		if (car.x + move > WIDTH / 2 + 10) {
			System.out.println("\b");
		} else {
			car.x += move;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

}
