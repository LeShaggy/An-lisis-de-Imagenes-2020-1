package analisisenfrecuencias.Filtros;

import java.awt.*;

public class FiltroButterworth extends FiltroFrecuencia{

    private Image Filter;
    private int radio, n;
    private Dimension dim;
    private Image imagen;
    private boolean pasa_altas;

    public FiltroButterworth(int radio, int orden, boolean pasa_altas, Dimension dim) {
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.imagen = null;
        this.pasa_altas = pasa_altas;
        this.n = orden;
    }



    @Override
    public void crearFiltro() {

    }
} // End class
