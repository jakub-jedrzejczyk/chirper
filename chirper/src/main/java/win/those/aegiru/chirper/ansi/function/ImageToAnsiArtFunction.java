package win.those.aegiru.chirper.ansi.function;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * Converts raw image in stored in byte array to ANSI art stored in {@link String}.
 */
@Component
public class ImageToAnsiArtFunction implements BiFunction<byte[], Integer, String> {

    /**
     * Converts image to ANSI art.
     *
     * @param image raw image
     * @param width desired output width
     * @return image in ANS art
     */
    @Override
    @SneakyThrows
    public String apply(byte[] image, Integer width) {
        return convertImageToAnsiArt(image, width);
    }

    /**
     * @param imageBytes   raw image
     * @param consoleWidth console width
     * @return image in ANSI art
     * @throws IOException on IO error
     */
    private String convertImageToAnsiArt(byte[] imageBytes, int consoleWidth) throws IOException {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double ratio = (double) imageHeight / imageWidth;
        int consoleHeight = (int) (consoleWidth * ratio);

        BufferedImage resizedImage = new BufferedImage(consoleWidth, consoleHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.createGraphics().drawImage(image, 0, 0, consoleWidth, consoleHeight, null);

        StringBuilder ansiArt = new StringBuilder();

        for (int y = 0; y < consoleHeight; y++) {
            for (int x = 0; x < consoleWidth; x++) {
                ansiArt.append("\u001B[48;5;%dm  ".formatted(parseAnsiColorCode(new Color(resizedImage.getRGB(x, y)))));
            }
            ansiArt.append("\u001B[0m\n");
        }

        return ansiArt.toString();
    }

    /**
     * Parses {@link Color} to ANSI color code. ANSI color codes (16-231) are organized into a 6x6x6 color cube.
     *
     * @param color color
     * @return ansi color
     */
    private int parseAnsiColorCode(Color color) {
        int r = color.getRed() / 51; //Requires values in 0-5 range.
        int g = color.getGreen() / 51;
        int b = color.getBlue() / 51;

        return 16 + r * 36 + g * 6 + b;
    }
}
