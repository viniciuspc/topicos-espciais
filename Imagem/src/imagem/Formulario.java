package imagem;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Formulario {
    
    private BufferedImage imagem;
    private ImageIcon icon;
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        JFrame f = new JFrame("Imagem");
        JPanel p = new JPanel();
        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(icon);
        
        p.add(lblImagem);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    
    
    
}
