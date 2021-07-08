package com.mdl.hundun.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.util.PDFMergerUtility;
import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PdfUtil {

    /**
     * @return
     * @Author Ragty
     * @Description 将图片转换为PDF
     * @Date 15:27 2019/3/4
     **/
    public static String Img2PDF(String imagePath, BufferedImage img, String descfolder) {
        String pdfPath = "";
        try {
            //图片操作
            Image image = null;
            File file = new File(descfolder);

            if (!file.exists()) {
                file.mkdirs();
            }

            pdfPath = descfolder + "/" + System.currentTimeMillis() + ".pdf";
            String type = imagePath.substring(imagePath.lastIndexOf(".") + 1);
            Document doc = new Document(null, 0, 0, 0, 0);

            //更换图片图层
            BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            bufferedImage.getGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
            bufferedImage = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(bufferedImage, null);

            //图片流处理
            doc.setPageSize(new Rectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));
            System.out.println(bufferedImage.getWidth() + "()()()()()" + bufferedImage.getHeight());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(bufferedImage, type, out);
            byte[] b = out.toByteArray();
            image = Image.getInstance(b);

            //写入PDF
            System.out.println("写入PDf:" + pdfPath);
            FileOutputStream fos = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.add(image);
            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return pdfPath;
    }


    /**
     * @return
     * @Author Ragty
     * @Description 获取文件夹下的PDF
     * @Date 17:33 2019/3/7
     **/
    private static String[] getFiles(String folder) throws IOException {
        File _folder = new File(folder);
        String[] filesInFolder;
        if (_folder.isDirectory()) {
//            filesInFolder = _folder.list();
            File[] files = _folder.listFiles();
            List<String> collect = Arrays.stream(files).map(t -> t.getAbsolutePath()).collect(Collectors.toList());
            filesInFolder=collect.toArray(new String[collect.size()]);
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }


    /**
     * @return
     * @Author Ragty
     * @Description 合成PDF
     * @Date 17:25 2019/3/7
     **/
    public static void mergePDF(String[] files, String desfolder, String pdfPath) throws Exception {
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (String file : files) {
            if (file.toLowerCase().endsWith("pdf")) {
                mergePdf.addSource(file);
            }
        }
//        String pdfPath = desfolder + "\\" + mergeFileName;
        System.out.println("pdfPath:" + pdfPath);
        mergePdf.setDestinationFileName(pdfPath);
        mergePdf.mergeDocuments();
        System.out.println("merge over");
    }

    /**
     * 删除合成前的基础pdf
     * @param files
     */
    private static void delOtherBasePdf(String[] files) {
        for (String file : files) {
            File picFile = new File(file);
            picFile.delete();
            if(!picFile.exists()){
                System.out.println("delete success : "+file);
            }
        }
    }


    /**
     * 文件夹下的图片转合成pdf
     * @param picDir   存放图片的文件夹
     * @param pdfName  xxx.pdf
     * @throws IOException
     */
    public static void imgToPdf(String picDir, String pdfName) throws IOException {
        //获取获取文件夹下的图片
        String[] files = getFiles(picDir);
        System.out.println("图片个数:"+files.length);

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            if (file.toLowerCase().endsWith(".png") || file.toLowerCase().endsWith(".jpg")
                    || file.toLowerCase().endsWith(".gif") || file.toLowerCase().endsWith(".jpeg")
                    || file.toLowerCase().endsWith(".gif")) {

                ////调整图片的尺寸大小等
                //ImgUtil imageUtil = new ImgUtil();
                //BufferedImage bi = imageUtil.rotateImage(file);
                File _img_file_ = new File(file);
                BufferedImage bi = ImageIO.read(_img_file_);
                if (bi == null) {
                    continue;
                }
                String pdffile = Img2PDF(file, bi, picDir);
                files[i] = pdffile;
            }
        }
        //合成pdf
        try {
            mergePDF(files, picDir, pdfName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除基础的pdf文件
        delOtherBasePdf(files);

    }



//    public static void main(String[] args) throws IOException {
//        String folder = "D:\\xiekun\\混沌研习社\\组织行为学：如何善待下属提升绩效";
////        String pdfFileName = "final.pdf";
//        String courseName = "组织行为学：如何善待下属提升绩效";
//        String pdfFileName = "D:\\xiekun\\course_pdf"+"\\"+courseName+".pdf";
//        imgToPdf(folder,pdfFileName);
//    }


//    public static void main(String[] args) throws Exception {
//        String[] files = new String[4];
//        files[0] = "D:\\xiekun\\混沌研习社\\如何找回真实的自我\\0-课程预告.jpg";
//        files[1] = "D:\\xiekun\\混沌研习社\\如何找回真实的自我\\1-为什么要找回真实的自我.jpg";
//        files[2] = "D:\\xiekun\\混沌研习社\\如何找回真实的自我\\2-问答环节.jpg";
//        files[3] = "D:\\xiekun\\混沌研习社\\如何找回真实的自我\\3-混沌学霸说.jpg";
//
//        String folder = "D:\\xiekun\\混沌研习社\\如何找回真实的自我";
//        String mergeFileName = "final.pdf";
//
//        for (int i = 0; i < files.length; i++) {
//            String file = files[i];
//            if (file.toLowerCase().endsWith(".png")
//                    || file.toLowerCase().endsWith(".jpg")
//                    || file.toLowerCase().endsWith(".gif")
//                    || file.toLowerCase().endsWith(".jpeg")
//                    || file.toLowerCase().endsWith(".gif")) {
//
//                ////调整图片的尺寸大小等
//                //ImgUtil imageUtil = new ImgUtil();
//                //BufferedImage bi = imageUtil.rotateImage(file);
//                File _img_file_ = new File(file);
//                BufferedImage bi = ImageIO.read(_img_file_);
//                if (bi == null) {
//                    continue;
//                }
//                String pdffile = Img2PDF(file, bi, folder);
//                files[i] = pdffile;
//            }
//        }
//        //合成pdf
//        mergePDF(files, folder, mergeFileName);
//        //删除基础的pdf文件
//        delOtherBasePdf(files);
//    }
}
