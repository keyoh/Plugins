package ru.sbt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptedClassLoader extends ClassLoader {

    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] byteMe = Files.readAllBytes(Paths.get(dir + "/" + name.replace('.', '/').concat(".class")));
            for (int i = 0; i < byteMe.length; i++) {
                byteMe[i] = (byte) (byteMe[i] + key.length()/2);
            }
            return defineClass(name, byteMe, 0, byteMe.length);
        } catch (IOException e) {
            System.err.println("EncryptedClassLoader: IOException");
            return null;
        }
    }
}