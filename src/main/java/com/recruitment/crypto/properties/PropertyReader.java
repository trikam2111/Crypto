package com.recruitment.crypto.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private String propertiesName;

    public PropertyReader(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String readProperty(String propertyName) {
        String property = "";
        try(InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propertiesName)) {
            Properties properties = new Properties();

            properties.load(inputStream);

            property = properties.getProperty(propertyName);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return property;
    }
}
