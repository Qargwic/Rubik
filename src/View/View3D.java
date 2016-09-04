package View;

import javax.swing.*;

public class View3D {

    public static void main(String[] args){

        JFrame window = new JFrame("Rubik");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setContentPane(new GamePanel());

        window.pack();
        window.setVisible(true);
    }


}
