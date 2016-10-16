package Controller;

import Util.Fila;
import Util.Grafo;
import Util.Lista;
import View.*;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Controller {
    private Object[][] matrizGrafo;
    private String[] bairros;//n sei se vai precisar
    private Grafo grafo;
    private Double precoQuilometro;//Como inicializar o preço, ou pede pra q sempre o usuario digite antes de escolher o trajeto
    private Lista arestasMenoresTempo;//Seria bom pra retornar as arestas de menor caminho, mas ppodem ser os vertices tbm
    private double distanciaDoMenorTempo;
    private double menorTempo;//n sei se vai precisar
   
    
    public Controller(){
       matrizGrafo = new Object[50][50];
       bairros = new String[50];
    }
    
    public boolean isAresta(int x, int y){
        return matrizGrafo[x][y]!=null;
    }
        
    public double getPrecoQuilometro(){
        return precoQuilometro;
    }
    public void setPrecoQuilometro(double valor){
        precoQuilometro = valor;
    }
    //retorna valor da corrida
    public double valorTotalCorrida(){
        double total;
        total = distanciaDoMenorTempo * precoQuilometro;
        return total;
    }
   
    public void insereNaMatriz(String nomeArquivo/*, String[] bairros*/) throws IOException{
        
        try {
            //Pega planilha com todas as abas
            Workbook workbook = Workbook.getWorkbook(new File(nomeArquivo));
            //Pega apenas a primeira aba da planilha
            Sheet aba = workbook.getSheet(0);
            Cell celulaTabela;
            Cell celula;
            for(int i = 0; i<matrizGrafo.length; i++){
                celula = aba.getCell(i+1, 0);
                //this.bairros[i] = celula.getContents();
                for(int j = 0; j< matrizGrafo.length;j++){
                    celulaTabela = aba.getCell(j+1, i+1);
                    matrizGrafo[i][j] = celulaTabela.getContents();
                }
            }
            workbook.close();
            
            //grafo.setBairros(bairros);
        } catch (BiffException ex) {
           
        }
        //grafo.setBairros(bairros);
         //   grafo.setBairros(bairros);
        
        //controller.insereNaMatriz("bairros2.xls");
        
    }
    public String retornaVertice(int indice){
       return grafo.getBairros(indice);
    }
    
    // n sei se o dijkstra retorna o vetor com os vertices ou o valor do tempo minimo
    public String[] dijkstra(int origem, int destino){
        String[] anterioresMenor = new String[50];
        int[] visitados = new int[50];
        double[] distanciasTemp = new double[50]; //Distancia temporaria, provavelmente a distancia dos adjacentes
        double[] distanciasPerm = new double[50];//Distancia final
        
        for(int i=0;i<50;i++){
            distanciasTemp[i] = Double.MAX_VALUE;
            distanciasPerm[i] = Double.MAX_VALUE;
            visitados[i]=0;
        }
        distanciasTemp[origem] = 0;
        distanciasPerm[origem] = 0;
        
        
        return null;
    }
    
    public void alteraTempo(int origem,int destino, double novoValor){
            if(matrizGrafo[origem][destino]!= null){
                grafo.alteraTempo(origem, destino, novoValor);
            }
            else{
                //Nao sao adjacentes
            }
    }
    
    public int[] retornaAdjacentes(int origem){//Quem chamar esse metodo tem que tratar se algum adjacente é igual ao destino
        Fila filaAdjacentes = null;
        for(int i=0;i<50;i++){
            if(matrizGrafo[origem][i]!= null){
                filaAdjacentes.inserirFinal(i);
            }
        }
        int[] indicesAdjacentes = new int[filaAdjacentes.obterTamanho()];
        for(int i=0;i<indicesAdjacentes.length;i++){
            indicesAdjacentes[i] = (int) filaAdjacentes.removerInicio();       
        }
        
        return indicesAdjacentes;
    }
    
  
    public void imprimeMatriz(){
        grafo = new Grafo();
        grafo.organizaMatrizes(matrizGrafo);
       
        //public  void imprimeGrafo(){
        for (int i = 0; i < matrizGrafo.length; i++) {

            for (int j = 0; j < matrizGrafo.length; j++) {

                System.out.print(matrizGrafo[i][j] + "\n");
            }
        }
       // grafo.imprimeGrafo();
    }
}