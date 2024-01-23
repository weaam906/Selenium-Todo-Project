/* ------------------------------------------------------- -
 - Date: 23rd.Dec.23                                       -
 - Author: We'am Othman                                    -
 - Desc: This class is to configure the properties from    -
 -       Prop file and reuse them across the whole project -
 - ------------------------------------------------------- - */
package com.qacart.todo.utils;

import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;
    private ConfigUtils() {
        String environment = System.getProperty("env","PRODUCTION");

        switch (environment) {
            case "PRODUCTION" -> properties = PropertiesUtils
                    .loadProperties("src/test/java/com/qacart/todo/config/production.properties");
            case "LOCAL" -> properties = PropertiesUtils
                    .loadProperties("");
            default -> throw new RuntimeException("Couldn't find this environment,please check it again");
        }
    }

    /* ----------------------------------------------------------------------------------- -
     - This method is to check if there's instance from this class or not                  -
     - If found then return it, if not then create new instance                            -
     - this method is static to be used directly without initializing instance every time. -
     - ----------------------------------------------------------------------------------- */
    public static ConfigUtils getInstance(){
        if(configUtils==null)
            configUtils=new ConfigUtils();
        return configUtils;
    }
    public String getBaseUrl(){
        String prop=properties.getProperty("baseUrl");
        if(prop !=null) return prop;
        throw new RuntimeException("Couldn't find the base url in the properties file");
    }
    public String getEmail(){
        String prop=properties.getProperty("email");
        if(prop !=null) return prop;
        throw new RuntimeException("Couldn't find the email in the properties file");
    }
    public String getPassword(){
        String prop=properties.getProperty("password");
        if(prop !=null) return prop;
        throw new RuntimeException("Couldn't find the password in the properties file");
    }
}