import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
/**
 * @author clholla
 *
 */
public class ImgLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedImage mapImage = null;
		
		try {
			System.out.println("Starting....");
			
			long startTime = System.nanoTime();
			
			File srcImage = new File ("img/chernarus-plus-very-high-res.jpg");
			mapImage = ImageIO.read(srcImage);


			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			
			System.out.println("Read to mapImage in " + duration + " ms"); 

		} catch (IOException e) {
			System.out.println("Error:");
            e.printStackTrace();
		}

		System.out.println("Image is " + mapImage.getWidth() + "x" + mapImage.getHeight() + " pixels");
	}
}