package chipjar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graphic extends JPanel {

	JFrame m_main_window;

	private final byte CHIP8_WIDTH = 64;
	private final byte CHIP8_HEIGHT = 32;
	
	byte m_window_scaling = 10;

	byte m_video_ram[];
	
    boolean m_redraw;
    
	public Graphic() {
		this.m_video_ram = new byte[CHIP8_WIDTH * CHIP8_HEIGHT];
		
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
        m_redraw = false;
	}
	
    protected void paintComponent(Graphics g) {
        for (int y = 0; y < CHIP8_HEIGHT; y++) {
            for (int x = 0; x < CHIP8_WIDTH; ++x) {
				if (this.m_video_ram[x + CHIP8_WIDTH * y] == 1)
				{
					g.setColor(Color.WHITE);
				}
				else
				{
					g.setColor(Color.BLACK);
				}

                g.fillRect(x * m_window_scaling, y * m_window_scaling, m_window_scaling, m_window_scaling);
            }
        }
    }
}
