package org.springframework.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RPCBeanManager {
    private static Map<String, Object> proxyMap = new ConcurrentHashMap<>();

    private static Object synObject = new Object();


    public static Object getProxy(Class fieldClass, String group, String version, DefaultListableBeanFactory factory) {
        String key = createProxyKey(group, version);
        Object proxy = proxyMap.get(key);
        if (proxy == null) {
            synchronized (synObject){
                if (proxy == null) {
                    proxy = new RPCBeanProxy(fieldClass, group, version, factory).getProxy();
                }
            }
        }
        return proxy;
    }

    private static String createProxyKey(String group, String version) {
        return group + "" + version;
    }
}
