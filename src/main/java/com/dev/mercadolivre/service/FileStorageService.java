package com.dev.mercadolivre.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {

    private final String path = "/tmp";

    public List<String> uploadFile(Integer id, MultipartFile[] file){
        List<String> result = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String pathImg = saveImage(id, multipartFile);
            result.add(pathImg);
        }
        return result;
    }

    private String saveImage(Integer id, MultipartFile file){
        if (file.isEmpty()) {
            throw new RuntimeException("Arquivo não pode ser vazio.");
        }
        String pathName = path+"/"+id+"_"+file.getOriginalFilename();
        Path destinationFile = Paths.get(pathName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.createDirectories(Paths.get(path));
            Files.copy(
                    inputStream,
                    destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
            return pathName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

}
