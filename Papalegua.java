
package View;

import Controller.Controller;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Cliente
 */
public class Papalegua extends javax.swing.JFrame {
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private JTextField texto;
   // private JButton botaoAdd;
    private Controller controller;
    private Object[] vertices;
    private String[] bairros;
   // private MouseListener mouse;
    private MouseEvent eventoMouse;
    private int[] origemDestino;

    public Papalegua() throws IOException{
        super("Papalégua");
        controller = new Controller();
        controller.insereNaMatriz("bairros2.xls");
        
        vertices = new Object[50];
        bairros = new String[50];
        origemDestino = new int[2];
        //inicializa();//ideia
        
        //setLocationRelativeTo(null);
        
        graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);  
        graphComponent.setBounds(309,0,753,720);
        
        //graphComponent.setBackground(Color.TRANSPARENT);
        //graphComponent.setImportEnabled(true);
        //310965   650  //499   748
       // mxGraph.setCellStyle ("ROUNDED", "vertices");
        graph.getModel().beginUpdate();
            //adicionaCoordenadas(); //ideia
           /* for(int i=0;i<50;i++){
                vertices[i]=graph.insertVertex(parent, null, controller.retornaVertice(i),coordenadasX[i] , coordenadasY[i],
                    80, 30);
                       // controller.retornaVertice(i);
            }*/
            /*Object v1 = graph.insertVertex(parent, null, "Aguas_Claras", 20, 20, 80,
                    30,";strokeColor=red; fillColor=red");*/
           /* vertices[0]=graph.insertVertex(parent, null, "Aguas_Claras", 284.50, 325.50, 80,
                    30,"ROUNDED;strokeColor=red; fillColor=red");
            vertices[1] = graph.insertVertex(parent, null, "Massaranduba",70 , 95,
                    80, 30);
            graph.insertEdge(parent, null, "Caminho", vertices[0], vertices[1]);*/
           
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
            //controller.setBairros(bairros);
            //scanner.close();
            
            /*for(int i=0;i<50;i++){
                for(int j=i+1;j<50;j++){
                    boolean testa = controller.isAresta(i, j);
                    if(testa==true){
                        graph.insertEdge(parent, null, "", vertices[i], vertices[j]);
                    }
                }
            }*/
            
   
            
           // controller.insereNaMatriz("bairros2.xls");//antes era só o arquivo os bairros n
        graphComponent.getViewport().setOpaque(false);//sobrepôs o grafo
        graph.getModel().endUpdate();
        //graph.setCellsMovable(false);
        graph.setCellsEditable(false);
        graph.setCellsLocked(true);
        graph.setCellsSelectable(true);
       
        getContentPane().add(graphComponent);
        initComponents();
        
        ImageIcon icone = new ImageIcon("papaleguas.png");
        setIconImage(icone.getImage());
        
        //setLocationRelativeTo(null);
        //ir.setEnabled(false);
        //graphComponent.addMouseListener(mouse);
        //Object vetor =graphComponent.addMouseListener(mouse);
      //  graphComponent.getGraphControl().addMouseListener((MouseListener) eventoMouse);
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter(){
            //private int[] origemDestino = new int[2];
           // private int indice;
                public void mousePressed(MouseEvent e){
                    Object retorno = graphComponent.getCellAt(e.getX(), e.getY());
                    if(retorno!=null){
                        for(int i=0;i<50;i++){
                            if(vertices[i]==retorno){//verificar qual foi selecionado
                                if(origemDestino[0]==0){//se a origem não tiver origem
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
                    //so pra testar
                    for(Object o:origemDestino){
                        System.out.println(o);
                    }
                }
            });
       /* if(origemDestino[0]==0||origemDestino[1]==0){
            ir.setEnabled(false);
        }
        else{
        ir.setEnabled(true);
        }*/
      // JList legenda = new JList();
       //legenda.
        setLocationRelativeTo(null);
        
    }
    public void mostraCaminho(){
        //Pega o lugar onde a pessoa clicou no mapa como origem e destino e passa pro dijkstra
        //depois pega o retorno do dijkstra
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        ir = new javax.swing.JButton();
        alterarPreco = new javax.swing.JButton();
        alterarTempo = new javax.swing.JButton();
        historico = new javax.swing.JButton();
        mapa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        desfazer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

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

        historico.setText("Histórico de Corridas");
        historico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historicoActionPerformed(evt);
            }
        });

