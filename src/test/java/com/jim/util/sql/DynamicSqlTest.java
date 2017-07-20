package com.jim.util.sql;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jim.util.sql.mapper.UserMapper;

/**
 * @author jim.huang
 *
 */
public class DynamicSqlTest {

    static {
        MapperRegister.registe(UserMapper.class.getPackage().getName());
    }

    @Test
    public void testMap() {
        final String statementId = UserMapper.class.getName() + "." + "getUser";
        final Map<String, Object> params = new HashMap<>();
        params.put("tableName", "hello");
        params.put("userName", "world");
        final DynamicSql dynamicSql = new DynamicSql(statementId, params);
        final String sql = dynamicSql.getSql();
        System.out.println(sql);
    }

}
