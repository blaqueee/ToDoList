package edu.personal.issuetracker.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    private static final String taskDir = "src/main/resources/static/";

    public static String writeFile(MultipartFile multipartFile, Long taskId, String username) {
        byte[] data = getBytesFromMultipartFile(multipartFile);
        String name = String.format("tasks_%s_%s.%s", username, taskId, getFileExtension(multipartFile));
        File file = new File(taskDir + name);
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static void deleteImageFile(String path) {
        try {
            Files.delete(Path.of(taskDir + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getBytesFromMultipartFile(MultipartFile multipartFile) {
        byte[] data = new byte[0];
        try {
            data = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
