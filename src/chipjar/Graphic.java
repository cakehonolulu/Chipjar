package chipjar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graphic extends JPanel {
	byte m_video_ram[];
	
	private final byte CHIP8_WIDTH = 64;
	private final byte CHIP8_HEIGHT = 32;
	
	byte m_window_scaling = 10;
	
	JFrame m_main_window;
	private Color foreground;

    private Color background;
    
    boolean m_redraw;
    
	public Graphic() {
		this.m_video_ram = new byte[64 * 32];
		
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
		this.foreground = Color.WHITE;
        this.background = Color.BLACK;
        m_redraw = false;
	}
	
    private void blit(Graphics g) {
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 64; ++x) {
                g.setColor(this.m_video_ram[(y * 64) + x] == 0 ? background : foreground);
                g.fillRect(x * m_window_scaling, y * m_window_scaling, m_window_scaling, m_window_scaling);
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(background);
        g.fillRect(0, 0, CHIP8_WIDTH, CHIP8_HEIGHT);

        blit(g);

        g.dispose();
    }
}
