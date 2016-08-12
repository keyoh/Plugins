package ru.sbt;

import java.net.MalformedURLException;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Plugin) new ClassLoaderForBrowser(pluginRootDirectory, pluginName, pluginClassName).loadClass(pluginClassName).newInstance();
    }
}