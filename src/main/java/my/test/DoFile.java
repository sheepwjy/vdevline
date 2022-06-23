package my.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DoFile {


    public static void main(String[] args) {
        String root = "E:\\e10work\\603\\mylog2-d4.out";
        BufferedReader br = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(root + "\\mylog2-d4-filtertree.out");
            br = new BufferedReader(new FileReader(root+"\\mylog2-d4.out"));
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.indexOf("docUpdateInit:") != -1
                        ||line.indexOf("docDeleteInit:") != -1
                        ||line.indexOf("methodPointcutAdvisorForClassification:") != -1
                        ||line.indexOf("methodPointcutAdvisor:") != -1
                        ||line.indexOf("dynamicDatasourceAnnotationAdvisor:") != -1
                        ||line.indexOf("dynamicTransactionAdvisor:") != -1
                        ||line.indexOf("org.springframework.transaction.config.internalTransactionAdvisor:") != -1
                        ||line.indexOf("sleuthAdvisorConfig:") != -1){
                    continue;
                }
                if (line.indexOf("@TTT@:") == -1) {
                    continue;
                }
                fw.append(line).append("\n");
            }
        } catch (Exception e) {

        }finally {
            try {
                br.close();
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
