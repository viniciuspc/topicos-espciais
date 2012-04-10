package imagem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FormularioVisao {
    
    private BufferedImage imagem = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    private BufferedImage imagemResultado = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
   
    private ImageIcon icon;
    private ImageIcon iconResultado;
    
    private JFrame f = new JFrame("Imagem");
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        iconResultado =  new ImageIcon(imagemResultado);
        
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(d.width+"x"+d.height);
        JPanel p = new JPanel();
        JLabel lblImagem = new JLabel();
        JLabel lblImagemResultado = new JLabel();
        
        lblImagem.setIcon(icon);
        lblImagemResultado.setIcon(iconResultado);
        
        
        p.add(lblImagem);
        p.add(lblImagemResultado, BorderLayout.EAST);
        
        //p.add(lblProjecaoHorizontal);
        //p.add(lblProjecaoVertical);
        //p.add(lblHistograma);
        f.add(p);
        
        JScrollPane scrollBar=new JScrollPane(p,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(scrollBar);
        
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        
    }
    
    public JFrame getJFrame() {
		return f;
	}



	public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public void setImagemResultado(BufferedImage imagemResultado) {
            this.imagemResultado = imagemResultado;
    }

    
    
   
}
