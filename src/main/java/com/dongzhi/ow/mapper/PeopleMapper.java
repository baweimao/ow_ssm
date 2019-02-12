package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.PeopleExample;
import java.util.List;

public interface PeopleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    List<People> selectByExample(PeopleExample example);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}