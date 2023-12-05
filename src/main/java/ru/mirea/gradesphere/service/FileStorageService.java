package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class FileStorageService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    public String storeFile(MultipartFile file, Long courseId) {
        try {
            String courseDirectory = fileUploadPath + File.separator + "course_" + courseId;
            createDirectoryIfNotExists(courseDirectory);

            String fileName = generateFileName(file.getOriginalFilename());
            String filePath = courseDirectory + File.separator + fileName;

            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filePath));

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public void deleteFile(String fileName, Long courseId) {
        String filePath = fileUploadPath + File.separator + "course_" + courseId + File.separator + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            if (!file.delete()) {
                throw new RuntimeException("Failed to delete file");
            }
        }
    }

    public List<String> getAllFiles(Long courseId) {
        String courseDirectory = fileUploadPath + File.separator + "course_" + courseId;
        File directory = new File(courseDirectory);

        if (directory.exists() && directory.isDirectory()) {
            //return Arrays.asList(directory.list());
            return Arrays.asList(Objects.requireNonNull(directory.list()));
        } else {
            return Collections.emptyList();
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Failed to create directory");
            }
        }
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }
}
