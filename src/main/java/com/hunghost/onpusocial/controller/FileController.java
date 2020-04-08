package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.service.file.FileLoadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;


import java.io.IOException;

@RestController
@RequestMapping("files")
public class FileController {
    private static final Logger log = LogManager.getLogger(FileController.class);
    private static final String DEFAULT_FILE_NAME = "images/name.png";

    private FileLoadService fileLoadService;
    private ServletContext servletContext;

    @Autowired
    public FileController(FileLoadService fileLoadService, ServletContext servletContext) {
        this.fileLoadService = fileLoadService;
        this.servletContext = servletContext;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file, @RequestParam String login) throws IOException {
        return fileLoadService.uploadFile(file,login);
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName, @RequestParam String login) throws IOException {
        return fileLoadService.downloadFile(fileName, login);
    }
}
