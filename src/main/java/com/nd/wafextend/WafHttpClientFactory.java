package com.nd.wafextend;

import com.nd.gaea.client.http.WafHttpClient;
import com.nd.gaea.client.http.WafSecurityHttpClient;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/6 0006
 */
public class WafHttpClientFactory {
    private static final WafHttpClient wafHttpClient = new WafHttpClient();//无bearToken
    private static final WafSecurityHttpClient wafSecurityHttpClient = new WafSecurityHttpClient();//带bearToken

    public static WafHttpClient newInstanceOfWafHttpClient(){
        return wafHttpClient;
    }

    public static  WafSecurityHttpClient newInstanceOfWafSecurityHttpClient(){
        return wafSecurityHttpClient;
    }
}
