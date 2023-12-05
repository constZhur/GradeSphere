package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.gradesphere.exception.EntityNotFoundException;
import ru.mirea.gradesphere.service.CourseMaterialService;

@RestController
@RequestMapping("/course-materials")
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @Autowired
    public CourseMaterialController(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    @PostMapping("/{courseMaterialId}/upload")
    public String uploadFile(
            @PathVariable(name = "courseMaterialId") Long courseMaterialId,
            @RequestParam("file") MultipartFile file) {
        courseMaterialService.uploadFile(courseMaterialId, file);
        return "File uploaded successfully";
    }

    @DeleteMapping("/{courseMaterialId}/delete")
    public String deleteCourseMaterial(
            @PathVariable(name = "courseMaterialId") Long courseMaterialId) {
        courseMaterialService.deleteCourseMaterial(courseMaterialId);
        return "Course material deleted successfully";
    }

    @DeleteMapping("/{courseMaterialId}/delete-file")
    public String deleteFile(
            @PathVariable(name = "courseMaterialId") Long courseMaterialId,
            @RequestParam(name = "fileId") String fileId) {
        try {
            courseMaterialService.deleteFile(courseMaterialId, fileId);
            return "File deleted successfully";
        } catch (EntityNotFoundException e) {
            return "File not found";
        }
    }
}