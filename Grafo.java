package Util;

public class Grafo {
    private String[] bairros;
    private double[][] matrizDistancias;
    private double[][] matrizTempo;
    //private Lista vertices;
    
    public Grafo(){
        this.bairros=new String[50];
        matrizDistancias = new double[50][50];
        matrizTempo = new double[50][50];
    }
    
    public int[] dijkstra(int origem, int destino){
        int[] anteriores = new int[50];
        int[] visitados = new int[50];
        double[] tempo = new double[500];
        int[] caminho = new int[50];
        
        for(int i=0;i<500;i++){
            tempo[i]=Double.MAX_VALUE;//inicializa as variaveis
        }
        anteriores[origem]=origem;
        tempo[origem]=0.0;
        
        int atual = origem;
        int i = 0;
        while(atual!=destino){
            visitados[i]=atual;

            int[] adjacentes = retornaAdjacentes(atual);
            for(int adj:adjacentes){
                if(!contains(visitados, adj)){
                    double soma =tempo[atual]+matrizTempo[atual][adj];
                    if(soma<tempo[adj]){
                        tempo[adj] = soma;
                        anteriores[adj]=atual;
                    }
                }
            }

            double menor = Double.MAX_VALUE;
            for(int adj:adjacentes){
                if(!contains(visitados, adj) && matrizTempo[atual][adj]<menor){
                    menor = matrizTempo[atual][adj];
                    atual = adj;
                }
            }

            i++;
        }
        
        double tempoTotal = tempo[destino];
        i=0;
        while(true){
            caminho[i] = atual;
            if(atual==origem){
                break;
            }            
            atual = anteriores[atual];
            i++;
        }
        
        return caminho;
    }
        
    public int[] retornaAdjacentes(int origem){//Quem chamar esse metodo tem que tratar se algum adjacente é igual ao destino
        Fila listaAdjacentes = new Fila();
        for(int i=0;i<50;i++){
            if(matrizTempo[origem][i]!= 0.0){
                listaAdjacentes.inserirFinal(i);
            }
        }
        int[] indicesAdjacentes = new int[listaAdjacentes.obterTamanho()];
        for(int i=0;i<indicesAdjacentes.length;i++){
            indicesAdjacentes[i] = (int) listaAdjacentes.removerInicio();       
        }
        
        return indicesAdjacentes;
    }
    public void setBairros(String[] bairros) {
        this.bairros = bairros;
    }
    
    public String getBairros(int idBairro){
        return bairros[idBairro];
    }
    
    public void organizaMatrizes(Object[][] matrizGrafo){
            for(int i=0; i<50;i++){
                for(int j = 0; j<50;j++){
                        String temp = (String) matrizGrafo[i][j];
                        String[] separa = temp.trim().split(",");
                        if(separa.length<=1){
                            matrizDistancias[i][j]=0.0;
                            matrizTempo[i][j]=0.0;
                        }
                        else{
                            String distancia = separa[0];
                            String tempo = separa[1]; 
                            //Essa parte ta bugando n sei pq, o parseDouble é pra transformar em double e a substring é pra pegar só o q ta depois do =
                            matrizDistancias[i][j] = Double.parseDouble(distancia.substring(distancia.indexOf("=") + 1, distancia.length()));
                            matrizTempo[i][j] = Double.parseDouble(tempo.substring(tempo.indexOf("=") + 1, tempo.length())); 
                        }
                } 
            }
    }
    
    //n sei se esse metodo fica aqui ou em aresta  
    public void alteraTempo(int origem,int destino, double valor){
        matrizTempo[origem][destino] = valor;
        matrizTempo[destino][origem] = valor;
    }
    
    public boolean isAresta(int origem,int destino){
        return matrizDistancias[origem][destino]!=0.0 && matrizTempo[origem][destino]!=0.0;
    }

    public double getDistancia(int origem, int destino) {
        return matrizDistancias[origem][destino];
    }
    public double getTempo(int origem, int destino){
        return matrizTempo[origem][destino];
    }
    //so pra testar
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
    
    private boolean contains(int[] array, Object obj){
        
        for(Object o:array){
            if(o.equals(obj)){
                return true;
            }
        }
        
        return false;
    }
}

/*package Util;

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
        }
    
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
        }
    }
}*/