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
package Util;
/*Essa classe implementa os métodos do Grafo.
    Ela possui como atributo um array de String para bairros, duas matrizes de
    double para distancias e tempo e um atributo double para o tempo total.
    Possui o método do dijkstra, retorna adjacentes, organiza na matriz, 
    getters, setters e contains.
*/
public class Grafo {

    private String[] bairros;
    private double[][] matrizDistancias;
    private double[][] matrizTempo;
    private double tempoTotal;

    public Grafo() {
        this.bairros = new String[50];
        matrizDistancias = new double[50][50];
        matrizTempo = new double[50][50];
    }

/*Esse método implementa o algoritmo de dijkstra. Recebe a origem e o destino
    como parametro.
    Ele cria inicialmente 4 variáveis: uma lista de anteriores, um array de
    visitados e um de tempo e uma lista de caminho. Ele inicializa todas
    posições da lista de tempo no infinito e adiciona a origem no inicio da
    lista de anteriores e iguala no vetor de tempo na posição da origem
    a zero.
    Cria uma variável para guardar o atual e inicialmente a iguala a origem.
    Enquanto o atual for diferente do destino, ele faz os seguinte passos:
    Adiciona no vetor de visitados o atual, pega os adjacentes do atual,
    percorre o vetor de adjacentes e verifica se o adj está na lista de
    visitados, se não tiver ele cria uma variável soma que soma o valor de
    tempo na posiçãoo atual com o valor da matriz de tempo tendo como origem 
    o atual e como destino o adj. Se a soma for menor que o tempo em adj
    ele guarda a soma nessa posição e insere o atual na lista de anteriores.
    Depois de percorrer todos os adjacentes, ele cria uma variável menor, 
    guardando um valor infinito, e percorre novamente a lista de adjacentes
    e verifica se ele não estiver na lista de visitados e o tempo na matriz
    tempo para atual como origem e adj como destino for menor que menor, ele
    iguala menor ao valor dessa posição e muda o atual para adj.
    Ele sai do loop nessa parte quando, ao mudar o atual para adj, o atual
    passar a ser igual o destino.
    Depois de sair do loop ele iguala o atributo de tempo total ao tempo
    armazenado na posição destino. Depois disso, o atual passa a ser o destino,
    ele cria um loop para adicionar o atual na lista de caminho, confere se
    atual for igual a origem, se não for ele continua no loop, se for ele sai.
    Caso não seja, o atual passa a ser o anterior do atual naquele momento.
    Ao terminar esse loop e todos os caminhos tiverem adicionados ele retorna
    a lista de caminhos.
    */
    public ILista dijkstra(int origem, int destino) {
        ILista anteriores = new Lista();
        int[] visitados = new int[50];
        double[] tempo = new double[500];
        ILista caminho = new Lista();

        for (int i = 0; i < 500; i++) {
            tempo[i] = Double.MAX_VALUE;//inicializa as variaveis
        }
        anteriores.inserirInicio(origem);
        tempo[origem] = 0.0;

        int atual = origem;
        int i = 0;
        while (atual != destino) {
            visitados[i] = atual;

            int[] adjacentes = retornaAdjacentes(atual);
            for (int adj : adjacentes) {
                if (!contains(visitados, adj)) {
                    double soma = tempo[atual] + matrizTempo[atual][adj];
                    if (soma < tempo[adj]) {
                        tempo[adj] = soma;
                        anteriores.inserirFinal(atual);
                    }
                }
            }
            double menor = Double.MAX_VALUE;
            for (int adj : adjacentes) {
                if (!contains(visitados, adj) && matrizTempo[atual][adj] < menor) {
                    menor = matrizTempo[atual][adj];
                    atual = adj;
                }
            }
            i++;
        }
        
        tempoTotal = tempo[destino];
        i = 0;
        atual = destino;
        while (true) {
            caminho.inserirFinal(atual);
            if (atual == origem) {
                break;
            }
            atual = (int) anteriores.recuperar(atual);
            i++;
        }

        return caminho;
    }
/*Esse método implementa o retorno dos vértices adjacentes. Recebe um vertice
    origem como parametro.
    Ele percorre a lista e caso aquela posição seja diferente de 0 ele
    adiciona a mesma no final de uma lista e após isso ele converte a lista em
    um array de inteiros para retornar.
    */
    private int[] retornaAdjacentes(int origem) {
        Fila listaAdjacentes = new Fila();
        for (int i = 0; i < 50; i++) {
            if (matrizTempo[origem][i] != 0.0 && matrizDistancias[origem][i] != 0.0) {
                listaAdjacentes.inserirFinal(i);
            }
        }
        int[] indicesAdjacentes = new int[listaAdjacentes.obterTamanho()];
        for (int i = 0; i < indicesAdjacentes.length; i++) {
            indicesAdjacentes[i] = (int) listaAdjacentes.removerInicio();
        }

        return indicesAdjacentes;
    }
    //Altera a String[] de bairros
    public void setBairros(String[] bairros) {
        this.bairros = bairros;
    }
    //Retorna o bairro de um certo indice
    public String getBairros(int idBairro) {
        return bairros[idBairro];
    }
/*Esse método implementa a organização das matrizes. Ele recebe uma matriz de
    grafo como parametro.
    Ele percorre a matriz do grafo e separa a informação de distancia e tempo
    com o split e adiciona cada um em sua respectiva matriz de distancias e
    de tempo.
    */
    public void organizaMatrizes(Object[][] matrizGrafo) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                String temp = (String) matrizGrafo[i][j];
                String[] separa = temp.trim().split(",");
                if (separa.length <= 1) {
                    matrizDistancias[i][j] = 0.0;
                    matrizTempo[i][j] = 0.0;
                } else {
                    String distancia = separa[0];
                    String tempo = separa[1];
                    matrizDistancias[i][j] = Double.parseDouble(distancia.substring(distancia.indexOf("=") +
                                                                1, distancia.length()));
                    matrizTempo[i][j] = Double.parseDouble(tempo.substring(tempo.indexOf("=") + 1, tempo.length()));
                }
            }
        }
    }
    //Altera o tempo na matrizTempo
    public void alteraTempo(int origem, int destino, double valor) {
        matrizTempo[origem][destino] = valor;
        matrizTempo[destino][origem] = valor;
    }
    //Retorna se é aresta
    public boolean isAresta(int origem, int destino) {
        return matrizDistancias[origem][destino] != 0.0 && matrizTempo[origem][destino] != 0.0;
    }
    //Retorna a distancia entre a origem e o destino
    public double getDistancia(int origem, int destino) {
        return matrizDistancias[origem][destino];
    }
    //Retorna o tempo total
    public double getTempo() {
        return tempoTotal;
    }
    //Esse método retorna se contem certo objeto no dado array
    private boolean contains(int[] array, Object obj) {

        for (Object o : array) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }
}