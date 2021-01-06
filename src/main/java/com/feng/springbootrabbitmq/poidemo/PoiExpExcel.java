package com.feng.springbootrabbitmq.poidemo;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PoiExpExcel
{
    public static void main(String[] args)
    {
        String[] title = {"id", "name", "sex"};
        //创建Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // workbook.writeProtectWorkbook("123", "feng");
        //创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i = 1; i <= 10; i++)
        {
            HSSFRow nextRow = sheet.createRow(i);
            HSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue("a" + i);
            cell2 = nextRow.createCell(1);
            cell2.setCellValue("user" + i);
            cell2 = nextRow.createCell(2);
            cell2.setCellValue("男");
        }

        //创建一个文件
        File file = new File("D:/poi_test01.xls");
        try
        {
            file.createNewFile();
            //将Excel内容存盘
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 生成zip文件
    public static void zip() throws IOException
    {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("d://oi_test01.zip"));
        File f = new File("D:/poi_test01.xls");
        ZipEntry ze = new ZipEntry(f.getName());
        ze.setTime(f.lastModified());
        zip.putNextEntry(ze);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];
        while (in.read(b) != -1)
        {
            zip.write(b);
        }
        zip.flush();
        zip.close();
    }

    public static void encrypt_zip(String src_file, String dst_file, String encode)
    {
        File file = new File(src_file);

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);//压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别

        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);//加密方式
        parameters.setPassword(encode.toCharArray());//设置密码

        try
        {
            ZipFile zipFile = new ZipFile(dst_file);
            zipFile.setFileNameCharset("gbk");
            zipFile.addFile(file, parameters);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
