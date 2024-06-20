package me.zhihui.shootgame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		initFrame(frame, 1440, 800);

		ShootPanel sp = new ShootPanel();
		frame.add(sp);
		frame.setVisible(true);

		sp.action();

	}

	static private void initFrame(JFrame frame, int width, int height) {
		// 获取一个与系统相关工具类对象
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// 获取屏幕的分辨率
		Dimension d = toolkit.getScreenSize();
		int x = (int) d.getWidth();
		int y = (int) d.getHeight();
		frame.setBounds(0, 0, width, height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
