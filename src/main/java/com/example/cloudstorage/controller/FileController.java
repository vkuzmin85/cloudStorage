package com.example.cloudstorage.controller;

import com.example.cloudstorage.dto.FileDto;
import com.example.cloudstorage.entity.FileEntity;
import com.example.cloudstorage.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/list")
    public ResponseEntity<List<FileDto>> getAllFiles(@RequestHeader("auth-token") String authToken,
                                                     @RequestParam("limit") int limit) {
        return ResponseEntity.ok(fileService.getFiles(authToken, limit));
    }

    @PostMapping("file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename,
                                        @RequestBody MultipartFile file) throws IOException {
        fileService.uploadFile(authToken, filename, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/file")
    public ResponseEntity<?> renameFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename,
                                        @RequestBody Map<String, String> fileNameRequest) {
        fileService.renameFile(authToken, filename, fileNameRequest.get("filename"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename) {
        fileService.deleteFile(authToken, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("auth-token") String authToken,
                                               @RequestParam("filename") String filename) {
        FileEntity file = fileService.downloadFile(authToken, filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFiletype()))
                .header("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file.getContent());
    }
}