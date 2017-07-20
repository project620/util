package com.jim.util.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

/**
 * @author jim.huang
 *
 */
public class DynamicSql {

    private final BoundSql boundSql;
    private final Object params;

    public DynamicSql(final String statementId, final Object params) {
        this.params = params;
        final Configuration configuration = MapperRegister.configuration;
        final MappedStatement statement = configuration.getMappedStatement(statementId);
        boundSql = statement.getBoundSql(params);
    }


    public String getSql() {
        return boundSql.getSql();
    }


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

}
