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
    private boolean canceled = false;
    private JProgressBar progressBar;
    private JButton downloadBtn;

    public DownloadThread(File downDir, String url, JProgressBar progressBar, JButton downloadBtn){
        this.downDir = downDir;
        this.url = url;

        String[] urlArr = url.split("/");
        this.saveFile = new File(downDir.getAbsolutePath() + "/" +  urlArr[urlArr.length-1]);

        if(!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.progressBar = progressBar;
        this.downloadBtn = downloadBtn;
    }

    @Override
    public void run() {
        try {
            canceled = false;
            URL download = new URL(this.url);
            URLConnection downCon = download.openConnection();
            this.fileEndSize = downCon.getContentLength();
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            BufferedInputStream urlIn = new BufferedInputStream(downCon.getInputStream());
            this.progress = 0;

            byte[] buff = new byte[1024];

            int count = 0;
            while(((count = urlIn.read(buff,0,1024)) != -1) && !canceled){
                fileOut.write(buff,0,count);
                fileOut.flush();

                progress += count;

                this.progressBar.setMinimum(0);
                this.progressBar.setMaximum(this.fileEndSize);
                this.progressBar.setValue(progress);
            }

            if(canceled){
                JOptionPane.showMessageDialog(null, "Canceled");
            }else{
                JOptionPane.showMessageDialog(null, "Done !");
                this.progressBar.setValue(progress);
            }

            fileOut.close();
            urlIn.close();


            this.progressBar.setValue(0);
            this.downloadBtn.setText("Download");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler bei der URL");
            e.printStackTrace();
        }
    }


    public void setCanceled(boolean b){
        canceled = b;
    }
}
