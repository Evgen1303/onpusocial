package com.hunghost.onpusocial.service.file;

import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.MyFileNotFoundException;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.service.user.UserCommandService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;


@Service
public class FileLoadService {
    private static final String DIRECTORY = "C:\\ONPUsocialfiles";

    private static final Logger log = LogManager.getLogger(FileLoadService.class);
    private UserQueryService userQueryService;
    private FileCommandService fileCommandService;
    private ServletContext servletContext;
    private UserCommandService userCommandService;


    @Autowired
    public FileLoadService(UserQueryService userQueryService, FileCommandService fileCommandService, ServletContext servletContext, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.fileCommandService = fileCommandService;
        this.servletContext = servletContext;
        this.userCommandService = userCommandService;
    }

    public ResponseEntity downloadFile(String fileName, String login) throws IOException {
        File file = new File(DIRECTORY + "/"+login+"/" +fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            if(!resource.exists()){
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        log.info("fileName: " + fileName);
        log.info("mediaType: " + mediaType);
        ResponseEntity responseEntity = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
        return responseEntity;
    }
    public byte[] downloadFileAsByte(String fileName, String login) throws IOException {
        File file = new File(DIRECTORY + "/"+login+"/" +fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        if(!resource.exists()){
            throw new MyFileNotFoundException("File not found " + fileName);
        }
        byte[] bimage = new byte[resource.getInputStream().available()];
        return bimage;
    }
    public ResponseEntity<Resource> downloadFileAsResource(String fileName, String login, HttpServletRequest request) throws IOException {
        File file = new File(DIRECTORY + "/"+login+"/" +fileName);
        Path filePath = file.toPath();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            throw new MyFileNotFoundException("File not found " + fileName);
        }
    }

    public ResponseEntity uploadFile(MultipartFile multipartFile, String login, Boolean profilephoto) throws IOException {
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
            throw new ResourceNotFoundException("User not found");
        }
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, multipartFile.getOriginalFilename());

        ServerFile serverFile = new ServerFile();
        serverFile.setFilename(login+multipartFile.getOriginalFilename());
        serverFile.setFiletype(mediaType.getType());
        serverFile.setFileowner(user);
        fileCommandService.saveFile(serverFile);
        serverFile.setFilename(serverFile.getId()+serverFile.getFilename());
        fileCommandService.saveFile(serverFile);
        File file = new File(catalog+"/"+serverFile.getFilename());
        multipartFile.transferTo(file);
        log.info(String.format("Hello, File name '%s' uploaded successfully.", multipartFile.getOriginalFilename()));

        if(profilephoto == true){
            user = userQueryService.getUserByUsername(login);
            user.setPhoto(serverFile.getFilename());
            userCommandService.updateUser(user);
            log.info("Change user photo:"+user.getUsername()+" at "+user.getPhoto());
        }
        return ResponseEntity.ok().build();
    }
}
