import analisisenfrecuencias.FFT.Gestor;
import analisisenfrecuencias.FFT.NumeroComplejo;
import analisisenfrecuencias.Filtros.FiltroIdealPasaBajas;
import gui.JframeImagen;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
/*
      Image imagen = AbrirImagen.openImage();
        JframeImagen frame = new JframeImagen(imagen);
        Histogramas h1 = new Histogramas(imagen);
        h1.Graph();

        Image ruidoA = Suavizado.agregarRuidoAditivo(imagen,50) ;
        JframeImagen frame2 = new JframeImagen(ruidoA);
        Histogramas h2 = new Histogramas(ruidoA);
        h2.Graph();

        Image ruidoB = Suavizado.agregarSustractivo(imagen,50);
        JframeImagen frame3 = new JframeImagen(ruidoB);
        Histogramas h3 = new Histogramas(ruidoB);
        h3.Graph();
*/


        Image imagen = AbrirImagen.openImage();
        JframeImagen frame = new JframeImagen(imagen);
        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(imagen));
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(true);
        JframeImagen frame2 = new JframeImagen(AbrirImagen.toBufferedImage(iFre));

        // creamos el filtro
        FiltroIdealPasaBajas fipb = new FiltroIdealPasaBajas(35,new Dimension(512,512));
        fipb.crearFiltro();
        NumeroComplejo [][] filtro = fipb.getFiltroEspacial();
        JframeImagen frame3 = new JframeImagen(fipb.getImagen());
        gestor.aplicarFiltro(filtro);


        BufferedImage ImagenEspacial = gestor.obtenerImagenEspacial();
        JframeImagen frame4 = new JframeImagen(AbrirImagen.toBufferedImage(ImagenEspacial));
        System.out.println();

    }

}
