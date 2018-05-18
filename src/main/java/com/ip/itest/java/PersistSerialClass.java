package com.ip.itest.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PersistSerialClass {

    public static void main(String [] args) {
        String filename = "time.ser";

        if(args.length > 0){
            filename = args[0];
        }
		
        PersistSerialClass time = new PersistSerialClass();
        ObjectOutputStream out = null;

        try{
        	FileOutputStream  fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(time);
            out.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
     }
 }