package com.qsd.utils;

/**
 * @author 秦世东
 * date 2020-03-30
 * 文件说明:
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 最重要的就是 不要 XmlType  和 XmlRootElement 注解不要弄混了
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"head","body"})
@XmlRootElement(name="xml")
public class XmlRemoteDto {
    private Head head;
    private Body body;

//  setter  getter  方法

    // toString 方法
    @Override
    public String toString() {
        return "XmlRemoteDto [head=" + head + ", body=" + body + "]";
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}