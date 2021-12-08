package Model.HandlingImages_Multipart;

import org.apache.commons.fileupload.FileItem;
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

    private static void convertToJPEG( String fileName , String path , FileItem imageFile ) throws Exception {

        String fileOldName = imageFile.getName();
        imageFile.write( new File( path  + fileName + fileOldName ) );
        FileInputStream fileInputStream = new FileInputStream( path  + fileName + fileOldName );
        FileOutputStream fileOutputStream = new FileOutputStream( path  + fileName + "converted.jpeg");

        BufferedImage bufferedImage = ImageIO.read( fileInputStream );

        boolean result = ImageIO.write( bufferedImage , "jpeg" , fileOutputStream );

        fileInputStream.close();
        fileOutputStream.close();
        System.out.println("first");
        File file = new File( path + fileName + fileOldName );
        System.out.println(file.delete());

        CompressJPEG( fileName , path );

        System.out.println("second");
        File file1 = new File( path + fileName + "converted.jpeg" );
        System.out.println(file1.delete());

    }

    private static void CompressJPEG( String fileName , String path ) throws IOException {

        FileInputStream fileInputStream = new FileInputStream( path + fileName + "converted.jpeg" );
        FileOutputStream fileOutputStream = new FileOutputStream( path + fileName + ".jpeg");

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

        FileUtils.copyFile( new File( path + fileName + ".jpeg" ) , new File( "D:\\project\\2nd project servlet\\src\\main\\webapp\\Resources\\Images\\EducationalPostImages\\" + fileName + ".jpeg") );

        fileInputStream.close();
        fileOutputStream.close();
        imageOutputStream.close();
        imageWriter.dispose();


    }

    public static Thread convertCompressJPEG( String fileName , String path , FileItem imageFile ){

        Runnable runnable = () -> {

            try {

                convertToJPEG( fileName , path , imageFile);
                
            } catch (Exception e) {

                Thread thread = convertCompressJPEG( fileName , path , imageFile );
                thread.start();

            }

        };

        return new Thread( runnable );

    }

}
