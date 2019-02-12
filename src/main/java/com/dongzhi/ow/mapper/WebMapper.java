package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.pojo.WebExample;
import java.util.List;

public interface WebMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Web record);

    int insertSelective(Web record);

    List<Web> selectByExample(WebExample example);

    Web selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Web record);

    int updateByPrimaryKey(Web record);
}