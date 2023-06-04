package com.example.cloudstorage;

import com.example.cloudstorage.dto.FileDto;
import com.example.cloudstorage.entity.FileEntity;
import com.example.cloudstorage.jwt.JwtProvider;
import com.example.cloudstorage.repository.FileRepository;
import com.example.cloudstorage.service.FileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class FileServiceTests {
    @InjectMocks
    FileServiceImpl fileService;
    @Mock
    FileRepository fileRepository;
    @Mock
    JwtProvider jwtProvider;
    private String authToken = "token";
    private  String bearerToken = "Bearer " + authToken;
    String fileName = "file name";

    private String owner = "user";
    private FileEntity file = new FileEntity();
    private List<FileEntity> fileList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getFileTest() {
        file.setFilename(fileName);
        fileList.add(file);

        Mockito.when(jwtProvider.getUsernameFromToken(bearerToken.substring(7))).thenReturn(owner);
        Mockito.when(fileRepository.findAllByOwner(owner)).thenReturn(fileList);

        List<FileDto> responseList = fileService.getFiles(bearerToken, 1);
        assertEquals(responseList.get(0).getFilename(), file.getFilename());
    }

    @Test
    public void fileRenameTest() {
        String newFileName = "new file name";
        Mockito.when(jwtProvider.getUsernameFromToken(bearerToken.substring(7))).thenReturn(owner);
        fileService.renameFile(bearerToken, fileName, newFileName);
        verify(fileRepository, times(1)).renameFile(fileName, newFileName, owner);
    }

    @Test
    public void saveFileInRepositoryTest() throws IOException {
        byte[] content = "content".getBytes();
        file.setFilename(fileName);
        file.setContent(content);
        file.setSize((long) content.length);
        fileService.uploadFile(bearerToken, fileName, new MockMultipartFile(fileName, content));
        verify(fileRepository, times(1)).save(file);
    }

    @Test
    public void testGetFileFromRepository() {
        file.setFilename(fileName);
        Mockito.when(jwtProvider.getUsernameFromToken(bearerToken.substring(7))).thenReturn(owner);
        Mockito.when(fileRepository.findByFilenameAndOwner(fileName, owner)).thenReturn(file);
        FileEntity actualFile = fileService.downloadFile(bearerToken, fileName);
        Assertions.assertEquals(file.getFilename(), actualFile.getFilename());
    }

}