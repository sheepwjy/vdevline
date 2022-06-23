package my.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DoFile2 {


    public static void main(String[] args) {
        String root = "E:\\e10work\\605\\mylog2-d5.out";
        //String root = "E:\\e10work\\605";
        //String root = "E:\\e10work\\603\\mylog2-d3.out";
        BufferedReader br = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(root + "\\mylog2-d5-cache-notzero.out");
            br = new BufferedReader(new FileReader(root+"\\mylog2-d5.out"));
//            fw = new FileWriter(root + "\\out - 13000-f10-cache-notzero.out");
//            br = new BufferedReader(new FileReader(root+"\\out - 13000-f10.txt"));
//            fw = new FileWriter(root + "\\mylog2-d3-cache-notzero.out");
//            br = new BufferedReader(new FileReader(root+"\\mylog2-d3.out"));
            String line = null;
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf("I am cache:");
                if ( idx== -1 || line.indexOf("=begin") != -1) {
                    continue;
                }

                line = line.substring(idx);
                String[] lines = line.split("[:#]");
                if(lines[3].length()<4 || lines[4].length()==1 || lines[5].length()==1){
                    continue;
                }



                fw.append(lines[6]).append("\n");
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
