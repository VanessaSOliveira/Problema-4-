package Controller;

import Util.Grafo;
import Util.Grafo.Aresta;
import Util.Grafo.Vertice;
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
    private String[] bairros;
    private Grafo grafo;
    
    public Controller(){
       matrizGrafo = new Object[50][50];
       bairros = new String[50];
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
                    celulaTabela = aba.getCell(j+1, i+1);
                    matrizGrafo[i][j] = celulaTabela.getContents();
                }
            }
            workbook.close();
            grafo = new Grafo(matrizGrafo);
            grafo.setBairros(bairros);
        } catch (BiffException ex) {
           
        }
        
    }
    
    public String[] dijkstra(int origem, int destino){
        String[] anterioresMenor = new String[50];//Guarda menor caminho
        int[] adjacentes = new int[50];//Coloca 1 se for adjacente
        int[] visitados = new int[50];//coloca 1 se ja foi visitado
        double[] distancias = new double[50]; //Se n for alcançavel coloca infinito
        int atual;
        double novaDistancia=0;
        
        for(int i=0; i<50; i++){
            adjacentes[i]=0;
            visitados[i]= 0;
            distancias[i]= 99999;
        }
        distancias[origem]= 0;
        anterioresMenor[0] = bairros[origem]; 
        atual = origem;
        double dA = distancias[atual];
            int i=atual;
            for(int j=atual;j!=destino;j++){
                if(matrizGrafo[i][j]!=null){
                    adjacentes[j]=1;
                    Aresta aresta = new Aresta(i,j,matrizGrafo);
                    aresta.inserePeso();
                    Vertice vertice = new Vertice(i);
                    vertice.insereArestas(aresta);
                    distancias[i] = aresta.getPesoTempo();
                }
            }
            int a=0;
            
            while(adjacentes[a]==1){
                novaDistancia +=distancias[a];
                if(novaDistancia<distancias[a]){
                    
                }
                
                a++;
            }
        
        
        return null;
    }
  
   //Vi na internet esse
    /*public Lista dijkstraMenorCaminho(int origem,int destino){//tem q ser static
        for(int i=0;i<50;i++){
            anteriores[i]=-1;
            distancias[i]=99999;//simboliza infinito
            visitados[i]=0;//ainda n foram visitados
        }
        distancias[origem] = 0;
        
        return null;
    }
    //procura vetice mais proximo q ainda n foi visitado
    private int procuraVerticeMaisProximo(double[] distancias, int[] visitados){
        int menor=-1;
        boolean primeiro = true;
        for(int i=0;i<50;i++){
            if(distancias[i]<99999 && distancias[i]>=0 && visitados[i]==0){
                if(primeiro){
                    menor=i;
                    primeiro=false;
                }
                else if(distancias[menor]>distancias[i]){
                    menor=i;
                }
            }
        }
       
        
        return 0;
    }*/
    
    
}
//Encontrei na internet tbm
/*// Atributos usados na funcao encontrarMenorCaminho

        // Lista que guarda os vertices pertencentes ao menor caminho encontrado
        List<Vertice> menorCaminho = new ArrayList<Vertice>();

        // Variavel que recebe os vertices pertencentes ao menor caminho
        Vertice verticeCaminho = new Vertice();

        // Variavel que guarda o vertice que esta sendo visitado
        Vertice atual = new Vertice();

        // Variavel que marca o vizinho do vertice atualmente visitado
        Vertice vizinho = new Vertice();

        // Lista dos vertices que ainda nao foram visitados
        List<Vertice> naoVisitados = new ArrayList<Vertice>();

        // Algoritmo de Dijkstra
        public List<Vertice> encontrarMenorCaminhoDijkstra(Grafo grafo, Vertice v1,
                        Vertice v2) {

                // Adiciona a origem na lista do menor caminho
         372       menorCaminho.add(v1);

                // Colocando a distancias iniciais
                for (int i = 0; i < grafo.getVertices().size(); i++) {

                        // Vertice atual tem distancia zero, e todos os outros,
                        // 9999("infinita")
                        if (grafo.getVertices().get(i).getDescricao()
                                        .equals(v1.getDescricao())) {

                                grafo.getVertices().get(i).setDistancia(0);

                        } else {

                                grafo.getVertices().get(i).setDistancia(9999);

                        }
                        // Insere o vertice na lista de vertices nao visitados
                        this.naoVisitados.add(grafo.getVertices().get(i));
                }

                Collections.sort(naoVisitados);

                // O algoritmo continua ate que todos os vertices sejam visitados
                while (!this.naoVisitados.isEmpty()) {

                        // Toma-se sempre o vertice com menor distancia, que eh o primeiro
                        // da
                        // lista

                        atual = this.naoVisitados.get(0);
                        System.out.println("Pegou esse vertice:  " + atual);
                        /*
                         * Para cada vizinho (cada aresta), calcula-se a sua possivel
                         * distancia, somando a distancia do vertice atual com a da aresta
                         * correspondente. Se essa distancia for menor que a distancia do
                         * vizinho, esta eh atualizada.
                         */
                      /*  for (int i = 0; i < atual.getArestas().size(); i++) {

                                vizinho = atual.getArestas().get(i).getDestino();
                                System.out.println("Olhando o vizinho de " + atual + ": "
                                                + vizinho);
                                if (!vizinho.verificarVisita()) {

                                        // Comparando a distância do vizinho com a possível
                                        // distância
                                        if (vizinho.getDistancia() > (atual.getDistancia() + atual
                                                        .getArestas().get(i).getPeso())) {

                                                vizinho.setDistancia(atual.getDistancia()
                                                                + atual.getArestas().get(i).getPeso());
                                                vizinho.setPai(atual);

                                                /*
                                                 * Se o vizinho eh o vertice procurado, e foi feita uma
                                                 * mudanca na distancia, a lista com o menor caminho
                                                 * anterior eh apagada, pois existe um caminho menor
                                                 * vertices pais, ateh o vertice origem.
                                                 */
                                               /* if (vizinho == v2) {
                                                        menorCaminho.clear();
                                                        verticeCaminho = vizinho;
                                                        menorCaminho.add(vizinho);
                                                        while (verticeCaminho.getPai() != null) {

                                                                menorCaminho.add(verticeCaminho.getPai());
                                                                verticeCaminho = verticeCaminho.getPai();

                                                        }
                                                        // Ordena a lista do menor caminho, para que ele
                                                        // seja exibido da origem ao destino.
                                                        Collections.sort(menorCaminho);

                                                }
                                        }
                                }

                        }
                        // Marca o vertice atual como visitado e o retira da lista de nao
                        // visitados
                        atual.visitar();
                        this.naoVisitados.remove(atual);
                        /*
                         * Ordena a lista, para que o vertice com menor distancia fique na
                         * primeira posicao
                         */

                       /* Collections.sort(naoVisitados);
                        System.out.println("Nao foram visitados ainda:"+naoVisitados);

                }

                return menorCaminho;
        }

}*/