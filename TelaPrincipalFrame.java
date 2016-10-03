package View;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class TelaPrincipalFrame extends JFrame {
    private JLabel titulo;
    public TelaPrincipalFrame(){
        super("Papalégua");
   
        ImageIcon mapa = new ImageIcon("mapaSsa.png");
        titulo =new JLabel("", mapa, SwingConstants.RIGHT);
	titulo.setHorizontalTextPosition( SwingConstants.CENTER );
	titulo.setVerticalTextPosition( SwingConstants.TOP );
        
        Image image = mapa.getImage();  
        Image newimg = image.getScaledInstance(1150, 700, java.awt.Image.SCALE_SMOOTH);  
        titulo.setIcon(new ImageIcon(newimg));
		
        add(titulo);
        
        ImageIcon icone = new ImageIcon("papalegua.png");
        setIconImage(icone.getImage());
        titulo.setHorizontalTextPosition( SwingConstants.CENTER );
        
    }
    
    
}
