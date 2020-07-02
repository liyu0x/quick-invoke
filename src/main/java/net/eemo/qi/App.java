package net.eemo.qi;

import net.eemo.qi.reflection.ClassUtils;
import net.eemo.qi.util.FileUtils;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = ClassUtils.determineMainClass();
        List<File> files = FileUtils.getAllFileInClassPath(clazz, file -> file.getName().endsWith(".class"));
    }

}