package com.soul.a94806.app12.utils;

import android.os.Environment;

import java.io.*;

public class FileUtils {

    private String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    public File write2SDFromInput(String path, String fileName, InputStream input) throws IOException {
        File dir = new File(SDPATH + path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(SDPATH + path + "/" + fileName);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists() && file.canWrite()) {
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int temp;
            while ((temp = input.read(buffer)) != -1) {
                output.write(buffer, 0, temp);
            }
            output.flush();
        }
        return file;
    }

    public boolean isFileExist(String fileName) {
        return new File(SDPATH + fileName).exists();
    }
}