        mapa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cliente\\Documents\\Vanessa\\UEFS\\IIº SEMESTRE\\MI PROGRAMAÇÃO\\PROBLEMA 4\\Problema 4\\mapaSalvador.jpeg")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cliente\\Documents\\Vanessa\\UEFS\\IIº SEMESTRE\\MI PROGRAMAÇÃO\\PROBLEMA 4\\Problema 4\\papa1.jpg")); // NOI18N

        desfazer.setText("Desfazer");
        desfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desfazerActionPerformed(evt);
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
        jLayeredPane1.setLayer(historico, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(mapa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(desfazer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(historico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(alterarTempo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alterarPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(desfazer, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
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
                .addComponent(historico, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desfazer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1570, 1570, 1570))
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

    private void historicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_historicoActionPerformed

//Botao alterar tempo
    private void alterarTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarTempoActionPerformed
        Object[] obj = new String[500];
        for(int i = 0; i<500; i++){
            for(int j=i+1; j<50; j++){
                //if(controller.isAresta(i, j)){
                if(controller.isAresta(i, j)&&controller.isAresta(j, i)){
                    obj[i]= controller.getBairros(i) + " -- " + controller.getBairros(j);
                }
            }
        }
        Object retorno = JOptionPane.showInputDialog(null, "Selecione um caminho:","Alteração do Tempo",JOptionPane.INFORMATION_MESSAGE, null,obj,obj[0]);
        /*Object selectedValue = JOptionPane.showInputDialog(null,"Caminho:", "Selecione o caminho:",
        JOptionPane.INFORMATION_MESSAGE, null, obj, obj[0]);	
        Object[] opcoes = {"Um","Dois","Tres","Quatro"};
Object res = JOptionPane.showInputDialog(null, "Escolha um item" , "Selecao de itens" ,
				JOptionPane.PLAIN_MESSAGE , null ,opcoes,"");*/
    }//GEN-LAST:event_alterarTempoActionPerformed

//botao edita preço
    private void alterarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarPrecoActionPerformed
        double valor =Double.parseDouble(JOptionPane.showInputDialog("Digite o novo preço para o quilômetro rodado:"));
        controller.setPrecoQuilometro(valor);
    }//GEN-LAST:event_alterarPrecoActionPerformed

    private void irActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irActionPerformed
        //corrigir pq ta meio bugado
        
        int[] caminhoMinimo=controller.retonaMenorCaminho(origemDestino[0], origemDestino[1]);
        
        for(int i=0;i<vertices.length-1 && i<caminhoMinimo.length-1;i++){
            //for(int j=i+1;j<caminhoMinimo.length;j++){
                if(caminhoMinimo!=null){
                    //if(caminhoMinimo[i]!=0){
                        graph.insertEdge(null, null, "", vertices[caminhoMinimo[i+1]], vertices[caminhoMinimo[i]]);
                    //}
                }
            //}
        }
        JOptionPane.showMessageDialog(null,
                    "Distancia: " + controller.retornaValorDistancia(origemDestino[0], origemDestino[1])+ " Km" + "\n"+ 
                    "Tempo de percurso: "+ controller.retornaTempoTotal() + " min"+"\n" +
                    "Valor da Corrida: R$ " + controller.retornaValorTotalCorrida(),"Resultado",JOptionPane.INFORMATION_MESSAGE );

    }//GEN-LAST:event_irActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
        
    }//GEN-LAST:event_formMouseClicked

    private void desfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desfazerActionPerformed
       
        int[] caminhoMinimo=controller.retonaMenorCaminho(origemDestino[0], origemDestino[1]);
        Object[] obj = new Object[caminhoMinimo.length];
        for(int i=0;i<50;i++){
            for(int j=i+1;j<50;j++){
                if(caminhoMinimo!=null){
                    //if(caminhoMinimo[i]!=0){
                        obj = graph.getEdgesBetween(vertices[i], vertices[j]);
                    //}
                }
            }
        }
        graph.resetEdges(obj);
        graph.refresh();
    }//GEN-LAST:event_desfazerActionPerformed

//Botao alterar preço//Botao histórico


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
    private javax.swing.JButton desfazer;
    private javax.swing.JButton historico;
    private javax.swing.JButton ir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mapa;
    // End of variables declaration//GEN-END:variables
}
