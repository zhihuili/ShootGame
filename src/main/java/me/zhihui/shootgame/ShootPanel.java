package me.zhihui.shootgame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShootPanel extends JPanel {

	private List<int[]> ll = new LinkedList<int[]>();
	private int offsetX, offsetY;
	private static int HOLE_OFFSET = 4;
	private int[] ghost = new int[2];
	private int intervel = 1000;
	private int killed = 0;
	private int missed = 0;

	public ShootPanel() {
		// 设置窗口的光标
		setCursor(createCursor());
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(BackGround.getBackGround(this), 0, 0, null);
		for (int[] p : ll) {
			g.setColor(Color.BLACK);
			g.fillOval(p[0], p[1], HOLE_OFFSET * 2, HOLE_OFFSET * 2);
		}
		if (ghost[0] > 0 && ghost[1] > 0) {
			g.drawImage(Ghost.getGhost(this), ghost[0], ghost[1], null);
		}
		drawScore(g);
	}

	private void drawScore(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // 字体
		g.setColor(Color.RED);
		g.setFont(font);
		String tips = "KILLED:" + killed + " MISSED:" + missed;
		g.drawString(tips, 600, 20);
	}

	public void action() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Thread(() -> {
					int[] p = new int[2];
					p[0] = e.getX() + offsetX;
					p[1] = e.getY() + offsetY;
					if (p[0] > ghost[0] && p[0] < ghost[0] + Ghost.ghostWidth && p[1] > ghost[1]
							&& p[1] < ghost[1] + Ghost.ghostHeight) {
						killed++;
					} else {
						missed--;
					}
					ll.add(p);
					repaint();
				}).start();
			}
		});

		Timer timer = new Timer(); // 生成ghost
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Random r = new Random();
				ghost[0] = r.nextInt(1300);
				ghost[1] = r.nextInt(700);
				repaint(); // 重绘，调用paint()方法
			}

		}, intervel, intervel);
	}

	private Cursor createCursor() {
		BufferedImage cursorImage = null;
		try {
			cursorImage = ImageIO.read(ShootPanel.class.getResource("/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		offsetX = cursorImage.getWidth() / 2 - HOLE_OFFSET;
		offsetY = cursorImage.getHeight() / 2 - HOLE_OFFSET;

		// 创建自定义光标
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0),
				"Custom Cursor");

		return customCursor;

	}
}
