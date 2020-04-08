package com.qsd.controller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 秦世东
 * date 2020-04-08
 * 文件说明:
 */
public class TEST {
    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        boolean isCDATA = false;
//        String parentName = "<FormInfo name=\"REPORT_SJZ_JYJ_SJSHXSBZ\">\n" +
//                "  <Item name=\"CASEINFO_ID\" name_cn=\"办件ID\"></Item>\n" +
//                "  <Item name=\"REPORT_KEY\" name_cn=\"报表标识位\">approval_sqs_sjshxsbz</Item>\n" +
//                "  <Item name=\"CASE_NAME\" name_cn=\"审批事项详情名称\"> 市级三好学生 </Item>\n" +
//                "  <Item name=\"STU_NAME\" name_cn=\"学生姓名\"> 王五 </Item>\n" +
//                "  <Item name=\"STU_CLASS\" name_cn=\"学生班级\"> 五年级 </Item>\n" +
//                "  <Item name=\"STU_NO\" name_cn=\"学号\"> 0311923233 </Item>\n" +
//                "  <Item name=\"STU_POLITIC\" name_cn=\"政治面貌\"> 11 </Item>\n" +
//                "  <Item name=\"SCHOOL_NAME\" name_cn=\"学校名称\"> 测试学校 </Item>\n" +
//                "  <Item name=\"SCHOOL_TYPE\" name_cn=\"学校类型\"> 直属学校 </Item>\n" +
//                "  <Item name=\"LINK_NAME\" name_cn=\"联系人姓名\"> 李四 </Item>\n" +
//                "  <Item name=\"LINK_MOBILE\" name_cn=\"联系手机\"> 13800138000 </Item>\n" +
//                "  <Item name=\"ADDRESS\" name_cn=\"通讯地址\"> 测试地址 </Item>\n" +
//                "  <Item name=\"LOCATION_AREA\" name_cn=\"所属地区\"> 130104 </Item>\n" +
//                "</FormInfo>";
//        Document doc = DocumentHelper.createDocument();
//        doc.addElement(parentName);
//
//        String xml = recursionMapToXml(doc.getRootElement(), parentName, map, isCDATA);
//        System.out.println(formatXML(xml));
    }


    /**
     * (多层)xml格式字符串转换为map
     *
     * @param xml xml字符串
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> multilayerXmlToMap(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
           e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        if (null == doc) {
            return map;
        }
        // 获取根元素
        Element rootElement = doc.getRootElement();
        recursionXmlToMap(rootElement,map);
        return map;
    }

    /**
     * multilayerXmlToMap核心方法，递归调用
     *
     * @param element 节点元素
     * @param outmap 用于存储xml数据的map
     */
    @SuppressWarnings("unchecked")
    private static void recursionXmlToMap(Element element, Map<String, Object> outmap) {
        // 得到根元素下的子元素列表
        List<Element> list = element.elements();
        int size = list.size();
        if (size == 0) {
            // 如果没有子元素,则将其存储进map中
            outmap.put(element.getName(), element.getTextTrim());
        } else {
            // innermap用于存储子元素的属性名和属性值
            Map<String, Object> innermap = new HashMap<>();
            // 遍历子元素
            list.forEach(childElement -> recursionXmlToMap(childElement, innermap));
            outmap.put(element.getName(), innermap);
        }
    }

}
