package chipjar;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graphics extends JPanel {
	byte m_video_ram[][];
	
	private final byte CHIP8_WIDTH = 64;
	private final byte CHIP8_HEIGHT = 32;
	
	byte m_window_scaling = 10;
	
	JFrame m_main_window;
	
	public Graphics() {
		this.m_video_ram = new byte[64][32];
		
		this.setPreferredSize(new Dimension(
				CHIP8_WIDTH * m_window_scaling, CHIP8_HEIGHT * m_window_scaling));
		this.setSize(new Dimension(
				CHIP8_WIDTH * m_window_scaling, CHIP8_HEIGHT * m_window_scaling));
		
		m_main_window = new JFrame("Chipjar");
		m_main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_main_window.add(this);
		m_main_window.pack();
		m_main_window.setVisible(true);
		m_main_window.setFocusable(true);
	}
}
