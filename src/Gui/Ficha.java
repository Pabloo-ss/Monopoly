package Gui;

import javax.swing.*;
import java.awt.*;

public class Ficha extends JPanel {
    private int pos;

    public Ficha(Color color) {
        this.pos = 0;
        this.setBackground(color);
        this.setPreferredSize(new Dimension(20, 20));
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
