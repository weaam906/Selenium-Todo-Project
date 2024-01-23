/* --------------------------------------------------------- -
 - Date: 23rd.Dec.23                                         -
 - Author: We'am Othman                                      -
 - Desc: This file is to load the properties from property   -
 - file store them in a file and read them from input stream -
 - --------------------------------------------------------- */
package com.qacart.todo.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties loadProperties(String filePath){
        File file = new File(filePath); //1-Create file to read the path and store data inside
        try {
            InputStream inputStream = new FileInputStream(file); //2-Create inputStream instance to read data from file
            Properties properties = new Properties(); //3-Create instance from Properties class
            properties.load(inputStream);//4-Load(read) the data from the file through inputStream
            inputStream.close();//5-Close the stream
            return properties;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File isn't found");
        }
        catch (IOException e) {
            throw new RuntimeException("Error while loading property");
        }
    }
}
