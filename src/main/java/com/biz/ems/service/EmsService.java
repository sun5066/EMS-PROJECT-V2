package com.biz.ems.service;

import com.biz.ems.model.EmsVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface EmsService {
    public List<EmsVO> selectAll();

    public EmsVO findById(long id);

    public int insert(EmsVO emsVO, MultipartFile file[]);

    public int update(EmsVO emsVO);

    public int delete(long id);
}
