package com.qsd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qsd.utils.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AA {

    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    /**
     * yunfeng POST 方式  用的是@RequestBody  注解 参数类型是String
     *
     * @param
     * @return
     */
    @PostMapping("/rpt/smsStatusPullYunFeng")
    public List<Record> smsStatusPullYunFeng(@RequestBody String msgreport) {
        System.out.println("yunfeng,回调参数为={}" + msgreport);
        XmlRemoteDto dto = (XmlRemoteDto) CloudFengUtil.convertXmlStrToObject(XmlRemoteDto.class, msgreport);
        Body body = dto.getBody();
        Records records = body.getRecords();
        List<Record> rec = records.getRecord();
        for (Record a : rec) {
            System.out.println(a.toString());
        }
        return rec;
    }

    @PostMapping("/rpt/update")
    public void test() {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "update  trademark  set type = ?,number =? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 8);
            ps.setObject(2, 100182);
            ps.setObject(3, 1);
            int result = ps.executeUpdate();
            if (result == 1)
                System.out.println("修改成功");
            else {
                System.out.println("修改失败");
            }
            System.out.print(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, connection);
        }
    }

    @PostMapping("/rpt/select")
    public void testSelect() {

        Connection connection = JDBCUtil.getConnection();

        try {
            String sql = "select * from trademark ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {//游标结果集不为空，则输出
                System.out.println(rs.getString("type") + rs.getString("number") + rs.getString("name") + rs.getString("content"));
                //System.out.print(String.valueOf(rs.getClob(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, connection);
        }
    }

    @RequestMapping("test")
    public void test222() throws SQLException {

        ConnectionPool pool = new ConnectionPool();
        Connection connection = pool.getConnection();
        try {
            String sql = "select * from trademark ";
            ps = connection.prepareStatement(sql);
            while (rs.next()) {//游标结果集不为空，则输出
                System.out.println(rs.getString("type") + rs.getString("number") + rs.getString("name") + rs.getString("content"));
                //System.out.print(String.valueOf(rs.getClob(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(connection);
        }
    }

}