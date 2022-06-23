//package org.springframework.beans.factory.support;
//
//import com.vdevline.anno.MyQualifier;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.TypeConverter;
//import org.springframework.beans.factory.config.DependencyDescriptor;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.Set;
//
//public class MyDefaultListableBeanFactory extends DefaultListableBeanFactory{
//
////    Class<? extends Annotation> myAnnoType = null;
////    @Override
////    protected boolean isAutowireCandidate(String beanName, RootBeanDefinition mbd, DependencyDescriptor descriptor, AutowireCandidateResolver resolver) {
////
////        MyQualifier myAnno = descriptor.getAnnotation(MyQualifier.class);
////        if (myAnno != null) {
////            if ((myAnno.group() + myAnno.version()).equals(beanName)) {
////                return true;
////            }else{
////                return false;
////            }
////        }
////
////        return super.isAutowireCandidate(beanName, mbd, descriptor, resolver);
////    }
//
//    @Override
//    public Object doResolveDependency(DependencyDescriptor descriptor, String beanName, Set<String> autowiredBeanNames, TypeConverter typeConverter) throws BeansException {
//        MyQualifier myAnno = descriptor.getAnnotation(MyQualifier.class);
//        if (myAnno != null) {
//            Field field = descriptor.getField();
//            Class fieldClass = field.getType();
//            Object proxy = RPCBeanManager.getProxy(fieldClass, myAnno.group(), myAnno.version(), this);
//            return proxy;
//        }
//
//        return super.doResolveDependency(descriptor, beanName, autowiredBeanNames, typeConverter);
//    }
//}
