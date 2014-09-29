
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author clholla
 *
 */
public class ImgHandler {
	public static BufferedImage mapImage = null;
	public static BufferedImage crpImage = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// GoogleMaps API requires a 256 x 256 tile size. For custom applications, change to desired tile size including rectangular
		int chunkWidth = 256;
		int chunkHeight = 256;
		int i = 0;
		int j = 0;

		try {
			System.out.println("Starting....");
			
//			File srcImage = new File ("img/chernarus-plus-very-high-res.jpg");
			File srcImage = new File ("img/test.jpg");
			mapImage = ImageIO.read(srcImage);

		} catch (IOException e) {
			System.out.println("Error:");
            e.printStackTrace();
		}				// end try / catch

		System.out.println("Image is " + mapImage.getWidth() + " x " + mapImage.getHeight() + " pixels");
		System.out.println("Image is " + mapImage.getWidth()/chunkWidth + " x " + mapImage.getHeight()/chunkHeight + " chunks");

		// tilemaps for a zoom level are stored in their own numeric directory
		// tilemaps are [0..n] across and [0..m] down
		// URL is "http://<address>/<relative-path-on-server>/zoom/x/y.jpg'

		for (i = 0;i<(mapImage.getHeight()/chunkHeight);i++) {
			for (j = 0;j<(mapImage.getWidth()/chunkWidth);j++) {
				cropping((j*chunkWidth),(i*chunkHeight),chunkWidth,chunkHeight,i,j);
			}			// end for j
			System.out.println("Fininshed row "+ i);
		}				// end for i
		System.out.println("Are we done? "+ checkBoundary(mapImage.getWidth(),chunkWidth,mapImage.getHeight(),chunkHeight));
	}					// end main

	public static void cropping(int startX, int startY, int widthX, int heightY, int xDir, int yName){
		crpImage = mapImage.getSubimage(startX, startY, widthX, heightY);
		try {
			new File ("./"+xDir).mkdirs();
			ImageIO.write(crpImage,"jpg", new File(xDir+"/"+yName+".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}			// end try/catch
	} 					// end cropping


	public static boolean checkBoundary(int imgWidth, int imgHeight, int chunkWidth, int chunkHeight) {
		if (((imgWidth % chunkWidth)!=0 ) || ((imgHeight % chunkHeight)!=0 )) {
			return false;
		} else {
			return true;
		}
	}					// end checkBoundary



} 						// end class ImgHandler