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
package View;

import Controller.Controller;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*Essa classe implementa os métodos da view(Papalegua).
    Possui os atributos do mxGraph, mxGraphComponent, controller, vetor de
    object para os vertices, vetor de string para bairros, vetor de inteiros
    para a origem e destino e um vetor de object para as arestas. Além disso,
    possui os métodos de gerar grafo na tela, clique na tela, e os métodos dos
    eventos dos botões da interface gráfica.
    */
public class Papalegua extends javax.swing.JFrame {
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private Controller controller;
    private Object[] vertices;
    private String[] bairros;
    private int[] origemDestino;
    private Object[] arestas;

/*No construtor ele inicializa os atributos do Papalegua, chama o método para
    gerar a matriz a partir do excel, chama os métodos de gerar o grafo na tela,
    do clique na tela e detalhes da interface, como o icone e o nome da
    aplicação.
    */
    public Papalegua() throws IOException{
        super("Papalégua");
        controller = new Controller();
        controller.insereNaMatriz("bairros2.xls");
        
        vertices = new Object[50];
        arestas = new Object[50];
        bairros = new String[50];
        origemDestino = new int[2];
        graph = new mxGraph();
        graphComponent = new mxGraphComponent(graph);  
        
        //gerar grafo
        gerarGrafoNaTela();
        initComponents();
        
        ImageIcon icone = new ImageIcon("papaleguas.png");
        setIconImage(icone.getImage());
        //clique do mouse
        cliqueNaTela();
        setLocationRelativeTo(null);
    }
    
    
    /*Esse método implementa a geração de grafo na tela.
    Inicialmente ele referencia um pai default para o vértice. Depois disso ele
    altera o tamanho do graphComponent e inicializa o modelo do graph. Ao
    inicializar, ele lê um arquivo com as coordenadas de todos os pontos a serem
    adicionados na tela. Ele percorre todas as linhas do arquivo, separa o y e
    x, salvando em uma variável e cria um vértice de acordo com aquela variável.
    Depois disso, ele define o fundo do grafo para ficar transparente e permitir
    a exibição do mapa, também ele faz com que os vértices fiquem fixos na tela 
    e que possam ser selecionados.
    */
    private void gerarGrafoNaTela() throws FileNotFoundException{
        Object parent = graph.getDefaultParent();
        
        graphComponent.setBounds(309,0,753,720);
        
        graph.getModel().beginUpdate();

            int x;
            int y;
            String nome;

            Scanner scanner = new Scanner(new FileReader("coordenadasXYComNomes.txt"))
                       .useDelimiter("\\n");
            String s = "";
            
            for(int i=0;i<50;i++){
                s = scanner.nextLine();
                if(s.length()>0){
                    String[] separa = new String[3];
                    separa = s.trim().split(",");
                    x = Integer.parseInt(separa[0]);
                    y = Integer.parseInt(separa[1]);
                    nome = separa[2];
                    vertices[i]=graph.insertVertex(parent, null, i+1, x, y, 15, 15, "fillColor=lightpink;strokeColor=black;");
                    bairros[i] = nome;
                }
            }
        graph.getModel().endUpdate();
        graphComponent.getViewport().setOpaque(false);
        graph.setCellsEditable(false);
        graph.setCellsLocked(true);
        graph.setCellsSelectable(true);
       
        getContentPane().add(graphComponent);
    }
/*Esse método implementa o clique na tela.
    Inicialmente ele "zera" o vetor de origem atribuindo às duas posições o valor
    máximo do inteiro. Depois disso ele cria uma classe interna passando-a para
    o graphComponent adicionando o Mouse Listener. Essa classe interna recebe o
    evento do mouse e guarda o vértice que foi clicado e verifica, caso não tenha
    uma origem ainda, ele adiciona na origem, caso ja tenha, sempre vai alterar
    o destino, caso seja clicado em mais de um vértice.
    */
    private void cliqueNaTela(){
        origemDestino[0] = Integer.MAX_VALUE;
        origemDestino[1] = Integer.MAX_VALUE;
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter(){

                public void mousePressed(MouseEvent e){
                    Object retorno = graphComponent.getCellAt(e.getX(), e.getY());
                    if(retorno!=null){
                        for(int i=0;i<50;i++){
                            if(vertices[i]==retorno){//verificar qual foi selecionado
                                if(origemDestino[0]==Integer.MAX_VALUE){//se a origem não tiver origem
                                    origemDestino[0]=i;
                                    break;
                                }
                                else{
                                    origemDestino[1]=i;//se tiver origem adiciona no destino
                                    break;
                                }
                            }
                        } 
                    }
                }
            });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        ir = new javax.swing.JButton();
        alterarPreco = new javax.swing.JButton();
        alterarTempo = new javax.swing.JButton();
        mapa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        resetar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ir.setText("Ir");
        ir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irActionPerformed(evt);
            }
        });

        alterarPreco.setText("Alterar Preço ");
        alterarPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarPrecoActionPerformed(evt);
            }
        });

        alterarTempo.setText("Alterar Tempo");
        alterarTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarTempoActionPerformed(evt);
            }
        });

        mapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/mapaSalvador.jpeg"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/papa1.jpg"))); // NOI18N

        resetar.setText("Resetar Caminhos");
        resetar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetarActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return bairros.length; }
            public String getElementAt(int i) { return (i+1)+". "+bairros[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel2.setText("Legenda dos Bairros ");

        jLayeredPane1.setLayer(ir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(alterarPreco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(alterarTempo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(mapa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(resetar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(alterarTempo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alterarPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetar, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel2))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(mapa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1563, 1563, 1563))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapa)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*Esse metodo implementa o evento do botao alterar tempo.
    Inicialmente ele confere se os vértices selecionados são adjacentes, caso sejam,
    é exibida uma popup com uma mensagem que solicita ao usuario que o mesmo digite o 
    novo tempo e salva em uma variável o tempo digitado pelo usuário. Se esse tempo 
    não for nulo, ele altera o tempo na aresta entre aqueles dois vértices.
    Caso os bairros não sejam adjacentes, exibe uma popup com uma mensagem de erro.*/
    private void alterarTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarTempoActionPerformed
       if(controller.isAresta(origemDestino[0], origemDestino[1])){
           String str =JOptionPane.showInputDialog("Digite o tempo:");
           if(str!=null){
                double valor =Double.parseDouble(str);
                controller.alteraTempo(origemDestino[0], origemDestino[1], valor);
           }
       }
       else{
           JOptionPane.showMessageDialog(null, "Não é possivel alterar tempo, bairros não adjacentes! ",
                                                "ERRO", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_alterarTempoActionPerformed

/*Esse método implementa o evento do botão alterar preço.
    Inicialmente ele exibe uma popup com uma mensagem requisitando ao usuario
    o novo preço para o quilômetro rodado. Caso o valor digitado pelo usuario
    não seja null, ele altera o preço quilometro do controller.*/
    private void alterarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarPrecoActionPerformed
        String str = JOptionPane.showInputDialog("Digite o novo preço para o quilômetro rodado:");
        if(str!=null){
            double valor =Double.parseDouble(str);
            controller.setPrecoQuilometro(valor);
        }
    }//GEN-LAST:event_alterarPrecoActionPerformed
/*Esse método implementa o evento do botão ir.
    Inicialmente faz-se uma chamada do método retornaMenorCaminho() do
    controller enviando a origem e o destino como parametro e recebe um array
    com o caminhoMinimo. Após isso, ele percorre o caminhoMinimo criando as
    arestas com os indices correspondentes que está armazenada nesse array.
    Após adicionar todas as arestas, ele exibe uma popup informando o valor da
    distância, tempo do percurso e o valor da corrida.
    */
    private void irActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irActionPerformed
        int[] caminhoMinimo=controller.retonaMenorCaminho(origemDestino[0], origemDestino[1]);
        for(int i=0;i<vertices.length && i<caminhoMinimo.length-1;i++){
            if(caminhoMinimo!=null){
                arestas[i] = graph.insertEdge(null, null, "", vertices[caminhoMinimo[i+1]], vertices[caminhoMinimo[i]]);
            }
        }
        JOptionPane.showMessageDialog(null,
                    "Distancia: " + controller.retornaValorDistancia(origemDestino[0], origemDestino[1])+ " Km" + "\n"+ 
                    "Tempo de percurso: "+ controller.retornaTempoTotal() + " min"+"\n" +
                    "Valor da Corrida: R$ " + controller.getValorTotalCorrida(),"Resultado",JOptionPane.INFORMATION_MESSAGE );

    }//GEN-LAST:event_irActionPerformed
/*Esse método implementa o evento do método de resetar.
    Nesse método os valores de origem e destinos são "zerados" e as arestas são
    removidas do grafo da tela.
    */
    private void resetarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetarActionPerformed
        origemDestino[0] = Integer.MAX_VALUE;
        origemDestino[1] = Integer.MAX_VALUE;
        graph.cellsRemoved(arestas);
    }//GEN-LAST:event_resetarActionPerformed

    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Papalegua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Papalegua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Papalegua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Papalegua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Papalegua papalegua = null ;
                try {
                   papalegua = new Papalegua();
                } catch (IOException ex) {
                    System.out.println("Ocorreu erro no arquivo");
                }
                papalegua.setSize(1110, 730);
                papalegua.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarPreco;
    private javax.swing.JButton alterarTempo;
    private javax.swing.JButton ir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mapa;
    private javax.swing.JButton resetar;
    // End of variables declaration//GEN-END:variables
}
