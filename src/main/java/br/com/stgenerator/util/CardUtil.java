package br.com.stgenerator.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("restriction")
public class CardUtil {

    public static byte[] getFotoDefault() throws IOException {
        Resource resource = new ClassPathResource("/indisponivel.jpg");
        InputStream input = resource.getInputStream();

        try {
            return IOUtils.toByteArray(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage Base64ToImage(String StringImage) throws IOException {
        String imageDataBytes = StringImage.substring(StringImage.indexOf(",") + 1);
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(imageDataBytes);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        return ImageIO.read(bis);
    }

    public static ModeloCarta getModeloCartaById(String id) {
        List<ModeloCarta> modelos = Arrays.asList(ModeloCarta.values());

        for (ModeloCarta modeloCarta : modelos) {
            if (id.equals(modeloCarta.name())) {
                return modeloCarta;
            }
        }
        return modelos.get(0);
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }

}
