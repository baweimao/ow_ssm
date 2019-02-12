package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Social;
import com.dongzhi.ow.pojo.SocialExample;
import java.util.List;

public interface SocialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Social record);

    int insertSelective(Social record);

    List<Social> selectByExample(SocialExample example);

    Social selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Social record);

    int updateByPrimaryKey(Social record);
}