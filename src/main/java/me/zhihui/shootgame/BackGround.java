package me.zhihui.shootgame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackGround {

	static Image backGround;

	public static Image getBackGround(JPanel p) {
		if (backGround == null) {
			try {
				backGround = ImageIO.read(BackGround.class.getResource("/background.png"))
						.getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return backGround;
	}

}
