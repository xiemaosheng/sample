package com.nd;

import com.alibaba.fastjson.JSONObject;
import com.nd.gaea.core.config.SystemConfig;

/**
 * 类名称：常量
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/10 0010
 */
public class Constant {
    public static final JSONObject UCNODE = JSONObject.parseObject(SystemConfig.instance.getProperty("uc_node"));
    public static final JSONObject UCORG = JSONObject.parseObject(SystemConfig.instance.getProperty("uc_org"));


    public static JSONObject getTeacherNode() {
        return UCNODE.getJSONObject("teacher_node");
    }

    public static JSONObject getStudentNode() {
        return UCNODE.getJSONObject("student_node");
    }

    public static JSONObject getUcOrg() {
        return UCORG;
    }

    public static String getOrgId() {
        return UCORG.getString("org_id");
    }
}
