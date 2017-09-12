package com.jim.util.sql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * @author jim.huang
 *
 */
public interface UserMapper {

    @Select("select * from users where name = #{name}")
    @ResultType(Map.class)
    List<Map<String, Object>> getUserByName(@Param("name") String name);

}
