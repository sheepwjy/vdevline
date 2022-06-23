package my.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateServiceTest {
    private static final String root = "E:/idea_work/dubbo-project/src/main/java/com/weaver/dubbo/provider/test";
    private static final String resource = "E:/idea_work/dubbo-project/src/main/resources/weaver-dubbo-provider2.xml";
    private static final String targetWar = "E:/idea_work/dubbo-project/target/dubbo-project-1.0-SNAPSHOT.war";

    private static final List<Integer> order = new ArrayList<Integer>();
    private static final List<Integer> order2 = new ArrayList<Integer>();


    private  static int server_count = 10;
    private  static int server_field_count = 2;

    private  static int dserver_count = 10;
    private  static int dserver_field_count = 2;

    public static void main(String[] args) {

        FileUtil.deleteDirectory(root);
        FileUtil.deleteFile(targetWar);
        new File(root).mkdirs();
        new File(root+"/api").mkdirs();

        for (int i = 1; i <= server_field_count; i++) {
            order.add(i);
        }
        for (int i = 1; i <= dserver_field_count; i++) {
            order2.add(i);
        }

        for (int i = 1; i <= server_count; i++) {
            makeOneServer(i);
        }

        makeOneDServerXml(dserver_count);
        for (int i = 1; i <= dserver_count; i++) {
            makeOneDServer(i);
        }


    }

    private static void makeOneDServerXml(int n) {
        FileWriter w = null;
        try {
            w = new FileWriter(resource);

            w.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                    "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"\n" +
                    "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                    "    http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                    "     http://code.alibabatech.com/schema/dubbo\n" +
                    "     http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">").append("\n");

            for (int i = 1; i <= n; i++) {
                w.append("<dubbo:service interface=\"com.weaver.dubbo.provider.test.api.TestDService"+i+"\"\n" +
                        "                   ref=\"dubbo_service_"+i+"\" group=\"group_"+i+"\" version=\"version_"+i+"\"/>").append("\n");;

            }

            w.append("</beans>");


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                w.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void makeOneDServer(int n) {
        FileWriter w = null;
        try {
            w = new FileWriter(root + "/api/TestDService" + n + ".java");

            w.append("package com.weaver.dubbo.provider.test.api;").append("\n");
            w.append("public interface TestDService" + n + " {").append("\n");
            w.append("    public void hello();").append("\n");
            w.append("}").append("\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                w.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        try {
            Collections.shuffle(order2);

            w = new FileWriter(root + "/TestDService" + n + "Impl.java");

            w.append("package com.weaver.dubbo.provider.test;").append("\n");
            w.append("import com.weaver.dubbo.provider.test.api.*;").append("\n");
            w.append("import org.apache.dubbo.config.annotation.DubboReference;").append("\n");
            w.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
            w.append("import org.springframework.stereotype.Service;").append("\n");
            w.append("@Service(\"dubbo_service_"+n+"\")").append("\n");
            w.append("public class TestDService" + n + "Impl implements TestDService" + n + " {").append("\n");
            for (int i : order) {
                int idx = ((int) ((n - 1) * 1.0 / order.size())) * order.size() + i;
                w.append("    @Autowired").append("\n");
                w.append("    private TestService" + idx + " testService" + idx + ";").append("\n");
            }

            for (int i : order2) {
                int idx = ((int) ((n - 1) * 1.0 / order2.size())) * order2.size() + i;
                w.append("    @DubboReference(group = \"group_"+idx+"\", version = \"version_"+idx+"\")").append("\n");
                w.append("    private TestDService" + idx + " testDService" + idx + ";").append("\n");
            }

            w.append("    public TestDService" + n + "Impl() {").append("\n");
            w.append("        System.out.println(\"init:\" + this.getClass().getName());").append("\n");
            w.append("    }").append("\n");

            w.append("    @Override").append("\n");
            w.append("    public void hello() {").append("\n");
            w.append("        System.out.println(\"hello:\" + this.getClass().getName());").append("\n");
            w.append("    }").append("\n");
            w.append("}").append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                w.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static void makeOneServer(int n) {
        FileWriter w = null;
        try {
            w = new FileWriter(root + "/api/TestService" + n + ".java");

            w.append("package com.weaver.dubbo.provider.test.api;").append("\n");
            w.append("public interface TestService" + n + " {").append("\n");
            w.append("    public void hello();").append("\n");
            w.append("}").append("\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                w.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        try {
            Collections.shuffle(order);
            Collections.shuffle(order2);

            w = new FileWriter(root + "/TestService" + n + "Impl.java");

            w.append("package com.weaver.dubbo.provider.test;").append("\n");
            w.append("import com.weaver.dubbo.provider.test.api.*;").append("\n");
            w.append("import org.apache.dubbo.config.annotation.DubboReference;").append("\n");
            w.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
            w.append("import org.springframework.stereotype.Service;").append("\n");
            w.append("@Service").append("\n");
            w.append("public class TestService" + n + "Impl implements TestService" + n + " {").append("\n");
            for (int i : order) {
                int idx = ((int) ((n - 1) * 1.0 / order.size())) * order.size() + i;
                w.append("    @Autowired").append("\n");
                w.append("    private TestService" + idx + " testService" + idx + ";").append("\n");
            }

            int n2 = n - dserver_count * ((int) (n * 0.99999 / dserver_count));
            n2 = n2 == 0 ? 1 : n2;
            for (int i : order2) {
                int idx = ((int) ((n2 - 1) * 1.0 / order2.size())) * order2.size() + i;
                w.append("    @DubboReference(group = \"group_"+idx+"\", version = \"version_"+idx+"\")").append("\n");
                w.append("    private TestDService" + idx + " testDService" + idx + ";").append("\n");
            }

            w.append("    public TestService" + n + "Impl() {").append("\n");
            w.append("        System.out.println(\"init:\" + this.getClass().getName());").append("\n");
            w.append("    }").append("\n");

            w.append("    @Override").append("\n");
            w.append("    public void hello() {").append("\n");
            w.append("    }").append("\n");
            w.append("}").append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                w.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
