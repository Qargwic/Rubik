package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final int SCALE = 1;

    private Graphics2D g;
    private BufferedImage image;
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BigCube bigCube;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void run(){

        long startTime;
        long waitTime;
        long URDTimeMilis;

        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        bigCube = new BigCube();

        bigCube.turn(30, 0, 60);

        while(running){
            startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            URDTimeMilis = (System.nanoTime() - startTime) / 1_000_000;
            waitTime  = targetTime - URDTimeMilis;
             try{
                 Thread.sleep(waitTime);
             } catch (Exception e){}


        }
    }

    private void gameUpdate(){
        double a = 0.0001;
        bigCube.turn(0,a,0);
    }
    private void gameRender(){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH,HEIGHT);
        g.setColor(Color.WHITE);
        bigCube.draw(g);
    }
    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();

        g2.drawImage(image,0,0,null);
        g2.dispose();
    }

    @Override
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

}
