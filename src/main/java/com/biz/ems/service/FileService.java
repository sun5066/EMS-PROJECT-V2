package com.biz.ems.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String fileUp(MultipartFile multipartFile);

    public boolean fileDelete(String b_file);
}
