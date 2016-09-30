package com.nd.uc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nd.gaea.WafProperties;
import com.nd.gaea.client.entity.WafBearerToken;
import com.nd.gaea.client.http.WafHttpClient;
import com.nd.gaea.client.support.WafClientContextHolder;
import com.nd.gaea.client.support.WafContext;
import com.nd.gaea.util.WafJsonMapper;
import com.nd.wafextend.WafHttpClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 类名称：UC服务端bearToken调用
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/8/18 0018
 */
@Service
public class UcApiService {
    Logger logger = LoggerFactory.getLogger(UcApiService.class);

    static final String UC_BASE_URL = WafProperties.getProperty(WafContext.WAF_UC_URI);
    static final String UC_TOKEN_URL = UC_BASE_URL + "tokens";
    static final String UC_USER_INFO_URL = UC_BASE_URL + "users/{userId}";
    static final String UC_TOKEN_BASE_URL = UC_BASE_URL + "tokens/{access_token}";
    static final String UC_TOKEN_VALID_URL = UC_TOKEN_BASE_URL + "/actions/valid";
    static final String UC_THIRD_TOKEN_URL = UC_BASE_URL + "third_tokens";
    static final String UC_THIRD_QUERY_URL = UC_BASE_URL + "users/actions/third_query";
    static final String UC_USERS_QUERY_URL = UC_BASE_URL + "users/actions/query";
    static final String UC_ORG_CREATE_USER = UC_BASE_URL + "organizations/{org_id}/orgnodes/{node_id}/users";
    static final String UC_ORG_DELETE_USER = UC_ORG_CREATE_USER + "/{user_id}";
    static final String UC_ORG_USER_RESET_PASSWORD = UC_ORG_CREATE_USER + "/{user_id}/password";
    static final String UC_USER_MODIFY_PASSWORD = UC_USER_INFO_URL + "/password/actions/modify";
    static final String UC_CREATE_NODE_URL = UC_BASE_URL + "/organizations/{org_id}/orgnodes";
    static final String UC_MOVE_NODE_URL = UC_CREATE_NODE_URL + "/actions/move";
    static final String UC_MOVE_USER_URL = UC_BASE_URL + "/organizations/{org_id}/user/{user_id}/actions/move";
    static final String UC_SEARCH_NODE_URL = UC_BASE_URL + "/organizations/{org_id}/orgnodes/{node_id}/actions/search?name={name}&$offset=0&$limit=500";
    static final String UC_REGISTER = UC_BASE_URL + "users";
    static final String UC_SMSES = UC_BASE_URL + "smses";
    static final String UC_REFRESH_TOKEN = UC_BASE_URL+"/tokens/{refresh_token}/actions/refresh";
    static final String UC_SERVER_TIME = UC_BASE_URL + "server/time";
    static final String UC_UPDATE_NODE_URL = UC_BASE_URL + "/organizations/{org_id}/orgnodes/{node_id}";
    static final String UC_COUNT_NODE_AMOUNT_UNDER_NODE=UC_BASE_URL+
            "/organizations/{org_id}/orgnodes/{node_id}/nodes/actions/count";
    static final String UC_COUNT_USER_AMOUNT_UNDER_NODE=UC_BASE_URL+
            "/organizations/{org_id}/orgnodes/{node_id}/users/actions/count";
    static final String UC_DELETE_NODE = UC_BASE_URL+"/organizations/{org_id}/orgnodes/{node_id}";
    static final String UC_ORGANIZATIONS_NORNODES_URL = UC_BASE_URL+"/organizations/{org_id}/orgnodes/{node_id}";
    static final String UC_BATCH_ADD_ENTITY_USERS= UC_BASE_URL+"/organizations/{org_id}/orgnodes/{node_id}/entityusers";
    static final String UC_GET_MOBILE = UC_BASE_URL+"/users/{user_id}/mobile";
    static final String UC_GET_EMAIL = UC_BASE_URL+"/users/{user_id}/email";
    static final String UC_DELETE_USER_FORM_NODE =UC_BASE_URL+"/organizations/{org_id}/orgnodes/{node_id}/users/{user_id}";

    /**
     * 创建用户到指定节点
     * @param orgId
     * @param nodeId
     * @param json
     * @return
     */
    public JSONObject createOrgUser(String orgId, String nodeId, JSONObject json) {
        WafHttpClient client = WafHttpClientFactory.newInstanceOfWafSecurityHttpClient();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(json);
        logJsonData(jsonArray);
        return client.postForObject(UC_BATCH_ADD_ENTITY_USERS, jsonArray, JSONObject.class, orgId, nodeId);
    }

    public WafBearerToken getBearToken(){
        WafBearerToken wafBearerToken = WafClientContextHolder.getToken();
        return wafBearerToken;
    }

    protected void logJsonData(Object jsonData) {
        try {
            logger.info(String.format("json data:%s", WafJsonMapper.toJson(jsonData)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
