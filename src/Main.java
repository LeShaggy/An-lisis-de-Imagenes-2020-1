import analisisespecial.Histogramas;
import gui.JFrameSegmentacion;
import gui.JframeImagen;
import open.AbrirImagen;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Image imagen = AbrirImagen.openImage();


        Histogramas h = new Histogramas(imagen);
        h.Graph();
        JframeImagen frame = new JframeImagen(imagen);

        JFrameSegmentacion frame2 = new JFrameSegmentacion("Segmentacion", imagen);
        //h = new Histogramas():



        System.out.println();

    }
}