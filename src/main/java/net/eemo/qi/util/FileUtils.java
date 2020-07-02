package net.eemo.qi.util;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {

    private FileUtils() {
    }

    public interface Filter {
        boolean filter(File file);
    }

    public static Set<File> getAllFileInPath(File root, Filter filter) {
        Set<File> files = new HashSet<>();
        if (root.exists()) {
            if (root.isDirectory()) {
                List<File> fileList = directoryConvertChildFiles(root);
                Queue<File> fileQueue = new LinkedList<>(fileList);
                while (!fileQueue.isEmpty()) {
                    File file = fileQueue.poll();
                    if (!file.isDirectory()) {
                        files.add(file);
                    } else {
                        fileQueue.addAll(directoryConvertChildFiles(file));
                    }
                }
            } else {
                files.add(root);
            }
        }
        if (filter != null) {
            files = files.stream()
                    .filter(filter::filter)
                    .collect(Collectors.toSet());
        }
        return files;
    }

    private static List<File> directoryConvertChildFiles(File file) {
        List<File> result = new ArrayList<>();
        if (!file.isDirectory()) {
            result.add(file);
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                result.addAll(Arrays.asList(files));
            }
        }
        return result;
    }

}
