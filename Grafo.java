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
    
    public Grafo(){
        
    }
    //Fiz essa parte de adicionar na matriz de duas formas , a q ta aqui em baixo e a q ta como comentario
    public void addGrafo() throws IOException{
        try {
        Workbook workbook = Workbook.getWorkbook(new File("bairros2.xls"));
            
            Sheet[] abas = workbook.getSheets();
            Sheet aba = workbook.getSheet(0);
            matrizGrafo = new Object[aba.getRows()][aba.getColumns()];

            Cell[] celulaTabela;
            for(int i = 0; i<matrizGrafo.length; i++){
                celulaTabela = aba.getRow(i+1);
                for(int j = 0; j< matrizGrafo[0].length;j++){
                    matrizGrafo[i][j] = celulaTabela[j].getContents();
                }
            }
        } catch (BiffException ex) {
           
        }
        
    }/*
    public void addGrafo() throws IOException, BiffException{
        try{
            Workbook workbook = Workbook.getWorkbook(new File("bairros2.xls"));
            //Sheet[] abas = workbook.getSheets();
            //Sheet aba = tabela.getSheet(0);
            
            Sheet sheet = workbook.getSheet(0);
            Cell cel;
            int linhas = sheet.getRows();
            int colunas = sheet.getColumns();
            matrizGrafo = new Object[linhas][colunas];
            
            for(int i = 0; i < linhas; i++){
                for(int j= 0; j<colunas;j++){
                    cel = sheet.getCell(j+1, i+1);
                    if (cel.getType() == CellType.EMPTY){
                        matrizGrafo[i][j] = null;
                    }
                    else{
                        matrizGrafo[i][j] = cel.getContents();
                        
                        Aresta aresta = new Aresta(i,j);
                        aresta.inserePeso();
                    }
                }
            }
            workbook.close();
        }catch(Exception e) {
    
        }
    }*/
    
    public  void imprimeGrafo(){
        for (int i = 0; i < matrizGrafo.length; i++) {

            for (int j = 0; j < matrizGrafo[0].length; j++) {

                System.out.print(matrizGrafo[i][j] + "\n");
            }
        }
    }
    //Olha a aresta pq n sei se é assim msm
    private class Aresta{
        private int origem;
        private int destino;
        private double pesoDistancia;
        private double pesoTempo;
        
        public Aresta(int origem, int destino){
            this.origem = origem;
            this.destino = destino;
            
        }

        public double getPesoDistancia() {
            return pesoDistancia;
        }

        public void inserePeso(){
            for(int i=0; i<50;i++){
                for(int j = 0; j<50;j++){
                    if(i==origem && j==destino){
                        String temp = (String) matrizGrafo[i][j];
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
    }
}
