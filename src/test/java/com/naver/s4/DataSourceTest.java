package com.naver.s4;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;

public class DataSourceTest extends TestAbstractCase {

	@Inject 
	private DataSource dataSource;

	@Test
	public void dataSourceTest() throws Exception {
		/* assertNotNull(dataSource); 데이터소스가 null인지 아닌지만 판단 */
		assertNotNull(dataSource.getConnection()); //연결이 되는지 안되는지 판단
	}
	
}
