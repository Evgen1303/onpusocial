package com.hunghost.onpusocial.service.file;

import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.FileRepository;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileQueryService {
    private FileRepository fileRepository;
    private UserQueryService userQueryService;

    @Autowired
    public FileQueryService(FileRepository fileRepository, UserQueryService userQueryService) {
        this.fileRepository = fileRepository;
        this.userQueryService = userQueryService;
    }

    public ServerFile getFilebyId(Long id){
        return fileRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<ServerFile> getFilesByUserId(Long userId){
        return fileRepository.findAllByFileowner(userQueryService.getUserById(userId));
    }
    public List<ServerFile> getFilesByUsers(){
        return fileRepository.findAll();
    }
}
