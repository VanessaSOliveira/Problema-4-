package View;


import Controller.Controller;
import java.io.IOException;
import javax.swing.JFrame;
import jxl.read.biff.BiffException;



public class Problema4{

    public static void main(String[] args) throws IOException, BiffException {
       Controller controller = new Controller();
       controller.insereNaMatriz("bairros2.xls");
       
    }
}
