package com.biz.ems.service;

import com.biz.ems.mapper.EmsDao;
import com.biz.ems.mapper.FileDao;
import com.biz.ems.model.EmsVO;
import com.biz.ems.model.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service("emsServiceV1")
public class EmsServiceImplV1 implements EmsService {

    @Autowired
    private EmsDao emsDao;

    @Autowired
    private NaverMailSendServiceV1 naverMailSendServiceV1;

    @Autowired
    private FileDao fileDao;

    @Autowired
    @Qualifier("fileServiceV1")
    private FileService fileService;

    @Override
    public List<EmsVO> selectAll() {
        return emsDao.selectAll();
    }

    @Override
    public EmsVO findById(long id) {
        EmsVO emsVO = emsDao.findById(id);
        List<FileVO> emsFileVOList = fileDao.findByID(id);
        emsVO.setEmsFileVOList(emsFileVOList);
        return emsVO;
    }

    @Override
    public int insert(EmsVO emsVO, MultipartFile files[]) {
        String file1Name = null;
        String file2Name = null;

        if (!files[0].getOriginalFilename().isEmpty()) {
            file1Name = fileService.fileUp(files[0]);
            emsVO.setS_file1(file1Name);
        }

        if (!files[1].getOriginalFilename().isEmpty()) {
            file2Name = fileService.fileUp(files[1]);
            emsVO.setS_file2(file2Name);
        }
        naverMailSendServiceV1.sendMail(emsVO);
        return emsDao.insert(emsVO);
    }

    @Override
    public int update(EmsVO emsVO) {
        return emsDao.update(emsVO);
    }

    @Override
    public int delete(long id) {
        EmsVO emsVO = emsDao.findById(id);
        String file1 = emsVO.getS_file1();
        String file2 = emsVO.getS_file2();

        if (file1 != null) {
            fileService.fileDelete(file1);
        }

        if (file2 != null) {
            fileService.fileDelete(file2);
        }

        fileService.fileDelete(emsVO.getS_file1());
        fileService.fileDelete(emsVO.getS_file2());
        return emsDao.delete(id);
    }
}
