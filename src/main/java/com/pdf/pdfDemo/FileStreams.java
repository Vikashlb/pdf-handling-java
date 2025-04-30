package com.pdf.pdfDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreams {
    public static void main(String[] args) {
        FileStreams obj = new FileStreams();
        obj.inputStreams();
        obj.outputStreams();
    }
    public void inputStreams() {
        try(FileInputStream file = new FileInputStream("inputStream.txt")){
            int data;
            while((data = file.read()) != -1) {
                System.out.print((char) data);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void outputStreams(){
        String data = "Hello this is from Output Stream!!!!";
        try(FileOutputStream file = new FileOutputStream("outputStream.txt")){
            file.write(data.getBytes());
            System.out.println("File created - 'outputStream.txt' ");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
