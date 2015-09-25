import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.Random;

public class KMean {
	private BufferedImage base;
	private Color[] C;
	private int nb_clusters;
	private int maxIter;
	private int[][] L;

	public KMean(BufferedImage image, int clusters, int maxIter) {
		this.base = image;
		this.nb_clusters = clusters;
		this.maxIter = maxIter;
		this.C = new Color[this.nb_clusters];
		this.L = new int[this.base.getWidth()][this.base.getHeight()];
	}

	private void Init() {
		// C initialization (with random set)
		Random rng = new Random();
		for (int i = 0; i < this.nb_clusters; i++) {
			this.C[i] = new Color(this.base.getRGB(
					rng.nextInt(this.base.getWidth()),
					rng.nextInt(this.base.getHeight())));
		}

		// L initialization
		this.updateLabel();
	}

	private boolean updateLabel() {
		boolean changed = false;
		for (int x = 0; x < this.L.length; x++) {
			for (int y = 0; y < this.L[x].length; y++) {
				int cluster = this.getCluster(x, y);
				if (!changed) {
					changed = cluster != L[x][y];
				}
				this.L[x][y] = cluster;
			}
		}
		return changed;
	}

	private static double getDistance(Color c1, Color c2) {
		double r_delta = c1.getRed() - c2.getRed();
		double g_delta = c1.getGreen() - c2.getGreen();
		double b_delta = c1.getBlue() - c2.getBlue();
		return Math.sqrt(Math.pow(r_delta, 2) + Math.pow(g_delta, 2)
				+ Math.pow(b_delta, 2));
	}

	private int getCluster(int x, int y) {
		Color current = new Color(this.base.getRGB(x, y));
		double min = getDistance(current, this.C[0]);
		int cluster = 0;
		for (int i = 1; i < this.C.length; i++) {
			double res = getDistance(current, this.C[i]);
			if (res < min) {
				min = res;
				cluster = i;
			}
		}
		return cluster;
	}

	private void updateClusters() {
		BigInteger[] clusters_sums_r = new BigInteger[this.nb_clusters];
		BigInteger[] clusters_sums_g = new BigInteger[this.nb_clusters];
		BigInteger[] clusters_sums_b = new BigInteger[this.nb_clusters];
		long[] size = new long[this.nb_clusters];
		for (int x = 0; x < this.L.length; x++) {
			for (int y = 0; y < this.L[x].length; y++) {
				Color c = new Color(this.base.getRGB(x, y));
				clusters_sums_r[this.L[x][y]].add(BigInteger.valueOf(c.getRed()));
				clusters_sums_g[this.L[x][y]].add(BigInteger.valueOf(c.getGreen()));
				clusters_sums_b[this.L[x][y]].add(BigInteger.valueOf(c.getBlue()));
				size[this.L[x][y]] ++;
			}
		}
		
		for(int i = 0; i < this.C.length; i++){
			int r = clusters_sums_r[i].divide(BigInteger.valueOf(size[i])).intValue();
			int g = clusters_sums_g[i].divide(BigInteger.valueOf(size[i])).intValue();
			int b = clusters_sums_b[i].divide(BigInteger.valueOf(size[i])).intValue();
			this.C[i] = new Color(r,g,b);
		}
	}

	public void Compute() {
		boolean changed = false;
		int iter = 0;
		do {
			this.updateClusters();
			changed = this.updateLabel();
		} while (changed && iter < this.maxIter);
	}
	
	public Color[] getClusters(){
		return this.C;
	}
	
	public int[][] getLabels(){
		return this.L;
	}
}
