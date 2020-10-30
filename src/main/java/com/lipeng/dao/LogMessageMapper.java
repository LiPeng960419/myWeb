package com.lipeng.dao;

import com.lipeng.domain.LogMessage;
import com.lipeng.domain.LogMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMessageMapper {

    long countByExample(LogMessageExample example);

    int deleteByExample(LogMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogMessage record);

    int insertSelective(LogMessage record);

    List<LogMessage> selectByExample(LogMessageExample example);

    LogMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogMessage record,
            @Param("example") LogMessageExample example);

    int updateByExample(@Param("record") LogMessage record,
            @Param("example") LogMessageExample example);

    int updateByPrimaryKeySelective(LogMessage record);

    int updateByPrimaryKey(LogMessage record);

    int batchInsertSelective(List<LogMessage> list);

}