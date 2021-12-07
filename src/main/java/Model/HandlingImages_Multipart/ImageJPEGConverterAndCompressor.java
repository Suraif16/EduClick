package Model.HandlingImages_Multipart;

import org.apache.commons.io.FileUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageJPEGConverterAndCompressor {

    public static void convertToJPEG(String fileName , String path , String fileNameNew) throws IOException {

        FileInputStream fileInputStream = new FileInputStream( path + "Image\\" + fileName );
        FileOutputStream fileOutputStream = new FileOutputStream( path + "Image\\" + fileNameNew + ".jpeg");

        BufferedImage bufferedImage = ImageIO.read( fileInputStream );

        boolean result = ImageIO.write( bufferedImage , "jpeg" , fileOutputStream );

        fileInputStream.close();
        fileOutputStream.close();
        System.out.println("first");
        File file = new File( path + "Image\\" + fileName );
        System.out.println(file.delete());

        CompressJPEG( fileNameNew , path );

        System.out.println("second");
        File file1 = new File( path + "Image\\" + fileNameNew + ".jpeg" );
        System.out.println(file1.delete());

    }

    public static void CompressJPEG(String fileName , String path ) throws IOException {

        FileInputStream fileInputStream = new FileInputStream( path + "Image\\" + fileName + ".jpeg" );
        FileOutputStream fileOutputStream = new FileOutputStream( path + "Image\\" + fileName + "Compressed" + ".jpeg");

        float quality = 0.5f;

        BufferedImage bufferedImage = ImageIO.read( fileInputStream );

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName( "jpeg" );

        if( !writers.hasNext() )throw new IllegalStateException( "No writers found");

        ImageWriter imageWriter = ( ImageWriter ) writers.next();

        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream( fileOutputStream );
        imageWriter.setOutput( imageOutputStream );

        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

        imageWriteParam.setCompressionMode( ImageWriteParam.MODE_EXPLICIT );
        imageWriteParam.setCompressionQuality( quality );

        imageWriter.write( null , new IIOImage( bufferedImage , null , null ) , imageWriteParam );

        FileUtils.copyFile( new File( path + "Image\\" + fileName + "Compressed" + ".jpeg" ) , new File( "D:\\project\\2nd project servlet\\src\\main\\webapp\\Image\\" + fileName + "Compressed" + ".jpeg") );

        fileInputStream.close();
        fileOutputStream.close();
        imageOutputStream.close();
        imageWriter.dispose();


    }

}
