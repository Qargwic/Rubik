package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, MouseMotionListener {
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
    @Override
    public void run(){

        long startTime;
        long waitTime;
        long URDTimeMilis;

        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHints(hints);
        bigCube = new BigCube();

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
    }
    private void gameRender(){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH,HEIGHT);
        g.setColor(Color.WHITE);
        bigCube.draw(g);
    }
    private void gameDraw(){
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(image,0,0,null);
        g2d.dispose();
    }

    @Override
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        addMouseMotionListener(this);
    }

    private boolean pressed = false;
    private int X;
    private int Y;
    private int sens = 10;
    @Override
    public void mouseDragged(MouseEvent e){
        int x;
        int y;
        if (!pressed) {
            X = e.getX();
            Y = e.getY();
        } else {
            x = e.getX();
            y = e.getY();
            bigCube.turnP(sens*(x - X), -sens*(y - Y));
            X = x;
            Y = y;

        }
        pressed = true;
    }
    @Override
    public void mouseMoved(MouseEvent e){
        if (pressed) bigCube.released();
        pressed = false;
    }


}
