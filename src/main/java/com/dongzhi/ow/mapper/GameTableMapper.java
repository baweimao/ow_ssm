package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.GameTable;
import com.dongzhi.ow.pojo.GameTableExample;
import java.util.List;

public interface GameTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameTable record);

    int insertSelective(GameTable record);

    List<GameTable> selectByExample(GameTableExample example);

    GameTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameTable record);

    int updateByPrimaryKey(GameTable record);
}