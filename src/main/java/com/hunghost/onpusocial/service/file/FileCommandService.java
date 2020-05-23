package com.hunghost.onpusocial.service.file;

import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.repositories.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileCommandService {

    private FileRepository fileRepository;
    private static final Logger log = LogManager.getLogger(FileCommandService.class);

    @Autowired
    public FileCommandService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFile(ServerFile serverFile){
        if(serverFile.getFilename()!=null){
            fileRepository.save(serverFile);
        }

    }

}
