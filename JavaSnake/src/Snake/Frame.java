package Snake;

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
	private static final long serialVersionUID = 1L;

	public Frame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JavaSnake");
		this.setSize(520, 540);
		operation = new Operation(maxn);
		text = new JLabel[maxn][maxn];
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		operation.init(text, mainPanel, maxn);
		this.getContentPane().add(mainPanel);
		// mainPanel.requestFocus();
		operation.display(text);

	}
}
