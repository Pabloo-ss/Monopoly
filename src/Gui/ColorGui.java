package Gui;

import javax.swing.*;
import java.awt.*;

public class ColorGui extends JPanel {
    private final int sizey = 19;//Esto se invertiria para las casillas que estan de lado
    private final int sizex = 76;
    private JLabel edificios;




    public ColorGui(Color color, Orientacion orientacion){
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(color);
        this.edificios = new JLabel("0");

        if(Orientacion.abajo.equals(orientacion) || Orientacion.arriba.equals(orientacion))
            this.setPreferredSize(new Dimension(this.sizex, this.sizey));
        else
            this.setPreferredSize(new Dimension(this.sizey, this.sizex));

        this.add(this.edificios);
    }

    public void actualizarEdificios(Integer num){
        this.edificios.setText(num.toString());
        this.edificios.repaint();
    }
}
