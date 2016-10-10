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
    private Object[][] matrizGrafo;
    private String[] bairros;
    //private Lista vertices;
    
    public Grafo(Object[][] matrizGrafo){
        this.matrizGrafo = matrizGrafo;
        this.bairros=new String[50];
    }
    
    public Grafo(){
        
    }

    public void setBairros(String[] bairros) {
        this.bairros = bairros;
    }
    
    public String getBairros(int idBairro){
        return bairros[idBairro];;
    }
    //só pra testar se a matriz ta gerando
    public  void imprimeGrafo(){
        for (int i = 0; i < matrizGrafo.length; i++) {

            for (int j = 0; j < matrizGrafo[0].length; j++) {

                System.out.print(matrizGrafo[i][j] + "\n");
            }
        }
        
        for(int i=0;i<50;i++){
            System.out.println(bairros[i]);
        }
    }
    
    public static class Vertice{
        private int idVertice;
        private String nome;
        private double menorDistancia;
        private boolean visitado;
        private Lista listaArestas;
        
        public Vertice(int idVertice){
            this.idVertice = idVertice;
        }

        public void insereArestas(Aresta aresta){
            listaArestas.inserirFinal(aresta);
        }
        public Iterador getListaArestas(){
            return listaArestas.iterador();
        }
        
        public String getNome() {
            return nome;
        }

        public double getMenorDistancia() {
            return menorDistancia;
        }

        public void setDistancia(double distancia) {
            this.menorDistancia = distancia;
        }

        public boolean isVisitado() {
            return visitado;
        }

        public void setVisitado(boolean visitado) {
            this.visitado = visitado;
        }
        
        
        
        
    }
    //Olha a aresta pq n sei se é assim msm
    public static class Aresta{
        private int origem;
        private int destino;
        private double pesoDistancia;
        private double pesoTempo;
        private Object[][] matrizGrafoo;
        
        public Aresta(int origem, int destino, Object[][] matrizGrafo){
            this.origem = origem;
            this.destino = destino;
            this.matrizGrafoo = matrizGrafo;
            
        }

        public double getPesoDistancia() {
            return pesoDistancia;
        }

        public void inserePeso(){
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
        }
    }
}
