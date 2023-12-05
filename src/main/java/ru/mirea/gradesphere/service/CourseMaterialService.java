package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.model.CourseMaterial;
import ru.mirea.gradesphere.model.FileEntity;
import ru.mirea.gradesphere.repository.CourseMaterialRepository;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public CourseMaterialService(CourseMaterialRepository courseMaterialRepository, FileStorageService fileStorageService) {
        this.courseMaterialRepository = courseMaterialRepository;
        this.fileStorageService = fileStorageService;
    }

    public void uploadFile(Long courseMaterialId, MultipartFile file) {
        CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId)
                .orElseThrow(EntityNotFoundException::new);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileId(fileStorageService.storeFile(file, courseMaterial.getCourse().getId()));
        fileEntity.setCourseMaterial(courseMaterial);

        courseMaterial.getFiles().add(fileEntity);

        courseMaterialRepository.save(courseMaterial);
    }

    public void deleteCourseMaterial(Long courseMaterialId) {
        CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId)
                .orElseThrow(EntityNotFoundException::new);

        List<FileEntity> files = courseMaterial.getFiles();
        for (FileEntity fileEntity : files) {
            fileStorageService.deleteFile(fileEntity.getFileId(), courseMaterial.getCourse().getId());
        }

        courseMaterialRepository.delete(courseMaterial);
    }

    public void deleteFile(Long courseMaterialId, String fileId) {
        CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId)
                .orElseThrow(EntityNotFoundException::new);

        List<FileEntity> files = courseMaterial.getFiles();
        FileEntity fileToDelete = files.stream()
                .filter(fileEntity -> fileId.equals(fileEntity.getFileId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

        fileStorageService.deleteFile(fileToDelete.getFileId(), courseMaterial.getCourse().getId());
        files.remove(fileToDelete);

        courseMaterialRepository.save(courseMaterial);
    }

    public void createCourseMaterial(Course course) {
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setCourse(course);
        courseMaterialRepository.save(courseMaterial);
    }
}
