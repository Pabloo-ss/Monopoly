package Gui;

import javax.swing.*;


public class VAviso extends JFrame {
        private JLabel mensaje;
        private JButton aceptar;

        public VAviso(String texto){
            this.setSize(200, 100);
            this.mensaje = new JLabel(texto);
            this.aceptar = new JButton("Aceptar");

            this.aceptar.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    bAceptarActionPerformed(evt);
                }
            });
        }

        void bAceptarActionPerformed(java.awt.event.ActionEvent evt){
            this.dispose();
        }
}
