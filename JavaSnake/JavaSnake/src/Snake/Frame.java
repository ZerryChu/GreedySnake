package Snake;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JavaSnake
 *
 * @author ZerryChu
 *
 */
public class Frame extends JFrame {
	/**
	 * 
	 */
	private int maxn = 20;
	private JLabel[][] text;
	private JPanel mainPanel;
	private Operation operation;
	private MyListener listener;
	private static final long serialVersionUID = 1L;

	public Frame() throws InterruptedException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JavaSnake");
		// this.setSize(210, 210);
		this.setBounds(500, 200, 418, 440);
		setMainPanel();
		setText();
		operation = new Operation(text, maxn);
		listener = new MyListener(mainPanel);
		listener.start(); // Ö´ÐÐ¼àÌýÏß³Ì
		operation.setMoveInformation(listener);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		operation.display(text);

	}

	public void setMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true);// @$@@!%@ what the hell?
	}

	public void setText() {
		text = new JLabel[maxn][maxn];
		for (int i = 0; i < maxn; i++)
			for (int j = 0; j < maxn; j++) {
				text[i][j] = new JLabel();
				text[i][j].setBounds(20 * j + 2, 20 * i, 20, 20);
				text[i][j].setBackground(Color.WHITE);
				text[i][j].setBorder(BorderFactory
						.createLineBorder(Color.WHITE));
				text[i][j].setOpaque(true);
				mainPanel.add(text[i][j]);
			}
	}

}
