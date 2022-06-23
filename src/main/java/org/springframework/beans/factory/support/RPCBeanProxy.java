package org.springframework.beans.factory.support;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RPCBeanProxy implements MethodInterceptor {

    private Object srcTarget = null;
    private Class fieldClass = null;
    private String beanName = null;
    private String group = null;
    private String version = null;

    private Object lockObject = new Object();

    private Enhancer enhancer = new Enhancer();

    private DefaultListableBeanFactory beanFactory = null;

    public RPCBeanProxy(Class fieldClass, String group, String version, DefaultListableBeanFactory beanFactory) {
        this.fieldClass = fieldClass;
        this.beanName = group + version;
        this.group = group;
        this.version = version;
        this.beanFactory = beanFactory;
    }

    public Object getProxy() {
        enhancer.setSuperclass(fieldClass);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (srcTarget == null) {
            synchronized (lockObject) {
                if (srcTarget == null) {
                    srcTarget = beanFactory.doGetBean(beanName, null, null, false);
                }
            }
        }
        System.out.println("rpc begin:"+fieldClass.getName()+"."+beanName);
        Object result = method.invoke(srcTarget, objects);
        return result;
    }
}
