package com.rhythmdiao.utils.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public final class ConfigUtil {
    private static PropertiesConfiguration propertiesConfiguration;

    static {
        try {
            propertiesConfiguration = new PropertiesConfiguration("launcher/src/main/resources/config.properties");
            propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static synchronized int getIntValue(final String key) {
        return propertiesConfiguration.getInt(key);
    }

    public static synchronized int getIntValue(final String key, final int defaultValue) {
        return propertiesConfiguration.getInt(key, defaultValue);
    }

    public static synchronized boolean getBooleanValue(final String key) {
        return propertiesConfiguration.getBoolean(key);
    }

    public static synchronized long getLongValue(final String key) {
        return propertiesConfiguration.getLong(key);
    }

    public static synchronized String getStringValue(final String key) {
        return propertiesConfiguration.getString(key);
    }

    public static synchronized String getStringValue(final String key, final String defaultValue) {
        return propertiesConfiguration.getString(key, defaultValue);
    }
}
