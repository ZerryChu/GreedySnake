package Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class MyListener extends Thread {
	private JPanel mainpPanel;
	private MoveInformation moveInformation;

	public MyListener(JPanel mainPanel) {
		this.mainpPanel = mainPanel;
		moveInformation = new MoveInformation();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mainpPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				do_key_pressed(e.getKeyCode());
			}
		});
		mainpPanel.requestFocus();
	}

	public void do_key_pressed(int code) {
		switch (code) {
		case KeyEvent.VK_UP: {
			moveInformation.setNum(1);
			break;
		}
		case KeyEvent.VK_DOWN: {
			moveInformation.setNum(2);
			break;
		}
		case KeyEvent.VK_LEFT: {
			moveInformation.setNum(3);
			break;
		}
		case KeyEvent.VK_RIGHT: {
			moveInformation.setNum(4);
			break;
		}
		case KeyEvent.VK_SPACE: {
			moveInformation.setNum(5);
			break;
		}// restart
		default:
			break;
		}
	}

	public MoveInformation getMoveInformation() {
		return this.moveInformation;
	}
}
