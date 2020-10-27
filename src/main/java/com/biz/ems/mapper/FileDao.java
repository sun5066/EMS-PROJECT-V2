package com.biz.ems.mapper;

import com.biz.ems.model.FileVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileDao {
    @Select("SELECT * FROM tbl_files WHERE f_b_seq = #{id}")
    public List<FileVO> findByID(long id);

    public int insert(@Param("vo") FileVO imageVO, @Param("id") long id);

    public int insert_list(List<FileVO> imageVOList, long b_seq);

}
