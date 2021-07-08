package com.mdl.hundun;

import java.util.Arrays;

public class DownLoadTest {

    public static void main(String[] args) {

//        String urlStr = "http://yxs-app.oss-cn-beijing.aliyuncs.com/1568276825_a(1).JPG";
        String urlStr = "http://yxs-app.oss-cn-beijing.aliyuncs.com/20180820-162333_1.jpg";
        String fileName = "test1.jpg";
        String savePath = "D:\\xiekun\\混沌研习社";

        String[] picFormats = {"jpg","png","jpeg"};

        String[] split = urlStr.split("\\.");
        int length = split.length;
        String picFormat = split[length-1];

        boolean formatFlag = Arrays.asList(picFormats).contains(picFormat);

        System.out.println("formatFlag:" + formatFlag + "-----split" + picFormat);

//        DownLoadUtil.download(urlStr , fileName , savePath);
    }

}
