package net.eemo.qi.reflection;

import net.eemo.qi.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * class utils
 *
 * @author liyu0
 */
public class ClassUtils {

    private ClassUtils() {
    }

    public final static String CLASS_FILE_SUFFIX = ".class";

    public final static String ROOT_CLASS_METHOD_NAME = "main";

    public final static String URL_PROTOCOL_JAR = "jar";

    public final static String URL_PROTOCOL_FILE = "file";

    /**
     * Trying find root class if the application started by main
     *
     * @return root class if finding or current class
     */
    public static Class<?> determineMainClass() {
        Exception exception = new Exception();
        try {
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                if (ROOT_CLASS_METHOD_NAME.equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ClassUtils.class;
    }

    /**
     * Making specified files convert to standard reference name. ex: java/util/Arrays.class -> java.util.Arrays
     *
     * @param files set of class files
     * @return the class object that the files represent
     */
    public static List<Class<?>> convertClassFileToClassObject(List<File> files) {
        files = files.stream()
                .filter(file -> file.getName().endsWith(CLASS_FILE_SUFFIX))
                .collect(Collectors.toList());
        return null;
    }

    public Set<Class<?>> getAllClass() {
        Class<?> clazz = determineMainClass();
        Set<Class<?>> result = new HashSet<>();
        try {
            Enumeration<URL> enumeration = clazz.getClassLoader().getResources(clazz.getPackage().getName());
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                if (URL_PROTOCOL_JAR.equals(url.getProtocol())) {
                    result.addAll(getAllClassInJar(getJarFile(url)));
                } else if (URL_PROTOCOL_FILE.equals(url.getProtocol())) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Class<?>> getAllClassInJar(JarFile jarFile) {
        Set<Class<?>> result = new HashSet<>();
        if (jarFile != null) {
            Enumeration<JarEntry> entryEnumeration = jarFile.entries();
            while (entryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = entryEnumeration.nextElement();
                String name = jarEntry.getName();
                name = name.startsWith("/") ? name.substring(1) : name;
                if (name.endsWith(CLASS_FILE_SUFFIX)) {
                    name = name.replaceAll("/", ".");
                    Class<?> clazz = loadClass(name);
                    if (clazz != null) {
                        result.add(clazz);
                    }
                }
            }
        }
        return result;
    }

    public Set<Class<?>> getAllClassInFile(File file) {
        Set<File> files = FileUtils.getAllFileInPath(file, f -> f.getName().endsWith(".class"));
        Set<Class<?>> result = new HashSet<>();
        return files.stream()
                .map()
    }

    public static Class<?> loadClass(String classFullName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(classFullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JarFile getJarFile(URL url) {
        if (URL_PROTOCOL_JAR.equals(url.getProtocol())) {
            try {
                return ((JarURLConnection) url.getContent()).getJarFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static File getFile(URL url) {
        if (URL_PROTOCOL_FILE.equals(url.getProtocol())) {
            return new File(url.getFile());
        }
        return null;
    }

    public String convertToClassName(String path) {
        if(path.endsWith(CLASS_FILE_SUFFIX)){
            path = path.startsWith("/") ? path.substring(1) : path;
        }

    }

}
