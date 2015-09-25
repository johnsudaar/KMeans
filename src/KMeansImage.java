import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class KMeansImage{
	public static void main(String[] args){
		FileChooser fc = new FileChooser();
		String path = fc.chooseFile();
		System.out.println("Init");
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		KMean k = new KMean(image,10,10);
		System.out.println("Compute");
		k.Compute();
		ImageViewer.viewImage(image);
	}
}
