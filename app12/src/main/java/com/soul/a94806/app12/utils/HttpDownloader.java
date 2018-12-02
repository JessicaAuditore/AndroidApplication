package com.soul.a94806.app12.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader implements Runnable {

    private String urlStr;
    private String path;
    private String fileName;

    public HttpDownloader(String urlStr, String path, String fileName) {
        this.urlStr = urlStr;
        this.path = path;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        File resultFile = null;
        URL url = null;
        FileUtils fileUtils = new FileUtils();
        if (fileUtils.isFileExist(path + "/" + fileName)) {
            System.out.println("文件已存在");
            return;
        } else {
            try {
                url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                inputStream = urlConnection.getInputStream();
                resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
                if (resultFile == null) {
                    System.out.println("下载失败");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
