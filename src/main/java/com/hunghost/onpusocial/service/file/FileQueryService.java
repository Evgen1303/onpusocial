package com.hunghost.onpusocial.service.file;

import com.hunghost.onpusocial.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileQueryService {
    private FileRepository fileRepository;
    @Autowired
    public FileQueryService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getFilesByUserId(Long userId){


        return null;
    }
}
