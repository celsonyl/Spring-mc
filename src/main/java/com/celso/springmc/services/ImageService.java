package com.celso.springmc.services;

import com.celso.springmc.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if(!"png".equals(extension) && !"jpg".equals(extension)){
            throw new FileException("Somente Imagens PNG e JPG são perimitidas!");
        }

        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if("png".equals(image)){
                image = pngToJpg(image);
            }

            return image;
        }catch (IOException e){
            throw new FileException("Erro ao ler arquivo");
        }
    }

    private BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image,0,0, Color.WHITE ,null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image,String extension){
        try{
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            ImageIO.write(image,extension,byteArrayInputStream);
            return new ByteArrayInputStream(byteArrayInputStream.toByteArray());
        } catch (IOException e) {
           throw new FileException("Erro ao ler arquivo");
        }
    }
}
