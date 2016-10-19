package Controller;

import Util.Grafo;
import Util.ILista;
import Util.Iterador;
import Util.Lista;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Controller {
    private Object[][] matrizGrafo;
    private String[] bairros;
    //private int[] distancias;
    private Grafo grafo;
    private double precoQuilometro;
    private Lista arestasMenoresTempo;//Seria bom pra retornar as arestas de menor caminho, mas ppodem ser os vertices tbm
    private double distanciaDoMenorTempo;
    private double menorTempo;//n sei se vai precisar

    
    public Controller(){
       grafo = new Grafo();
       matrizGrafo = new Object[50][50];
       bairros = new String[50];
       //distancias = new int[50];
       precoQuilometro = 1.0;       
    }
    
    public double getPrecoQuilometro(){
        return precoQuilometro;
    }
    public void setPrecoQuilometro(double valor){
        precoQuilometro = valor;
    }
    //retorna valor da corrida
    public String retornaValorTotalCorrida(){
        return String.format("%.2f", precoQuilometro * distanciaDoMenorTempo);
    }
    
    public String retornaTempoTotal(){
        return String.format("%.2f", grafo.getTempo());
    }
    //verifica pq acho q ta errado//tem q colocar no dijkstra e depois adicionar a um atributo e ter um get pra retornar
    public String retornaValorDistancia(int origem,int destino){
        double total=0;
        ILista lvertices = grafo.dijkstra(origem, destino);
        int[]  vertices = toArray(lvertices);
        for(int i=0;i<vertices.length-1;i++){
            //for(int j=i+1;j<vertices.length;j++){
                total+= grafo.retornoDistanciaMatriz(vertices[i],vertices[i+1]);
            //}
        }
        distanciaDoMenorTempo = total;
        return String.format("%.2f", total);
    }
    public int[] toArray(ILista lista){
        Iterador it = lista.iterador();
        int[] array = new int[lista.obterTamanho()];
        int i=0;
        while(it.temProximo()){
            array[i]=(int) it.obterProximo();
            i++;
        }
        return array;
    }
      
    public void insereNaMatriz(String nomeArquivo) throws IOException{
        
        try {
            //Pega planilha com todas as abas
            Workbook workbook = Workbook.getWorkbook(new File(nomeArquivo));
            //Pega apenas a primeira aba da planilha
            Sheet aba = workbook.getSheet(0);
            Cell celulaTabela;
            Cell celula;
            for(int i = 0; i<matrizGrafo.length; i++){
                celula = aba.getCell(i+1, 0);
                bairros[i] = celula.getContents();
                for(int j = 0; j< matrizGrafo.length;j++){
                    celulaTabela = aba.getCell(i+1, j+1);
                    matrizGrafo[i][j] = celulaTabela.getContents();
                } 
            }
            workbook.close();
            
            grafo.setBairros(bairros);
            grafo.organizaMatrizes(matrizGrafo);
            //grafo.imprimeGrafo();
        } catch (BiffException ex) {
            
        }
        
    }
    public boolean isAresta(int origem,int destino){
        return matrizGrafo[origem][destino]!=null;
    }
     public String getBairros(int idBairro){
        return bairros[idBairro];
    }
    
    public int[] retonaMenorCaminho(int origem, int destino){
        return toArray(grafo.dijkstra(origem, destino)); 
    }
    public void alteraTempo(int origem,int destino, double novoValor){
            if(matrizGrafo[origem][destino]!= null){
                grafo.alteraTempo(origem, destino, novoValor);
            }
            else{
                //Nao sao adjacentes
            }
    }
    
}
