package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import javax.swing.JFileChooser;
import jxl.*;


public class Grafo {
    private String[] bairros;
    private double[][] matrizDistancias;
    private double[][] matrizTempo;
    
    public Grafo(){
        matrizDistancias = new double[50][50];
        matrizTempo = new double[50][50];
        bairros = new String[50];
    }

    public void setBairros(String[] bairros) {
            this.bairros = bairros;
    }
    
    public String getBairros(int idBairro){
        return bairros[idBairro];
    }
    
    public boolean isAresta(int x, int y){
        return matrizTempo[x][y]!=0.0;
    }
    
    public void organizaMatrizes(Object[][] matrizGrafo){
        
            for(int i=0; i<50;i++){
                for(int j = 0; j<50;j++){
                        String temp = (String) matrizGrafo[i][j];
                        if(temp!=""){
                            String[] separa = new String[3];
                            separa = temp.trim().split(",");
                            String distancia = separa[0];
                            String tempo = separa[1];
                            //Essa parte ta bugando n sei pq, o parseDouble é pra transformar em double e a substring é pra pegar só o q ta depois do =
                            matrizDistancias[i][j] = Double.parseDouble(distancia.substring(distancia.indexOf("=") + 1, distancia.length()));
                            matrizTempo[i][j] = Double.parseDouble(tempo.substring(tempo.indexOf("=") + 1, tempo.length())); 
                        }
                        else{
                            matrizDistancias[i][j]=0.0;
                            matrizTempo[i][j]=0.0;
                        }
                    } 
                }
    }
    //n sei se esse metodo fica aqui ou em aresta  
    public void alteraTempo(int origem,int destino, double valor){
        matrizTempo[origem][destino] = valor;
        matrizTempo[destino][origem] = valor;
    }
   
    public  void imprimeGrafo(){
        for (int i = 0; i < matrizDistancias.length; i++) {

            for (int j = 0; j < matrizDistancias[0].length; j++) {

                System.out.print(matrizDistancias[i][j] + "\n");
            }
        }
        
        for(int i=0;i<50;i++){
            System.out.println(bairros[i]);
        }
    }
  
    //n sei se vai precisar de aresta
    private class Aresta{
        private int origem;
        private int destino;
        
        public Aresta(int origem, int destino, Object[][] matrizGrafo){
            this.origem = origem;
            this.destino = destino; 
        }
        
       /* public void organizaMatrizes(Object[][] matrizGrafo){
            for(int i=0; i<50;i++){
                for(int j = 0; j<50;j++){
                        String temp = (String) matrizGrafo[i][j];
                        String[] separa = temp.trim().split(",");
                        String distancia = separa[0];
                        String tempo = separa[1];
                        matrizDistancias[i][j] = Double.parseDouble(distancia.substring(distancia.indexOf("=") + 1, distancia.length()));
                        matrizTempo[i][j] = Double.parseDouble(tempo.substring(tempo.indexOf("=") + 1, tempo.length())); 
                    }
                    
                }
            }
        }*/
    
        public boolean isAresta(int origem,int destino){
            if(matrizDistancias[origem][destino]!=0 && matrizTempo[origem][destino]!=0){
                return true;
            }
            else{
                return false;
            }
        }

        public double getDistancia(int origem, int destino) {
            return matrizDistancias[origem][destino];
        }

       /* public void inserePeso(){
            for(int i=0; i<50;i++){
                for(int j = 0; j<50;j++){
                    if(i==origem && j==destino){
                        String temp = (String) matrizGrafoo[i][j];
                        String[] separa = temp.trim().split(",");
                        String distancia = separa[0];
                        String tempo = separa[1];
                        pesoDistancia = Double.parseDouble(distancia.substring(distancia.indexOf("=") + 1, distancia.length()));
                        pesoTempo = Double.parseDouble(tempo.substring(tempo.indexOf("=") + 1, tempo.length())); 
                    }
                    
                }
            }
        }

        public double getPesoTempo() {
            return pesoTempo;
        }
        
        public void setPesoTempo(int tempo){
            pesoTempo = tempo;
        }*/
    }
}