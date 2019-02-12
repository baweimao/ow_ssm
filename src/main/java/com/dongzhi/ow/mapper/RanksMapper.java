package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.pojo.RanksExample;
import java.util.List;

public interface RanksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ranks record);

    int insertSelective(Ranks record);

    List<Ranks> selectByExample(RanksExample example);

    Ranks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ranks record);

    int updateByPrimaryKey(Ranks record);
}