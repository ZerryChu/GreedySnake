package Snake;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class Operation {
	/**
	 * @wbp.parser.entryPoint
	 */
	private int[][] flag; // 0 presents nothing, 1~n presents snake, -1 presents
							// the food,
	private int pos_i, pos_j; /* 蛇头坐标 */
	private int next_i, next_j;
	private int size;
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
		pos_i = x;
		pos_j = y;
		size = 1;
		flag[x][y] = 1;
		text[x][y].setBackground(Color.BLACK);
		// text[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	public void get_nextPos(int i, int j, int num) {
		if (i > 0 && flag[i - 1][j] == num) {
			next_i = i - 1;
			next_j = j;
			return;
		}
		if (i > 0 && j > 0 && flag[i - 1][j - 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (i > 0 && j < 19 && flag[i - 1][j + 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (j > 0 && flag[i][j - 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (j < 19 && flag[i][j + 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (i < 19 && flag[i + 1][j] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (i < 19 && j > 0 && flag[i + 1][j - 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
		if (i < 19 && j < 19 && flag[i + 1][j + 1] == num) {
			next_i = i - 1;
			next_j = j - 1;
			return;
		}
	}

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
				for(int i = 0;i < maxn; i++)
					for(int j = 0;j < maxn; j++)
						if(flag[i][j] != 0) {
							flag[i][j] = 0; 
							text[i][j].setBackground(Color.WHITE);
						}
				display(text);
			}// restart
			default:
				break;
			}
			Thread.currentThread();
			Thread.sleep(500);
		}
	}

	public void turnUp(JLabel[][] text) throws InterruptedException { // 待修改
		int temp = 1;
		int now_i = pos_i;
		int now_j = pos_j;
		get_nextPos(now_i, now_j, ++temp);
		flag[pos_i][pos_i] = 0;
		text[pos_i][pos_j].setBackground(Color.WHITE);
		flag[--pos_i][pos_j] = 1; // check
		text[pos_i][pos_j].setBackground(Color.BLACK);
		if (pos_i == 0)
			return; // add 容错
		while (temp++ < size) {
			int to_i = now_i;
			int to_j = now_j;
			now_i = next_i;
			now_j = next_j;
			flag[now_i][now_j] = 0;
			text[now_i][now_j].setBackground(Color.WHITE);
			flag[to_i][to_j] = temp;
			text[to_i][to_j].setBackground(Color.BLACK);
			get_nextPos(now_i, now_j, temp);
		}
	}

	public void turnDown(JLabel[][] text) throws InterruptedException {
		int temp = 1;
		int now_i = pos_i;
		int now_j = pos_j;
		get_nextPos(now_i, now_j, ++temp);
		flag[pos_i][pos_i] = 0;
		text[pos_i][pos_j].setBackground(Color.WHITE);
		flag[++pos_i][pos_j] = 1; // check
		text[pos_i][pos_j].setBackground(Color.BLACK);
		if (pos_i == 19)
			return; // add 容错
		while (temp++ < size) {
			int to_i = now_i;
			int to_j = now_j;
			now_i = next_i;
			now_j = next_j;
			flag[now_i][now_j] = 0;
			text[now_i][now_j].setBackground(Color.WHITE);
			flag[to_i][to_j] = temp;
			text[to_i][to_j].setBackground(Color.BLACK);
			get_nextPos(now_i, now_j, temp);
		}
	}

	public void turnLeft(JLabel[][] text) throws InterruptedException {
		int temp = 1;
		int now_i = pos_i;
		int now_j = pos_j;
		get_nextPos(now_i, now_j, temp++);
		flag[pos_i][pos_i] = 0;
		text[pos_i][pos_j].setBackground(Color.WHITE);
		flag[pos_i][--pos_j] = 1; // check
		text[pos_i][pos_j].setBackground(Color.BLACK);
		if (pos_j == 0)
			return; // add 容错
		while (temp++ < size) {
			int to_i = now_i;
			int to_j = now_j;
			now_i = next_i;
			now_j = next_j;
			flag[now_i][now_j] = 0;
			text[now_i][now_j].setBackground(Color.WHITE);
			flag[to_i][to_j] = temp;
			text[to_i][to_j].setBackground(Color.BLACK);
			get_nextPos(now_i, now_j, temp);
		}

	}

	public void turnRight(JLabel[][] text) throws InterruptedException {
		int temp = 1;
		int now_i = pos_i;
		int now_j = pos_j;
		get_nextPos(now_i, now_j, ++temp);
		flag[pos_i][pos_i] = 0;
		text[pos_i][pos_j].setBackground(Color.WHITE);
		flag[pos_i][++pos_j] = 1; // check
		text[pos_i][pos_j].setBackground(Color.BLACK);
		if (pos_j == 19)
			return; // add 容错
		while (temp++ < size) {
			int to_i = now_i;
			int to_j = now_j;
			now_i = next_i;
			now_j = next_j;
			flag[now_i][now_j] = 0;
			text[now_i][now_j].setBackground(Color.WHITE);
			flag[to_i][to_j] = temp;
			text[to_i][to_j].setBackground(Color.BLACK);
			get_nextPos(now_i, now_j, temp);
		}
	}
}
// 食物被吃，flag变为3，蛇头随触发改方向，别的位置跟蛇头走