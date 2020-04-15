package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.service.file.FileLoadService;
import com.hunghost.onpusocial.service.file.FileQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {
    private static final Logger log = LogManager.getLogger(FileController.class);
    private static final String DEFAULT_FILE_NAME = "images/name.png";

    private FileLoadService fileLoadService;
    private FileQueryService fileQueryService;

    @Autowired
    public FileController(FileLoadService fileLoadService, FileQueryService fileQueryService) {
        this.fileLoadService = fileLoadService;
        this.fileQueryService = fileQueryService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file, @RequestParam String login) throws IOException {
        return fileLoadService.uploadFile(file,login,false);
    }

    @PostMapping(value = "/upload/profilephoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileProfile(@RequestParam MultipartFile file, @RequestParam String login) throws IOException {
        return fileLoadService.uploadFile(file,login,true);
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName, @RequestParam String login) throws IOException {
        return fileLoadService.downloadFile(fileName, login);
    }

    @GetMapping("/downloadasresource")
    public ResponseEntity<Resource> downloadFileAsResource(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName, @RequestParam String login, HttpServletRequest request) throws IOException {
        return fileLoadService.downloadFileAsResource(fileName,login,request);
    }
    @GetMapping("/downloadasbyterow")
    public  @ResponseBody Blob downloadFileAsByte(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName, @RequestParam String login) throws IOException, SQLException {
        byte[] bytes = fileLoadService.downloadFileAsByte(fileName,login);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        return blob;
    }

    @GetMapping("/downloadasfile")
    public  @ResponseBody
    File downloadFileAsFile(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName, @RequestParam String login) throws IOException {
        return fileLoadService.downloadFileAsFile(fileName,login);
    }

    @GetMapping("/users")
    public List<ServerFile> getFilesByUsers() {
        return fileQueryService.getFilesByUsers();
    }
    @GetMapping("/users/{userId}")
    public List<ServerFile> getFilesByUserId(@PathVariable Long userId) {
        return fileQueryService.getFilesByUserId(userId);
    }
}
