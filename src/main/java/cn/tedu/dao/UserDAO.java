package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public User login(String username, String password) {
        //获取链接
        try(Connection conn= DBUtils.getConn()){
            String sql=
                    "select id from vrduser where username=? and password=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int id=rs.getInt(1);
                return new User(id,username,password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean check(String username) {
        //获取链接
        try(Connection conn=DBUtils.getConn()){
            String sql="select id from vrduser where username=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            return rs.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void reg(String username, String password) {
        //获取链接
        try(Connection conn=DBUtils.getConn()){
            String sql="insert into vrduser values(null,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
