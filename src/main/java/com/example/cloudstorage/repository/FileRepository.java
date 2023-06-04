package com.example.cloudstorage.repository;

import com.example.cloudstorage.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByOwner(String owner);

    @Modifying
    @Query("update FileEntity f set f.filename = :newName where f.filename = :filename and f.owner = :owner")
    void renameFile(@Param("filename") String filename, @Param("newName") String newFilename, @Param("owner") String owner);

    FileEntity findByFilenameAndOwner(String filename, String owner);

    void removeByFilenameAndOwner(String filename, String owner);
}