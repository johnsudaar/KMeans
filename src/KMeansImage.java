import java.awt.Color;
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
		KMean k = new KMean(image,4,10);
		System.out.println("Compute");
		k.Compute();
		
		System.out.println("Generate Image");
		int labels[][] = k.getLabels();
		Color[] clusters = k.getClusters();
		BufferedImage next = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y< image.getHeight(); y++) {
				next.setRGB(x, y, clusters[labels[x][y]].getRGB());
			}
		}
		ImageViewer.viewImage(next);
	}
}
