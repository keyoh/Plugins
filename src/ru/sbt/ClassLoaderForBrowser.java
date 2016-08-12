package ru.sbt;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderForBrowser extends URLClassLoader {
    private final String pluginName;
    private final String pluginClassName;

    public ClassLoaderForBrowser(String pluginRootDirectory, String pluginName, String pluginClassName) throws MalformedURLException {
        super(new URL[]{new URL("file:" + pluginRootDirectory + "/" + pluginName)});

        this.pluginName = pluginName;
        this.pluginClassName = pluginClassName;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.contains("Plugin") || name.startsWith("java")) {
            return super.loadClass(name);
        }
        return findClass(name);
    }
}
