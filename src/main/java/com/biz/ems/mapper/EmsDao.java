package com.biz.ems.mapper;

import com.biz.ems.model.EmsVO;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface EmsDao {

    public List<EmsVO> selectAll();

    public EmsVO findById(long id);

    public int insert(EmsVO emsVO);

    public int update(EmsVO emsVO);

    @Delete("DELETE FROM tbl_ems WHERE id = #{id}")
    public int delete(long id);
}
