import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class KMeansImage extends Component {
	public static void main(String[] args){
		FileChooser fc = new FileChooser();
		String path = fc.chooseFile();
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		ImageViewer.viewImage(image);
	}
}
