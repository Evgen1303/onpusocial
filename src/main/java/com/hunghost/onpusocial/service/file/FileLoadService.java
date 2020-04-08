package com.hunghost.onpusocial.service.file;


import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class FileLoadService {
    private static final String DIRECTORY = "C:\\ONPUsocialfiles";

    private static final Logger log = LogManager.getLogger(FileLoadService.class);
    private UserQueryService userQueryService;
    private FileCommandService fileCommandService;
    private ServletContext servletContext;

    @Autowired
    public FileLoadService(UserQueryService userQueryService, FileCommandService fileCommandService, ServletContext servletContext) {
        this.userQueryService = userQueryService;
        this.fileCommandService = fileCommandService;
        this.servletContext = servletContext;
    }

    public ResponseEntity downloadFile(String fileName, String login) throws IOException {
        File file = new File(DIRECTORY + "/"+login+"/" +fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        log.info("fileName: " + fileName);
        log.info("mediaType: " + mediaType);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

    public ResponseEntity uploadFile(MultipartFile multipartFile, String login) throws IOException {
        String fileName;

        File resourceFolder = new File(DIRECTORY);
        if (!resourceFolder.exists()) {
            if (resourceFolder.mkdir()) {
                log.info("Folder is created!");
            } else {
                log.info("Failed to create Folder!");
            }
        }
        File catalog = new File(DIRECTORY+"/"+login);
        if (!catalog.exists()) {
            if (catalog.mkdir()) {
                log.info("Directory is created!");
            } else {
                log.info("Failed to create directory!");
            }
        }
        User user = userQueryService.getUserByUsername(login);
        if(user == null){
            // throw exception
        }
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, multipartFile.getOriginalFilename());

        ServerFile serverFile = new ServerFile();
        serverFile.setFilename(login+multipartFile.getOriginalFilename());
        serverFile.setFiletype(mediaType.getType());
        serverFile.setFileowner(user);
        fileCommandService.saveFile(serverFile);

        File file = new File(catalog+"/"+serverFile.getFilename());
        multipartFile.transferTo(file);
        log.info(String.format("File name '%s' uploaded successfully.", multipartFile.getOriginalFilename()));



        return ResponseEntity.ok().build();
    }

}
