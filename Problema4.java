package View;

import Util.Grafo;
import java.io.IOException;
import javax.swing.JFrame;
import jxl.read.biff.BiffException;



public class Problema4{

    public static void main(String[] args) throws IOException, BiffException {
        TelaPrincipalFrame mapa = new TelaPrincipalFrame();
        mapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapa.setSize(530, 260);
       // mapa.setSize(1400,900);
        mapa.setVisible(true);
        
        /*Grafo grafo = new Grafo();
        grafo.addGrafo();
        grafo.imprimeGrafo();*/
    }
    
}
