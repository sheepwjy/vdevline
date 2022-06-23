package my.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateServiceTest2 {
    private static final String root = "E:/idea_work/studydemo/src/main/java/com/example/studydemo/service/test/";

    private static final List<Integer> order = new ArrayList<Integer>();

    private  static int server_count = 10;
    private  static int server_field_count = 2;

    public static void main(String[] args) {

        FileUtil.deleteDirectory(root);
        new File(root).mkdirs();
        new File(root+"/impl").mkdirs();

        for (int i = 1; i <= server_field_count; i++) {
            order.add(i);
        }

        for (int i = 1; i <= server_count; i++) {
            makeOneServer(i);
        }

    }

    private static void makeOneServer(int n) {
        FileWriter w = null;
        try {
            w = new FileWriter(root + "/TestService" + n + ".java");

            w.append("package com.example.studydemo.service.test;").append("\n");
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

            w = new FileWriter(root + "/impl/TestService" + n + "Impl.java");

            w.append("package com.example.studydemo.service.test.impl;").append("\n");
            w.append("import com.example.studydemo.service.test.*;").append("\n");
            w.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
            w.append("import org.springframework.stereotype.Service;").append("\n");
            w.append("@Service").append("\n");
            w.append("public class TestService" + n + "Impl implements TestService" + n + " {").append("\n");
            for (int i : order) {
                int idx = ((int) ((n - 1) * 1.0 / order.size())) * order.size() + i;
                w.append("    @Autowired").append("\n");
                w.append("    private TestService" + idx + " testService" + idx + ";").append("\n");
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
