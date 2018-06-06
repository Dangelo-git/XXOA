package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class daoexamplegenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.dangelo.xxoa.dao");

        addNote(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }
    private static void addNote(Schema schema) {


//        //信息列表
//        Entity MessageInfo = schema.addEntity("MessageInfo");
//        MessageInfo.addIdProperty();
//        MessageInfo.addStringProperty("msg_name");                    //短信姓名
//        MessageInfo.addStringProperty("msg_content");                  //短信描述
//        MessageInfo.addIntProperty("msg_isread");                     //短信是否已读0已读1未读
//        MessageInfo.addLongProperty("msg_time");               //短信收到/发送时间
//        MessageInfo.addIntProperty("msg_type");                    //短信状态0:发送1:发送失败2:发送中3:收到
//        MessageInfo.addStringProperty("msg_pic");                     //短信头像图片地址
//        MessageInfo.addStringProperty("msg_number");                 //短信号码
//        MessageInfo.addStringProperty("msg_arg1");      //预留参数1
//        MessageInfo.addStringProperty("msg_arg2");      //预留参数2

        Entity FileInfo = schema.addEntity("FileInfo");
        FileInfo.addIdProperty();
        FileInfo.addStringProperty("FileUrl");                    //短信姓名
        FileInfo.addStringProperty("FileName");                  //短信描述
        FileInfo.addStringProperty("Filepath");                     //短信是否已读0已读1未读
        FileInfo.addStringProperty("Filetype");

    }
}
