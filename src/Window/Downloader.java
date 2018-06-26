package Window;

import javax.swing.*;

public class Downloader {
    private int x, y, width;
    private JProgressBar progressBar;
    private JLabel nameLabel;

    public Downloader(int x, int y, int width){
        this.x = x;
        this.y = y;
        this.width = width;

        this.nameLabel = new JLabel("No File");
        this.nameLabel.setLocation(this.x, this.y);
        this.nameLabel.setSize(width, width/15);

        this.progressBar = new JProgressBar();
        this.progressBar.setLocation(this.x, (this.y+width/15));
        this.progressBar.setSize(width, width/15);
    }

    public JProgressBar getProgressBar(){
        return this.progressBar;
    }

    public JLabel getNameLabel(){
        return this.nameLabel;
    }
}
