package com.ChatBot.Actions;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class ValidationsUtil {
	
	
	
	private JFrame frame_work() {
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		return frame;
	}

	public static java.lang.Runnable LookandFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return null;
	}
}
