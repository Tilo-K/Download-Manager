package Window;

import javax.swing.*;
import java.util.LinkedList;

public class Frame extends JFrame {
    private JPanel contentPane;
    private LinkedList<Downloader> downloads;


    public Frame(String title, int width, int height){
        super(title);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(width,height);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setLayout(null);

        contentPane = new JPanel();
        contentPane.setLocation(0,0);
        contentPane.setSize(width, height);
        contentPane.setLayout(null);

        this.downloads = new LinkedList<>();

        for(int i = 0; i < 8; i++){
            double wD = width*0.5;
            int w = (int)wD;
            downloads.add(new Downloader(10, 10+(75*i), w, this));
        }


        for(Downloader d : downloads) {
            contentPane.add(d.getNameArea());
            contentPane.add(d.getProgressBar());
            contentPane.add(d.getDownloadBtn());
        }

        super.setContentPane(contentPane);
    }
}
