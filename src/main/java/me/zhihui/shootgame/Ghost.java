package me.zhihui.shootgame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ghost {

	static Image ghost;
	static int ghostWidth = -10000;
	static int ghostHeight = -1000;

	public static Image getGhost(JPanel p) {
		if (ghost == null) {
			try {
				ghost = ImageIO.read(Ghost.class.getResource("/ghost.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ghostWidth = ghost.getWidth(p);
		ghostHeight = ghost.getHeight(p);
		return ghost;
	}

}
