package com.jim.util.sql;

import org.apache.ibatis.session.Configuration;

/**
 * @author jim.huang
 */
public class MapperRegister {

    protected static Configuration configuration = new Configuration();

    public static void registe(final String packageName) {
        configuration.addMappers(packageName);
    }

}
