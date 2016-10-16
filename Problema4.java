package View;


import Controller.Controller;
import java.io.IOException;
import javax.swing.JFrame;
import jxl.read.biff.BiffException;



public class Problema4{

    public static void main(String[] args) throws IOException, BiffException {
       /* System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        TelaPrincipalFrame mapa = new TelaPrincipalFrame();
        
        //mapa.grafoDesenho();
        mapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapa.setSize(530, 260);
        mapa.setVisible(true);
    }
    */
       Controller controller = new Controller();
       controller.insereNaMatriz("bairros2.xls");
       controller.imprimeMatriz();
       //controller.imprimeBairros();
       
    }
}

