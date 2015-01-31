package Snake;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Operation {
	/**
	 * @wbp.parser.entryPoint
	 */
	private int[][] flag; // 0 presents nothing, 1 presents snake, 2 presents the food, 3 presents the head of the snake

	public Operation(int maxn) {
		flag = new int[maxn][maxn];
		for (int i = 0; i < maxn; i++)
			for (int j = 0; j < maxn; j++)
				flag[i][j] = 0;
	}
	
	public void init(JLabel[][] text, JPanel panel, int maxn) {
		panel.setBounds(14, 85, 500, 500);
		panel.setLayout(null);
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("1");
				int code = e.getKeyCode();
				do_key_pressed(code);
			}
		});
		for (int i = 0; i < maxn; i++)
			for (int j = 0; j < maxn; j++) {
				text[i][j] = new JLabel();
				text[i][j].setBounds(25 * j + 2, 25 * i, 25, 25);
				text[i][j].setBackground(Color.WHITE);
				text[i][j].setBorder(BorderFactory
						.createLineBorder(Color.WHITE));
				text[i][j].setOpaque(true);
				panel.add(text[i][j]);
			}
		panel.requestFocus();
	}

	protected void do_key_pressed(int code) {
		switch (code) {
		case KeyEvent.VK_UP: {

			break;
		}
		case KeyEvent.VK_DOWN: {

			break;
		}
		case KeyEvent.VK_LEFT: {

			break;
		}
		case KeyEvent.VK_RIGHT: {

			break;
		}
		default:
			break;
		}
	}

	public void display(JLabel[][] text) {
		setFood(text);
		setSnake(text);
	}

	public void setFood(JLabel[][] text) {
		Random random = new Random();
		int x = random.nextInt(20);
		int y = random.nextInt(20);
		while (flag[x][y] == 1 || flag[x][y] == 3) {
			x = random.nextInt(20);
			y = random.nextInt(20);
		}
		flag[x][y] = 2;
		text[x][y].setBackground(Color.RED);
		text[x][y].setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void setSnake(JLabel[][] text) {
		Random random = new Random();
		int x = random.nextInt(20);
		int y = random.nextInt(20);
		while (flag[x][y] == 2) {
			x = random.nextInt(20);
			y = random.nextInt(20);
		}
		text[x][y].setBackground(Color.BLACK);
		text[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
//食物被吃，flag变为3，蛇头随触发改方向，别的位置跟蛇头走