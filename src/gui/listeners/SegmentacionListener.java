package gui.listeners;

import analisisespecial.FiltrosEspaciales;
import gui.JFrameSegmentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SegmentacionListener implements ActionListener {


    private JFrameSegmentacion frame;

    public SegmentacionListener(JFrameSegmentacion frame) {
        this.frame = frame;

    }

    public void actionPerformed(ActionEvent e) {
        int u1 = this.frame.getJSliderU1().getValue();
        int u2 = this.frame.getJSliderU2().getValue();
        Image res = FiltrosEspaciales.
                segmentarImagen(this.frame.getImagenEscalada(), u1, u2);
        this.frame.getLabelImagen().setIcon(new ImageIcon(res));
    }

}