/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Emille Victória Sampaio Guedes e Vanessa de Souza de Oliveira
 * Data:  20/10/2016
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package Controller;

import Util.Grafo;
import Util.ILista;
import Util.Iterador;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*Essa classe implementa os métodos do controller.
    Possui como atributo um array de String para bairros, um grafo, um atributo
    para o preço do quilometro e distancia do menor tempo. Possui os métodos
    de inserir na matriz, retornar o valor da distancia, alterar tempo,
    retornar menor caminho, retornar tempo total getters e setters dos atributos
    e to array.
    */
public class Controller {
    private String[] bairros;
    private Grafo grafo;
    private double precoQuilometro;
    private double distanciaDoMenorTempo;
    
    public Controller(){
       grafo = new Grafo();
       bairros = new String[50];
       precoQuilometro = 1.0;       
    }
    
/*Esse método implementa a inserção dos valores da planilha no grafo.
    Inicialmente ele instancia uma matriz 50 por 50 para a posição das arestas.
    Ele pega a planilha com todas as abas, depois pela apenas a primeira aba
    da planilha, pega o conteúdo de cada posição da tabela, adiciona na matriz
    e adiciona o nome de cada bairro no array de bairros. Depois, ele adiciona
    no grafo o array de bairros e envia a matriz para ele separar os pesos.
    */
    public void insereNaMatriz(String nomeArquivo) throws IOException{
        Object[][] matrizGrafo = new Object[50][50];
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
        } catch (BiffException ex) {
            
        } 
    }
/*Esse método implementa o retorno do valor da distancia. Recebe como parametro
    a origem e o destino.
    Ele faz a chamada do dijkstra e converte a lista recebida um array,
    percorre o mesmo e vai somando o valor da distancia para retornar.
    */
    public String retornaValorDistancia(int origem,int destino){
        double total=0;
        ILista lvertices = grafo.dijkstra(origem, destino);
        int[]  vertices = toArray(lvertices);
        for(int i=0;i<vertices.length-1;i++){
            total+= grafo.getDistancia(vertices[i],vertices[i+1]);
        }
        distanciaDoMenorTempo = total;
        return String.format("%.2f", total);
    }
/*Esse método implementa a alteração do tempo. Ele recebe como parametro a
    origem, destino e o novo valor de tempo.
    Ele faz chamada do método alterar tempo do grafo passando como parametro
    os valores que recebe de origem, destino e tempo.
    */
    public void alteraTempo(int origem,int destino, double novoValor){
        grafo.alteraTempo(origem, destino, novoValor);    
    }
/*Esse método implementa o métood que retorna o menor caminho. Ele recebe como
    parametro a origem e o destino.
    Ele retorna um array da chamada do método de dijkstra do grafo, passando
    os valores de origem e destino como parametro.   
    */
    public int[] retonaMenorCaminho(int origem, int destino){
        return toArray(grafo.dijkstra(origem, destino)); 
    }
/*Esse método implementa o retorno do tempo total.
    Ele retorna uma String com o tempo do grafo, que é o tempo total entre a
    origem e o destino.
    */
    public String retornaTempoTotal(){
        return String.format("%.2f", grafo.getTempo());
    }
    //Retorna se é aresta
    public boolean isAresta(int origem,int destino){
        return grafo.isAresta(origem, destino);
    }
    //Retorna o preço do quilometro
    public double getPrecoQuilometro(){
        return precoQuilometro;
    }
    //Altera o preço do quilometro
    public void setPrecoQuilometro(double valor){
        precoQuilometro = valor;
    }
    //Retorna valor da corrida
    public String getValorTotalCorrida(){
        return String.format("%.2f", precoQuilometro * distanciaDoMenorTempo);
    }
    //Retorna o bairro de certo id
     public String getBairros(int idBairro){
        return bairros[idBairro];
    }
     //Converte uma lista para array
    private int[] toArray(ILista lista){
        Iterador it = lista.iterador();
        int[] array = new int[lista.obterTamanho()];
        int i=0;
        while(it.temProximo()){
            array[i]=(int) it.obterProximo();
            i++;
        }
        return array;
    }
}
