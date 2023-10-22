package com.KafuuChino0722.coreextensions.core.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCode {
    public static void gCode() throws IOException {
        // 原始ZIP文件
        String sourceFileName = "core\\datagen\\output.zip";
        // 损坏的ZIP文件
        String corruptedFileName = "core\\datagen\\generate.zip";

        // 读取原始ZIP文件
        FileInputStream fis = new FileInputStream(sourceFileName);
        ZipInputStream zis = new ZipInputStream(fis);

        // 创建损坏ZIP文件
        FileOutputStream fos = new FileOutputStream(corruptedFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        // 将原始ZIP文件中的内容复制到损坏ZIP文件
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = zis.read(buffer)) != -1) {
            zos.write(buffer, 0, bytesRead);
        }

        // 关闭流
        zis.close();
        zos.close();

        // 手动添加损坏标记（模拟）
        RandomAccessFile raf = new RandomAccessFile(corruptedFileName, "rw");
        raf.seek(10); // 随机选择一个位置添加标记
        raf.write(0);  // 在这个位置添加0，表示文件损坏
        raf.close();

        //System.out.println("文件已损坏，但仍然可以识别和解压。");
    }
}
