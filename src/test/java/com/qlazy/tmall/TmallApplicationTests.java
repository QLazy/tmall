package com.qlazy.tmall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TmallApplicationTests {

	@Test
	void contextLoads() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tmall_springboot?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC",
                        "root", "zhouqi");
                Statement s = c.createStatement();
        )
        {
            for (int i = 1; i <=10 ; i++) {
                String sqlFormat = "insert into myuser values(null,'测试用户%d', 'password',null)";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }
              
            System.out.println("已经成功创建10条分类测试数据");
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
