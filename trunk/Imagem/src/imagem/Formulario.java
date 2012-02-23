package imagem;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Formulario {
    
    private BufferedImage imagem;
    private BufferedImage imagemResultado;
    private ImageIcon icon;
    private ImageIcon iconResultado;
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        iconResultado =  new ImageIcon(imagemResultado);
        JFrame f = new JFrame("Imagem");
        JFrame fR = new JFrame("Imagem Resultado");
        JPanel p = new JPanel();
        JPanel pR = new JPanel();
        JLabel lblImagem = new JLabel();
        JLabel lblImagemResultado = new JLabel();
        lblImagem.setIcon(icon);
        lblImagemResultado.setIcon(iconResultado);
        
        p.add(lblImagem);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        
        pR.add(lblImagemResultado);
        fR.add(pR);
        fR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fR.pack();
        fR.setVisible(true);
        
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

	public void setImagemResultado(BufferedImage imagemResultado) {
		this.imagemResultado = imagemResultado;
	}
    
    

    
    
    
}
