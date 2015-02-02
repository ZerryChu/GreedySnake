package Snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class Operation {
	/**
	 * @wbp.parser.entryPoint
	 */
	private int[][] flag; // 0 presents nothing, 1 presents snake, -1 presents
							// the food,
	private ArrayList<Position> snakePos; // 蛇的坐标
	private int maxn;
	private MoveInformation moveInformation;
	private JLabel[][] text;

	public Operation(JLabel[][] text, int maxn) {
		this.maxn = maxn;
		flag = new int[maxn][maxn];
		this.text = text;
		for (int i = 0; i < maxn; i++)
			for (int j = 0; j < maxn; j++)
				flag[i][j] = 0;
		snakePos = new ArrayList<Position>();
	}

	public void setMoveInformation(MyListener myListener) {
		this.moveInformation = myListener.getMoveInformation();
	}

	public void display(JLabel[][] text) throws InterruptedException {
		setFood(text);
		setSnake(text);
		move();
	}

	public void setFood(JLabel[][] text) {
		Random random = new Random();
		int x = random.nextInt(20);
		int y = random.nextInt(20);
		while (flag[x][y] > 0) {
			x = random.nextInt(20);
			y = random.nextInt(20);
		}
		flag[x][y] = -1;
		text[x][y].setBackground(Color.RED);
		// text[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	public void setSnake(JLabel[][] text) {
		Random random = new Random();
		int x = random.nextInt(20);
		int y = random.nextInt(20);
		while (flag[x][y] == -1) {
			x = random.nextInt(20);
			y = random.nextInt(20);
		}
		Position headPos = new Position(x, y);
		snakePos.add(headPos);
		flag[x][y] = 1;
		text[x][y].setBackground(Color.BLACK);
		// text[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	/*
	 * public void get_nextPos(int i, int j, int num) { if (i > 0 && flag[i -
	 * 1][j] == num) { next_i = i - 1; next_j = j; return; } if (i > 0 && j > 0
	 * && flag[i - 1][j - 1] == num) { next_i = i - 1; next_j = j - 1; return; }
	 * if (i > 0 && j < 19 && flag[i - 1][j + 1] == num) { next_i = i - 1;
	 * next_j = j - 1; return; } if (j > 0 && flag[i][j - 1] == num) { next_i =
	 * i - 1; next_j = j - 1; return; } if (j < 19 && flag[i][j + 1] == num) {
	 * next_i = i - 1; next_j = j - 1; return; } if (i < 19 && flag[i + 1][j] ==
	 * num) { next_i = i - 1; next_j = j - 1; return; } if (i < 19 && j > 0 &&
	 * flag[i + 1][j - 1] == num) { next_i = i - 1; next_j = j - 1; return; } if
	 * (i < 19 && j < 19 && flag[i + 1][j + 1] == num) { next_i = i - 1; next_j
	 * = j - 1; return; } }
	 */
	// 急于写代码，考虑不周到，许多模块被迫重写，造成严重的时间浪费。

	public void move() throws InterruptedException {
		while (true) {
			switch (moveInformation.getNum()) {
			case 1: {
				turnUp(text);
				break;
			}
			case 2: {
				turnDown(text);
				break;
			}
			case 3: {
				turnLeft(text);
				break;
			}
			case 4: {
				turnRight(text);
				break;
			}
			case 5: {
				moveInformation.setNum(0);
				init(text);
				display(text);
			}// restart
			default:
				break;
			}
			Thread.currentThread();
			Thread.sleep(200);
		}
	}

	public void eatFood(JLabel[][] text) {
		int size;
		if ((size = snakePos.size()) > 1) {
			if (snakePos.get(size - 1).x - 1 > 0
					&& flag[snakePos.get(size - 1).x - 1][snakePos
							.get(size - 1).y] == 1) {
				flag[snakePos.get(size - 1).x + 1][snakePos.get(size - 1).y] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x + 1,
						snakePos.get(size - 1).y);
				snakePos.add(rearPos);
				return;
			} else if (snakePos.get(size - 1).x + 1 < 20
					&& flag[snakePos.get(size - 1).x + 1][snakePos
							.get(size - 1).y] == 1) {
				flag[snakePos.get(size - 1).x - 1][snakePos.get(size - 1).y] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x - 1,
						snakePos.get(size - 1).y);
				snakePos.add(rearPos);
				return;
			} else if (snakePos.get(size - 1).y - 1 > 0
					&& flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y - 1] == 1) {
				flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y + 1] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x,
						snakePos.get(size - 1).y + 1);
				snakePos.add(rearPos);
				return;
			} else if (snakePos.get(size - 1).y + 1 < 20
					&& flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y + 1] == 1) {
				flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y - 1] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x,
						snakePos.get(size - 1).y - 1);
				snakePos.add(rearPos);
				return;
			}
		} else {
			int num;
			if ((num = moveInformation.getNum()) == 1) {
				flag[snakePos.get(size - 1).x + 1][snakePos.get(size - 1).y] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x + 1,
						snakePos.get(size - 1).y);
				snakePos.add(rearPos);
				return;
			} else if (num == 2) {
				flag[snakePos.get(size - 1).x - 1][snakePos.get(size - 1).y] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x - 1,
						snakePos.get(size - 1).y);
				snakePos.add(rearPos);
				return;
			} else if (num == 3) {
				flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y + 1] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x,
						snakePos.get(size - 1).y + 1);
				snakePos.add(rearPos);
				return;
			} else {
				flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y - 1] = 1;
				Position rearPos = new Position(snakePos.get(size - 1).x + 1,
						snakePos.get(size - 1).y - 1);
				snakePos.add(rearPos);
				System.out.println(snakePos.size());
				return;
			}
		}
	}

	public void turnUp(JLabel[][] text) throws InterruptedException {
		int size = snakePos.size();
		text[snakePos.get(size - 1).x][snakePos.get(size - 1).y]
				.setBackground(Color.WHITE);
		flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y] = 0;
		for (int i = size - 1; i > 0; i--) {
			snakePos.get(i).x = snakePos.get(i - 1).x;
			snakePos.get(i).y = snakePos.get(i - 1).y;
		}
		snakePos.get(0).x--;
		if (snakePos.get(0).x < 0
				|| flag[snakePos.get(0).x][snakePos.get(0).y] == 1) {
			gameOver(text);
			return;
		}
		text[snakePos.get(0).x][snakePos.get(0).y].setBackground(Color.BLACK);
		if (flag[snakePos.get(0).x][snakePos.get(0).y] == -1) {
			flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
			eatFood(text);
			setFood(text);
			return;
		}
		flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
	}

	public void turnDown(JLabel[][] text) throws InterruptedException {
		int size = snakePos.size();
		text[snakePos.get(size - 1).x][snakePos.get(size - 1).y]
				.setBackground(Color.WHITE);
		flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y] = 0;
		for (int i = size - 1; i > 0; i--) {
			snakePos.get(i).x = snakePos.get(i - 1).x;
			snakePos.get(i).y = snakePos.get(i - 1).y;
		}
		snakePos.get(0).x++;
		if (snakePos.get(0).x == 20
				|| flag[snakePos.get(0).x][snakePos.get(0).y] == 1) {
			gameOver(text);
			return;
		}
		text[snakePos.get(0).x][snakePos.get(0).y].setBackground(Color.BLACK);
		if (flag[snakePos.get(0).x][snakePos.get(0).y] == -1) {
			flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
			eatFood(text);
			setFood(text);
			return;
		}
		flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
	}

	public void turnLeft(JLabel[][] text) throws InterruptedException {
		int size = snakePos.size();
		text[snakePos.get(size - 1).x][snakePos.get(size - 1).y]
				.setBackground(Color.WHITE);
		flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y] = 0;
		for (int i = size - 1; i > 0; i--) {
			snakePos.get(i).x = snakePos.get(i - 1).x;
			snakePos.get(i).y = snakePos.get(i - 1).y;
		}
		snakePos.get(0).y--;
		if (snakePos.get(0).y < 0
				|| flag[snakePos.get(0).x][snakePos.get(0).y] == 1) {
			gameOver(text);
			return;
		}
		text[snakePos.get(0).x][snakePos.get(0).y].setBackground(Color.BLACK);
		if (flag[snakePos.get(0).x][snakePos.get(0).y] == -1) {
			flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
			eatFood(text);
			setFood(text);
			return;
		}
		flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
	}

	public void turnRight(JLabel[][] text) throws InterruptedException {
		int size = snakePos.size();
		text[snakePos.get(size - 1).x][snakePos.get(size - 1).y]
				.setBackground(Color.WHITE);
		flag[snakePos.get(size - 1).x][snakePos.get(size - 1).y] = 0;
		for (int i = size - 1; i > 0; i--) {
			snakePos.get(i).x = snakePos.get(i - 1).x;
			snakePos.get(i).y = snakePos.get(i - 1).y;
		}
		snakePos.get(0).y++;
		if (snakePos.get(0).y == 20
				|| flag[snakePos.get(0).x][snakePos.get(0).y] == 1) {
			gameOver(text);
			return;
		}
		text[snakePos.get(0).x][snakePos.get(0).y].setBackground(Color.BLACK);
		if (flag[snakePos.get(0).x][snakePos.get(0).y] == -1) {
			flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
			eatFood(text);
			setFood(text);
			return;
		}
		flag[snakePos.get(0).x][snakePos.get(0).y] = 1;
	}

	public void init(JLabel[][] text) {
		for (int i = 0; i < maxn; i++)
			for (int j = 0; j < maxn; j++)
				if (flag[i][j] != 0) {
					flag[i][j] = 0;
					text[i][j].setBackground(Color.WHITE);
				}
		snakePos = new ArrayList<Position>();
	}

	public void gameOver(JLabel[][] text) throws InterruptedException {
		init(text);
		int temp = 5;
		snakePos = new ArrayList<Position>();
		text[8][temp++].setText("G");
		text[8][temp++].setText("A");
		text[8][temp++].setText("M");
		text[8][temp++].setText("E");
		text[8][temp++].setText("");
		text[8][temp++].setText("O");
		text[8][temp++].setText("V");
		text[8][temp++].setText("E");
		text[8][temp++].setText("R");
		text[8][temp++].setText("!");
		Thread.currentThread();
		Thread.sleep(3000);
		temp = 5;
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		text[8][temp++].setText("");
		moveInformation.setNum(5);// restart
	}
}
// 食物被吃，flag变为3，蛇头随触发改方向，别的位置跟蛇头走