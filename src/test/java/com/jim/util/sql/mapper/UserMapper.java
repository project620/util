package com.jim.util.sql.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * @author jim.huang
 *
 */
public interface UserMapper {

    @Select("select * from ${tableName} where username = #{userName}")
    void getUser(Map<String, Object> params);

}
