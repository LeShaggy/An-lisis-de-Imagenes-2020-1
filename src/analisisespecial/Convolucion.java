package analisisespecial;

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Convolucion {
    // Matrices
     // Mascaras
    public static int RealzarBordes[][]={
            {0,0,0},
            {-1,-1,0},
            {0,0,0}
    };

    public static int filtroEnfocar[][]={
            {0,-1,0},
            {-1,5,-1},
            {0,-1,0}
    };

    public static int Desenfoque[][]={
            {1,1,1},
            {1,1,1},
            {1,1,1}
    };

    public static int DetectarBorder[][]={
            {0,1,0},
            {1,-4,1},
            {0,1,0}
    };

    public static int Repujado[][]={
            {-2,-1,0},
            {-1,-1,1},
            {0,1,2}
    };

    // Para detectar bordes
    public static int Prewitt[][][]={{
            {-1,0,1},
            {-1,0,1},
            {-1,0,1}},
            {{1,1,1},
             {0,0,0},
            {-1,-1,-1}}
    };

    public static int Sobel[][][]={{
            {-1,0,1},
            {-2,0,2},
            {-1,0,1}},
            {{-1,-2,-1},
            {0,0,0},
             {1,2,1}}
    };

    public static int Roberts[][][]={{
            {-1,0,0},
            {0,1,0},
            {0,0,0}},
            {{0,0,-1},
            {0,1,0},
             {0,0,0}}
    };

    public static int Kirsch[][][]={{
            {-3,-3, 5},
            {-3, 0, 5},
            {-3,-3, 5}},
            {{5,  5 , 5},
            {-3, 0, -3},
            {-3,-3, -3}}

    }; //end kisrch

    public static Image aplicarConvolucion(Image io,int[][] mascara, int div, int offset){

        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        // recorres el buffer
        for(int x=0; x < bi.getWidth();x++){
            for(int y=0; y < bi.getHeight();y++){
                int rgb = calcularNuevoTono(x,y,bi,mascara, div, offset);
                bnuevo.setRGB(x, y, rgb);
            }
        }
        return AbrirImagen.toImage(bnuevo);
    }

    private static int calcularNuevoTono(int x, int y, BufferedImage bi, int[][] mascara, int div, int offset) {
        int auxR = 0, auxG = 0, auxB = 0;
        Color color = null;
        int k = 0;
        for(int i = 0 , r = x -1; i<mascara.length;i++, r++){
            for(int j = 0, c = y -1; j < mascara[0].length;j++, c++){


                    try {
                        int rgb = bi.getRGB(r, c);
                        //int loc2= (x+i) * ((y*j)*bi.getWidth());

                       //k++;
                        color = new Color(rgb);
                        // acomodar la multiplicación
                        auxR+= color.getRed()*mascara[i][j];
                        auxG+= color.getGreen()*mascara[i][j];
                        auxB+= color.getBlue()*mascara[i][j];

                    } catch (Exception e) {
                        // nada de nada
                    }

            }
        }
        // quitar k
        // quitar el if
        /*if(k!=0){*/
            auxR/=div;
            auxG/=div;
            auxB/=div;

        auxR=FiltrosEspaciales.validarLimites(auxR+offset);
        auxG=FiltrosEspaciales.validarLimites(auxG+offset);
        auxB=FiltrosEspaciales.validarLimites(auxB+offset);
        color = new Color(auxR,auxG,auxB);

        //color = new Color(auxR+offset,auxG+offset,auxB+offset);
        return color.getRGB();
    }

    public static Image DeteccionBordes(Image io, int[][][] mascara, int div, int offset) {
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        BufferedImage bnuevo[] = new BufferedImage[mascara.length];
        for (int i = 0; i < bnuevo.length; i++){
            bnuevo[i] = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < bi.getWidth(); x++) {
                for (int y = 0; y < bi.getHeight(); y++) {
                    int rgb = calcularNuevoTono(x, y, bi, mascara[i], div, offset);
                    bnuevo[i].setRGB(x, y, rgb);
                }
            }
        }

        BufferedImage bnew = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics g = bnew.getGraphics();
        for (int i = 0; i<bnuevo.length; i++) {
            g.drawImage(bnuevo[i], 0, 0, null);
        }
            return AbrirImagen.toImage(bnew);
    }


    public static int validarValor(int valor){
        if(valor<0) return 0;
        if(valor>255) return 255;
        return valor;
    }

} // end class