package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Live;
import com.dongzhi.ow.pojo.LiveExample;
import java.util.List;

public interface LiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Live record);

    int insertSelective(Live record);

    List<Live> selectByExample(LiveExample example);

    Live selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Live record);

    int updateByPrimaryKey(Live record);
}