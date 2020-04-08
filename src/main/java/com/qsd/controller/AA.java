package com.qsd.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.qsd.utils.*;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

    /**
     * xml转map 带属性
     *
     * @param xmlStr
     * @param needRootKey 是否需要在返回的map里加根节点键
     * @return
     * @throws DocumentException
     */
    @RequestMapping("666")
    public static Map xmlToMap(String xmlStr, boolean needRootKey) throws DocumentException {

        Document doc = DocumentHelper.parseText(xmlStr);

        Element root = doc.getRootElement();

        Map<String, Object> map = (Map<String, Object>) xmlToMapWithAttr(root);

        if (root.elements().size() == 0 && root.attributes().size() == 0) {

            return map; //根节点只有一个文本内容

        }

        if (needRootKey) {

            //在返回的map里加根节点键（如果需要）

            Map<String, Object> rootMap = new HashMap<String, Object>();

            rootMap.put(root.getName(), map);

            return rootMap;

        }

        return map;

    }

    /**
     * xml转map 带属性
     *
     * @param
     * @return
     */

    private static Map xmlToMapWithAttr(Element element) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        List<Element> list = element.elements();

        List<Attribute> listAttr0 = element.attributes(); // 当前节点的所有属性的list

        for (Attribute attr : listAttr0) {

            map.put(attr.getName(), attr.getValue());

        }

        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {

                Element iter = list.get(i);

                List mapList = new ArrayList();


                if (iter.elements().size() > 0) {

                    Map m = xmlToMapWithAttr(iter);

                    if (map.get(iter.getName()) != null) {

                        Object obj = map.get(iter.getName());

                        if (!(obj instanceof List)) {

                            mapList = new ArrayList();

                            mapList.add(obj);

                            mapList.add(m);

                        }

                        if (obj instanceof List) {

                            mapList = (List) obj;

                            mapList.add(m);

                        }

                        map.put(iter.getName(), mapList);

                    } else

                        map.put(iter.getName(), m);

                } else {

                    List<Attribute> listAttr = iter.attributes(); // 当前节点的所有属性的list

                    if (listAttr.size() > 0) {

                        map.put(listAttr.get(0).getValue(), iter.getText());

                    }

                }

            }

        } else {

            // 根节点的

            if (listAttr0.size() > 0) {

                map.put("content", element.getText());

            } else {

                map.put(element.getName(), element.getText());

            }

        }

        return map;

    }

}