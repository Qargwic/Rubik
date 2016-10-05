package View;

import javax.swing.*;

public class View3D {

    public static void main(String[] args){

        JFrame frame = new JFrame("Rubik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new GamePanel());

        frame.pack();
        frame.setVisible(true);
    }


}
