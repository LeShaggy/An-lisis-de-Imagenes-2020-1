package analisisespecial;

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltrosEspaciales{

    public static Image generarImagenGrises(Image io){

        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int prom = (color.getRed()+color.getGreen()+color.getBlue())/3;
                color = new Color(prom,prom,prom);
                bi.setRGB(x, y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static Image generarImagenNegativa(Image io){

        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));

                color = new Color(255-color.getRed()
                        ,255-color.getGreen()
                        ,255-color.getBlue());
                bi.setRGB(x, y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static Image iluminarImagen(Image imagen, int alpha){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = color.getRed() + alpha;
                int g = color.getGreen() + alpha;
                int b = color.getBlue() + alpha;
                color = new Color(validarLimites(r),
                        validarLimites(g),
                        validarLimites(b));
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static Image modificarTemperatura(Image imagen, int alpha){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = color.getRed() + alpha;
                int g = color.getGreen();
                int b = color.getBlue() - alpha;
                color = new Color(validarLimites(r),
                        validarLimites(g),
                        validarLimites(b));
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static int validarLimites (int aux){

        if (aux<0)return 0;
        if (aux>255)return 255;
        return aux;

    }
    public static int obtenerMax(double[] h){

        return -1;
    }

    public static int obtenerMin(double[] h){
        for (int x=0; x<h.length;x++){
            if (h[x]!=0) return x;

        }

        return -1;
    }

    public static Image segmentarImagen(Image imagen, int umbral){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
                if (prom>umbral){
                    bi.setRGB(x,y,colorFondo.getRGB());
                }

            }
        return AbrirImagen.toImage(bi);
    }

    public static Image segmentarImagen(Image imagen, int u1, int u2){
        // TODO: garantizar  que el u2>u1
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
                if (!(prom>= u1 && prom<=u2)){
                    bi.setRGB(x,y,colorFondo.getRGB());
                }

            }
        return AbrirImagen.toImage(bi);
    }
/*
    public static Image FiltroT(Image imagen, int eje){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        bi.createGraphics().drawImage(imagen);
        Color color;
        int colorFinal;
        int max, min;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                int ValorPixel = bi.getRGB(x,y);
                color = new Color(bi.getRGB(x, y));
                int r = color.getRed() + eje;
                int g = color.getGreen() + eje;
                int b = color.getBlue() + eje;
                int ValorFinalPixel = 0;

                //calcula el promedio
                int total = ((r + g + b)/3);
                ValorFinalPixel = (int)  (255/(max - min))*(total-min);
                colorFinal = (0x00000000 | (ValorFinalPixel << 16 ) |(ValorFinalPixel));
                bi.setRGB(x,y,colorFinal);
            }

        return null;
    }
*/
public static Image expansionLineal(Image imagen, int r1, int r2){
    BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
    Color color;
    for(int x=0; x<bi.getWidth();x++)
        for(int y=0; y<bi.getHeight();y++){
            int ValorPixel = bi.getRGB(x,y);
            color = new Color(bi.getRGB(x, y));
            int r = (color.getRed()-r1*(255)) ;
            int g = color.getGreen() + ;
            int b = color.getBlue() + ;
            int ValorFinalPixel = 0;

            //calcula el promedio
            int total = ((r + g + b)/3);
            ValorFinalPixel = (int)  (255/(max - min))*(total-min);
            colorFinal = (0x00000000 | (ValorFinalPixel << 16 ) |(ValorFinalPixel));
            bi.setRGB(x,y,colorFinal);..
        }

    return null;
}



}