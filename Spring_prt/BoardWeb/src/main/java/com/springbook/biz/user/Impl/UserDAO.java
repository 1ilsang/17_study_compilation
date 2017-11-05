package com.springbook.biz.user.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

// DAO(Data Access Object)
@Repository("userDAO")
public class UserDAO {
    // JDBC ���� ����
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    // SQL ��ɾ��
    private final String USER_GET = "select * from users where id = ? and password=? ";
    // CRUD ����� �޼ҵ� ����
    // ȸ�� ���
    public UserVO getUser(UserVO vo){
        UserVO user = null;
        try {
            System.out.println("===> JDBC�� getUser() ��� ó��");
            conn = JDBCUtil.getConnection();
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery(); // rs ���� ���µ�>??
            if(rs.next()){   // �� �κп� ������ �ʰ� null�� ��ȯ�ع���
                user = new UserVO();
                user.setId(rs.getString("ID"));
                //System.out.println(user.getId());
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }
}
