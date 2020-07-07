package analisisespecial;

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.pow;
import static java.lang.Math.log;

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

public static Image expansionLineal(Histogramas h, Image imagen){
    BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
    Color color;
    for(int x=0; x<bi.getWidth();x++)
        for(int y=0; y<bi.getHeight();y++){
            int ValorPixel = bi.getRGB(x,y);
            color = new Color(bi.getRGB(x, y));
            int r = (color.getRed()-h.getMinR())*(255/h.getMaxR()-h.getMinR());
            int g = (color.getGreen()-h.getMinG())*(255/h.getMaxG()-h.getMinG());
            int b = (color.getBlue()-h.getMinB())*(255/h.getMaxB()-h.getMinB());
            color = new Color(validarLimites(r),
                    validarLimites(g),
                    validarLimites(b));
            bi.setRGB(x,y,color.getRGB());
        }

    return AbrirImagen.toImage(bi);
}
    public static Image expancionLogaritmica(Image imagen){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (int )((255*log(1+color.getRed()))/log(255));
                int g = (int )((255*log(1+color.getGreen()))/log(255));
                int b =  (int )((255*log(1+color.getBlue()))/log(255));

                color = new Color(validarLimites(r),
                        validarLimites(g),
                        validarLimites(b));
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    public static Image expancionExponencial(Image imagen,double z){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (int )((pow(1+z,color.getRed()))/z);
                int g = (int )((pow(1+z,color.getGreen()))/z);
                int b =  (int) ((pow(1+z,color.getBlue()))/z);

                color = new Color(validarLimites(r),
                        validarLimites(g),
                        validarLimites(b));
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static Image imagenSeparada(Image imagen, int u1 ){


        Image grises = FiltrosEspaciales.generarImagenGrises(imagen);

        BufferedImage big = AbrirImagen.toBufferedImage(grises);

        Color color, colorFondo, colorImagen;
        colorFondo = new Color(255,255,255);
        colorImagen =new Color(0,0,0);
        for(int x=0; x<big.getWidth();x++)
            for(int y=0; y<big.getHeight();y++){
                color = new Color(big.getRGB(x, y));
                int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
                if (prom>u1){
                    big.setRGB(x,y,colorFondo.getRGB());
                }else {
                    big.setRGB(x, y, colorImagen.getRGB());
                }

            }
        return AbrirImagen.toImage(big);
    }


    public static int metodoIterativo(double[] histograma){

        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        System.out.println(ui);
        do{
            uNuevo = ui;
            ui = reajustarUmbral(ui,histograma);
            System.out.println(ui);
        }while(uNuevo!=ui);

        return ui;

    }


    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
            numPixels+=histograma[x];
            suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }


    private static int reajustarUmbral(int ui, double[] histograma) {
        int u1,u2;
        int a1=0,a2=0,n1=0,n2=0;
        a1+=histograma[0];
        for(int x=1;x<ui;x++){
            a1+=histograma[x]*x;
            n1+=histograma[x];
        }

        for(int y=ui;y<=255;y++){
            a2+=histograma[y]*y;
            n2+=histograma[y];
        }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
        return (int)((u1+u2)/2);
    }


    public static Image ecualizarImagen(Image imagen){

        int nxm = imagen.getWidth(null)*imagen.getHeight(null);
        Histogramas h = new Histogramas(imagen);
        double[] ho = h.getHRed();
        double[] daf = new double[256];
        int[] nt = new int[256];
        daf[0] = (int)ho[0];
        nt[0] = (int)Math.round((daf[0]/nxm)*255);
        // recorremos el histograma para acumular
        for(int x=1; x<ho.length;x++){
            daf[x] = (int)(ho[x]+daf[x-1]);
            double aux = daf[x]/nxm;
            int tmp = (int) Math.round(aux * 255);
            nt[x] = tmp;
        }

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int t = color.getRed();
                int t2 =nt[t];
                color = new Color(t2,t2,t2);
                bi.setRGB(x,y,color.getRGB());
            }


        return AbrirImagen.toImage(bi);

    }

}
