package Gui;

import javax.swing.*;
import java.awt.*;
import Excepciones.Error;

public class CasillaGui extends JPanel {
    private final int size = 76;
    private JLabel etiqueta;
    private ColorGui colorPanel;
    private JPanel pEti;

    public CasillaGui(int pos, Orientacion orientacion, String texto, Color color){
        super();
        this.setBackground(Color.GRAY.brighter());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.etiqueta = new JLabel(texto);

        //Panel para las fichas
        this.pEti = new JPanel();
        this.pEti.setBackground(Color.GRAY.brighter());
        this.pEti.setPreferredSize(new Dimension(this.size - 16, this.size - 16));

        switch (orientacion) {
            case abajo:
                this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                if (pos % Error.maxFila == 0){
                    this.setBounds(11 * this.size, this.size * 11, this.size * 2, this.size * 2);
                    this.add(this.etiqueta);
                    this.add(this.pEti);
                }
                else {
                    this.setBounds((11 - pos) * this.size, this.size * 11, this.size, this.size * 2);
                    if (pos % Error.maxFila != 2 && pos % Error.maxFila != 4 && pos % Error.maxFila != 5 && pos % Error.maxFila != 7) {
                        this.colorPanel = new ColorGui(color, orientacion);
                        this.add(this.colorPanel);
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    } else {
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    }
                }
                break;
            case izquierda:
                this.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
                if (pos % Error.maxFila == 0) {
                    this.setBounds(0, this.size * 11, this.size * 2, this.size * 2);
                    this.add(this.pEti);
                    this.add(this.etiqueta);
                }
                else {
                    this.setBounds(0, this.size * 11 - (this.size * (pos % Error.maxFila)), this.size * 2, this.size);
                    if (pos % Error.maxFila != 2 && pos % Error.maxFila != 5 && pos % Error.maxFila != 7) {
                        this.colorPanel = new ColorGui(color, orientacion);
                        this.add(this.pEti);
                        this.add(this.etiqueta);
                        this.add(this.colorPanel);
                    } else {
                        this.add(this.pEti);
                        this.add(this.etiqueta);
                    }
                }
                break;
            case arriba:
                this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                if (pos % Error.maxFila == 0) {
                    this.setBounds(0, 0, this.size * 2, this.size * 2);
                    this.add(this.etiqueta);
                    this.add(this.pEti);
                }
                else {
                    this.setBounds((1 + pos % Error.maxFila) * this.size, 0, this.size, this.size * 2);
                    if (pos % Error.maxFila != 2 && pos % Error.maxFila != 5 && pos % Error.maxFila != 8) {
                        this.colorPanel = new ColorGui(color, orientacion);
                        this.add(this.colorPanel);
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    }else{
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    }
                }
                break;
            case derecha:
                this.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
                if (pos % Error.maxFila == 0) {
                    this.setBounds(11 * this.size, 0, this.size * 2, this.size * 2);
                    this.add(this.etiqueta);
                    this.add(this.pEti);
                }
                else {
                    this.setBounds(11 * this.size, (1 + pos % Error.maxFila) * this.size, this.size * 2, this.size);
                    if (pos % Error.maxFila != 3 && pos % Error.maxFila != 5 && pos % Error.maxFila != 6 && pos % Error.maxFila != 8) {
                        this.colorPanel = new ColorGui(color, orientacion);
                        this.add(this.colorPanel);
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    }else{
                        this.add(this.etiqueta);
                        this.add(this.pEti);
                    }
                }
                break;
        }
    }

    public ColorGui getColorPanel() {
        return colorPanel;
    }
}
