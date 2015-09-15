package gummyshootergame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	private GummyBear gummy;
	private Image image, character;
	private URL base;
	private Graphics second;
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		
		// add key listener
		addKeyListener(this);
		
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Gummy shooters!");
		
		try {
			base = getDocumentBase();
			System.out.println("Document base: " + base);
		} catch (Exception e) {
			System.out.println("Exception getting documentBase: " + e.getMessage());
		}
		
		// image setup
		character = getImage(base, "data/character.png");
	}

	@Override
	public void start() {
		gummy = new GummyBear();
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}
	
	@Override
	public void run() {
		while (true) {
			gummy.update();
			repaint();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(character, gummy.getCenterX() - 61, gummy.getCenterY() - 63, this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Move down");
			break;
		case KeyEvent.VK_LEFT:
			gummy.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			gummy.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			gummy.jump();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Stop moving down");
			break;
		case KeyEvent.VK_LEFT:
			gummy.stop();
			break;
		case KeyEvent.VK_RIGHT:
			gummy.stop();
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
