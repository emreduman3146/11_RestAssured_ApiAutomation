package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.Herokuapp_Testing.d_putRequests.request02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesReadWrite
{
    public static Properties properties;//Class JDK'dan gelir
    static String path;


    static
    {
        path = "src/test/java/com/beyondTheWisdom/API_Automation/restAssured_ogreniyorum/Ders/Herokuapp_Testing/d_putRequests/request02/configuration.properties";
        try
        {
            loadFileIntoProperties(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void loadFileIntoProperties(String path) throws IOException {
        try(FileInputStream fis = new FileInputStream(path))
        {
            properties = new Properties();
            properties.load(fis);
        }
    }




    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }





    public static void setProperty(String key, Object value) throws IOException {
        properties.setProperty(key, String.valueOf(value));
        flush();
    }


    public static void flush() throws IOException
    {
        try (final OutputStream outputstream = new FileOutputStream(path);)
        {
            properties.store(outputstream,"File Updated");
        }
    }




}
