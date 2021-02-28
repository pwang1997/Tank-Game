package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest {

    @Test
    void test() {
        try {
//            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream(
//                    "images/tankL.gif"
//            ));
//            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream(
                    "images/tankL.gif"
            ));
            assertNotNull(image2);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
