package com.biz.ems.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("fileServiceV1")
public class FileServiceImplV1 implements FileService {
    private final String rootFolder;

    public FileServiceImplV1() {
        this.rootFolder = "C:/bizWork/workSpace/upload";
    }

    @Override
    public String fileUp(MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename().isEmpty()) {
            return null;
        }

        File dir = new File(rootFolder);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String strUUID = UUID.randomUUID().toString();
        String saveFileName = strUUID + originalFilename;

        File saveFile = new File(rootFolder, saveFileName);
        try {
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFileName;
    }

    @Override
    public boolean fileDelete(String b_file) {
        boolean ret = false;
        File deleteFile = new File(rootFolder, b_file);
        if (deleteFile.exists()) {
            ret = deleteFile.delete();
        }
        return ret;
    }
}
