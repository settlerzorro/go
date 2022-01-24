package com.goout.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:conf.yaml"})
@ConfigurationProperties(prefix = "conf")
public class Properties {
    @Value("${advertFolderPath}")
    private String advertFolderPath;

    public String getAdvertFolderPath() {
        return advertFolderPath;
    }

    public void setAdvertFolderPath(String advertFolderPath) {
        this.advertFolderPath = advertFolderPath;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "advertFilePath='" + advertFolderPath + '\'' +
                '}';
    }
}
