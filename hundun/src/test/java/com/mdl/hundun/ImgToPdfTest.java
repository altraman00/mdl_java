package com.mdl.hundun;

import com.mdl.hundun.utils.PdfUtil;
import org.assertj.core.util.Lists;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImgToPdfTest {

    public static void main(String[] args) {
        PdfUtil pdfUtil = new PdfUtil();
        String dirs = "D:\\xiekun\\混沌研习社";
        File dirFiles = new File(dirs);
        File[] files = dirFiles.listFiles();
        List<String> coursePaths = Lists.newArrayList();
        for (File file : files) {
            String path = file.toString();
            if (file.isDirectory()) {
                String courseName = file.getName();
                System.out.println(path + "---" + courseName);
                String pdfFileName = "D:\\xiekun\\course_pdf" + "\\" + courseName + ".pdf";
                try {
                    pdfUtil.imgToPdf(path, pdfFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
