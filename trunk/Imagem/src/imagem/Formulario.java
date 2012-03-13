package imagem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Formulario {
    
    private BufferedImage imagem;
    private BufferedImage imagemResultado;
    private ImageIcon icon;
    private ImageIcon iconResultado;
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        iconResultado =  new ImageIcon(imagemResultado);
        JFrame f = new JFrame("Imagem");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(d.width+"x"+d.height);
        JPanel p = new JPanel();
        JLabel lblImagem = new JLabel();
        JLabel lblImagemResultado = new JLabel();
        lblImagem.setIcon(icon);
        lblImagemResultado.setIcon(iconResultado);
        
        p.add(lblImagem, BorderLayout.WEST);
        p.add(lblImagemResultado, BorderLayout.EAST);
        f.add(p);
        
        JScrollPane scrollBar=new JScrollPane(p,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(scrollBar);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public void setImagemResultado(BufferedImage imagemResultado) {
            this.imagemResultado = imagemResultado;
    }
    
    

    
    
    
}
