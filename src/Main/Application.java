package Main;

import Window.Frame;

public class Application {
    private Frame window;

    public void run(){
        initWindow();

    }

    public void initWindow(){
        this.window = new Frame("Download Manager", 1000, 720);
        this.window.setVisible(true);
    }
}
