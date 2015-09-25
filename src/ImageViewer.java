import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

class ImageViewer extends JComponent {
	private Image bg;
	public ImageViewer(Image bg) {
		this.bg = bg;
	}
	public void paintComponent(Graphics g) {
		g.drawImage(bg,0,0,null);
	}
	public static void viewImage(BufferedImage img) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(new ImageViewer(img));
		jf.setLocation(200,200);
		jf.setSize(img.getWidth()+100,img.getHeight()+100);
		jf.setVisible(true);
	}
}