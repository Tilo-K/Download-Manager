package Util;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadThread implements Runnable{
    private File downDir;
    private String url;
    private File saveFile;
    private int fileEndSize;
    private int progress = 0;

    public DownloadThread(File downDir, String url){
        this.downDir = downDir;
        this.url = url;

        String[] urlArr = url.split("/");
        this.saveFile = new File(downDir.getAbsolutePath() + urlArr[urlArr.length-1]);
    }

    @Override
    public void run() {
        try {
            URL download = new URL(this.url);
            URLConnection downCon = download.openConnection();
            this.fileEndSize = downCon.getContentLength();
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(saveFile));
            BufferedReader urlIn = new BufferedReader(new InputStreamReader(downCon.getInputStream()));
            this.progress = 0;

            int left = this.fileEndSize;
            char[] buff = new char[1024];

            while(left > 0){
                int downloaded = urlIn.read(buff);
                fileOut.write(buff);
                fileOut.flush();

                left -= downloaded;
                progress += downloaded;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler bei der URL");
            e.printStackTrace();
        }
    }

    public int getProgress(){
        return this.progress;
    }

    public int getFileEndSize(){
        return this.fileEndSize;
    }

    public void cancel(){

    }
}
