package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Foot;
import com.dongzhi.ow.pojo.FootExample;
import java.util.List;

public interface FootMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Foot record);

    int insertSelective(Foot record);

    List<Foot> selectByExampleWithBLOBs(FootExample example);

    List<Foot> selectByExample(FootExample example);

    Foot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Foot record);

    int updateByPrimaryKeyWithBLOBs(Foot record);
}