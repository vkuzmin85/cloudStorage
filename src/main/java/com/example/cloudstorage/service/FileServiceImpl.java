package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.FileDto;
import com.example.cloudstorage.entity.FileEntity;
import com.example.cloudstorage.jwt.JwtProvider;
import com.example.cloudstorage.jwt.JwtTokenFilter;
import com.example.cloudstorage.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository storageRepository;
    private final JwtProvider jwtProvider;

    public List<FileDto> getFiles(String authToken, int limit) {
        String user = getUserByToken(authToken);
        log.info("Get all files for " + user);
        List<FileEntity> fileList = storageRepository.findAllByOwner(user);
        return fileList.stream().map(fd -> new FileDto(fd.getFilename(), fd.getSize())).limit(limit).collect(Collectors.toList());
    }

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException {
        String user = getUserByToken(authToken);
        log.info("Uploading file by " + user);
        storageRepository.save(new FileEntity(filename, file.getContentType(), file.getSize(), file.getBytes(), user));
    }

    public void deleteFile(String authToken, String filename) {
        String user = getUserByToken(authToken);
        log.info("Deleting file by " + user);
        storageRepository.removeByFilenameAndOwner(filename, user);
    }

    public FileEntity downloadFile(String authToken, String filename) {
        String user = getUserByToken(authToken);
        log.info("Downloading files by " + user);
        return storageRepository.findByFilenameAndOwner(filename, user);
    }

    public void renameFile(String authToken, String filename, String newFilename) {
        String user = getUserByToken(authToken);
        log.info("Renaming files by " + user);
        storageRepository.renameFile(filename, newFilename, user);
    }

    private String getUserByToken(String authToken) {
        return jwtProvider.getUsernameFromToken(authToken.substring(JwtTokenFilter.BEARER_SIZE));
    }
}