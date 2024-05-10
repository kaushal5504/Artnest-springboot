package com.artnest.artnest.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileUtil {

    public static byte[] compressFile(byte[] data){

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.setLevel(Deflater.DEFAULT_COMPRESSION);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        while (!deflater.finished()){
          int size =  deflater.deflate(buffer);
          outputStream.write(buffer, 0, size);
        }

        try {
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        byte[] outputData = outputStream.toByteArray();
        System.out.println(data.length/1024+ " KB");
        System.out.println(outputData.length/1024+" KB");

        return outputData;
    }


    public static  byte[] decompressFile(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()){
                int size= inflater.inflate(buffer);
                stream.write(buffer, 0, size);
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return  stream.toByteArray();
    }
}