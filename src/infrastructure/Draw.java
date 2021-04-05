package infrastructure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Draw extends JPanel implements Runnable{
	private Thread t;
	private Graphics2D g;
	private boolean running = true;

// ======================================== CONSTRUCTOR ========================================
	
	public Draw() {
		t = new Thread(this);
		t.start();
	}

// ======================================== RUN-METHOD =========================================
	
	@Override
	public void run() {
		while(running) {
			super.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
// ======================================== METHODS ============================================

// ======================================== GET/SET METHODS ====================================
	
// ======================================== PAINT-METHODS ======================================

	@Override
	public void paint(Graphics graphics) {
		g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, (int)screensize.getWidth(), (int)screensize.getHeight());
		Main.sc.paint(g);
	}
}
