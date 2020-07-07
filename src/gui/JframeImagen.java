package gui;

import javax.swing.*;
import java.awt.*;

public class JframeImagen extends JFrame{

    private Image imagenOriginal;

    public JframeImagen(final Image aux){
        this.imagenOriginal = aux;
        final JLabel etiqueta = new JLabel();
        etiqueta.setIcon(new ImageIcon(this.imagenOriginal));
        add(etiqueta);
        setSize(this.imagenOriginal.getWidth(null),this.imagenOriginal.getHeight(null));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
//
    public Image getImagenOriginal(){
        return this.imagenOriginal;
    }
    public void setImagenOriginal(Image aux){
        this.imagenOriginal = aux;
    }


} // End class