import analisisespecial.FiltrosEspaciales;
import analisisespecial.Histogramas;
import gui.JframeImagen;
import open.AbrirImagen;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        JframeImagen frame = new JframeImagen(imagen);
        Histogramas h1 = new Histogramas(imagen);
        //h1.Graph();
            //FiltrosEspaciales.imagenSeparada(imagen, 140);
        Image IP1 = FiltrosEspaciales.expancionExponencial(imagen,88.8);
        JframeImagen frame2 = new JframeImagen(IP1);
        Histogramas h2 = new Histogramas(IP1);
        //h2.Graph();

        System.out.println();

    }
}