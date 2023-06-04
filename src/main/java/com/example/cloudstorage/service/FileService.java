package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.FileDto;
import com.example.cloudstorage.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    public List<FileDto> getFiles(String authToken, int limit);

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException;

    public void deleteFile(String authToken, String filename);

    public FileEntity downloadFile(String authToken, String filename);

    public void renameFile(String authToken, String filename, String newFilename);
}
