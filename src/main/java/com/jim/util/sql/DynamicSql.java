package com.jim.util.sql;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

/**
 * @author jim.huang
 *
 */
public class DynamicSql {

    private final BoundSql boundSql;
    private final MappedStatement mappedStatement;

    public DynamicSql(final String statementId, final Object params) {
        final Configuration configuration = MapperRegister.configuration;
        mappedStatement = configuration.getMappedStatement(statementId);
        boundSql = mappedStatement.getBoundSql(params);
    }


    /**
     *
     * @return 返回带?占位符的sql
     */

    public String getSql() {
        return boundSql.getSql();
    }


    /**
     * @return 返回已经排好序的sql参数
     */
    @SuppressWarnings("unchecked")
    public Object[] getParams() {
        final List<ParameterMapping> mappings = boundSql.getParameterMappings();
        final Object[] result = new Object[mappings.size()];
        final Map<String, Object> paramsMap = (HashMap<String, Object>) boundSql.getParameterObject();
        for(int i = 0; i < mappings.size(); i++) {
            final ParameterMapping mapping = mappings.get(i);
            final String property = mapping.getProperty();
            final Object value = paramsMap.get(property);
            result[i] = value;
        }
        return result;
    }

    /**
     * 设置PreparedStatement 各种类型参数
     * @param preparedStatement
     */
    public void completePreparedStatementParams(final PreparedStatement preparedStatement) {
        final DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
        handler.setParameters(preparedStatement);
    }

}
