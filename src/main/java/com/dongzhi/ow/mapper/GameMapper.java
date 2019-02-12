package com.dongzhi.ow.mapper;

import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.GameExample;
import java.util.List;

public interface GameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    List<Game> selectByExampleWithBLOBs(GameExample example);

    List<Game> selectByExample(GameExample example);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKeyWithBLOBs(Game record);

    int updateByPrimaryKey(Game record);
}