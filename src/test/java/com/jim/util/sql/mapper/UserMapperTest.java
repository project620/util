package com.jim.util.sql.mapper;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author jim.huang
 *
 */
public class UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	
	@BeforeClass
	public static void setUp() throws IOException {
		System.out.println("set up UserMapperTest");
		 Reader reader = Resources.getResourceAsReader("com/jim/util/sql/mapper/mybatis-config.xml");
		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		    reader.close();
		    // populate in-memory database
		    final SqlSession session = sqlSessionFactory.openSession();
		    final Connection conn = session.getConnection();
		    reader = Resources.getResourceAsReader("com/jim/util/sql/mapper/ddl.sql");
		    final ScriptRunner runner = new ScriptRunner(conn);
		    runner.setLogWriter(null);
		    runner.runScript(reader);
		    reader.close();
		    session.close();
	}
	
	
	@Test
	public void testGetUser() {
		final SqlSession sqlSession = sqlSessionFactory.openSession();
		final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		 final List<Map<String, Object>> users = userMapper.getUserByName("jim");
		 for(final Map<String, Object> user : users) {
			 Assert.assertEquals(user.get("NAME"), "jim");
		 }
		 sqlSession.close();
	}
	
	
	@AfterClass
	public static void shutDown() {
		System.out.println("shut down UserMapperTest");
	}
	
}
