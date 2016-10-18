
package View;

import Controller.Controller;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Cliente
 */
public class Papalegua extends javax.swing.JFrame {
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private JTextField texto;
    private JButton botaoAdd;
    private Controller controller;
    private Object[] vertices;
    private String[] bairros;
    private MouseListener mouse;

    public Papalegua() throws IOException{
        super("Papalégua");
        controller = new Controller();
        controller.insereNaMatriz("bairros2.xls");
        
        vertices = new Object[50];
        bairros = new String[50];
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
        
        setLocationRelativeTo(null);
        graphComponent.addMouseListener(mouse);
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        mapa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        jScrollPane1.setViewportView(jTextPane1);

        mapa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cliente\\Documents\\Vanessa\\UEFS\\IIº SEMESTRE\\MI PROGRAMAÇÃO\\PROBLEMA 4\\Problema 4\\mapaSalvador.jpeg")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cliente\\Documents\\Vanessa\\UEFS\\IIº SEMESTRE\\MI PROGRAMAÇÃO\\PROBLEMA 4\\Problema 4\\papa1.jpg")); // NOI18N

        jLayeredPane1.setLayer(ir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(alterarPreco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(alterarTempo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(historico, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(mapa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(historico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(alterarTempo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alterarPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1913, 1913, 1913))
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
        JDialogTempo jDialogTemp = new JDialogTempo(this,true);
        jDialogTemp.setLocationRelativeTo(null);
        jDialogTemp.setTitle("Alteração de Tempo do percurso");
        Dimension d = new Dimension();
        d.setSize(250, 130);
        jDialogTemp.setMinimumSize(d);
        jDialogTemp.setVisible(true);
        //getContentPane().add(jDialog);
        //pega a origem do combobox, o indice e o destino e envia para o controller no metodo se sao adjacentes, se sim altera
        String retorno = jDialogTemp.valorDigitadoTempo();
        double valor = Double.parseDouble(retorno);
        //controller.alteraTempo(origem, destino, valor);//oorigem e destino correspondente ao combobox
    }//GEN-LAST:event_alterarTempoActionPerformed

//botao edita preço
    private void alterarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarPrecoActionPerformed
        //Parte anterior com jinternalframe
        /*TelaEdicao editaPreco = new TelaEdicao();
        editaPreco.setTitle("Alteração de Preço");
        getContentPane().add(editaPreco);
        editaPreco.setVisible(true);
        String retorno = editaPreco.getJTextField();
        double valor = Double.parseDouble(retorno);
        controller.setPrecoQuilometro(valor);*/
        
        //Com JDialog
        NovoJDialog jDialog = new NovoJDialog(this,true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setTitle("Alteração de Preço");
        Dimension d = new Dimension();
        d.setSize(250, 130);
        jDialog.setMinimumSize(d);
        jDialog.setVisible(true);
        //getContentPane().add(jDialog);
        String retorno = jDialog.valorDigitado();
        double valor = Double.parseDouble(retorno);
        controller.setPrecoQuilometro(valor);
        

        //  controller.setPrecoQuilometro(Double.parseDouble(retorno.trim()));
        //String str = editaPreco.getJTextField();

        //controller.setPrecoQuilometro(str);

        // ;

        // TODO add your handling code here:
    }//GEN-LAST:event_alterarPrecoActionPerformed

    private void irActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_irActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
        
    }//GEN-LAST:event_formMouseClicked

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
    private javax.swing.JButton historico;
    private javax.swing.JButton ir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel mapa;
    // End of variables declaration//GEN-END:variables
}
