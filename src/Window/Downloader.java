package Window;

import Util.DownloadThread;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Downloader {
    private int x, y, width;
    private JProgressBar progressBar;
    private JTextArea nameArea;
    private JButton downloadBtn;
    private File downDir;
    private JFrame frame;
    private DownloadThread runn;
    private Thread downloadThread;
    private boolean downloading;

    public Downloader(int x, int y, int width, JFrame frame){
        this.x = x;
        this.y = y;
        this.width = width;
        this.frame = frame;
        this.downloading = false;

        this.nameArea = new JTextArea("No File");
        this.nameArea.setLocation(this.x, this.y);
        this.nameArea.setSize(new Double(width*0.75d).intValue(), width/20);
        this.nameArea.setBackground(frame.getBackground());

        this.progressBar = new JProgressBar();
        this.progressBar.setLocation(this.x, (this.y+width/15));
        this.progressBar.setSize(width, width/15);

        this.downloadBtn = new JButton("Download");
        this.downloadBtn.setSize(new Double(width*0.25d).intValue(), width/20);
        this.downloadBtn.setLocation(this.x + new Double(width*0.75d).intValue(), this.y);
        this.downloadBtn.addActionListener(onClick);
    }

    public JProgressBar getProgressBar(){
        return this.progressBar;
    }

    public JTextArea getNameArea(){
        return this.nameArea;
    }

    public JButton getDownloadBtn(){
        return this.downloadBtn;
    }

    ActionListener onClick = e -> {
        if(!downloading) {
            downloading = true;
            this.downloadBtn.setText("Cancel");
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.showDialog(this.frame, "Speichern");
            this.downDir = fc.getSelectedFile();
            this.runn = new DownloadThread(downDir, this.nameArea.getText(), this.progressBar);
            this.downloadThread = new Thread(this.runn);
            this.downloadThread.start();

        }else{
            this.runn.setCanceled(true);
            this.downloadBtn.setText("Download");
        }
    };

}
