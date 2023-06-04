package com.example.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
@Entity
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    @Column(name = "filename")
    String filename;

    @Column(name = "filetype", nullable = false)
    String filetype;
    Long size;
    @Lob
    byte[] content;

    String owner;

    public FileEntity(String filename, String filetype, Long size, byte[] content, String owner) {
        this.filename = filename;
        this.filetype = filetype;
        this.size = size;
        this.content = content;
        this.owner = owner;
    }

}