package Formulario;

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

public class FormularioEntropia {
    
    private BufferedImage imagem = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    private BufferedImage imagemVlMedio = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    private BufferedImage imagemVarianca = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
   
    private ImageIcon icon;
    private ImageIcon iconVlMedio;
    private ImageIcon iconVarianca;
    
    private JFrame f = new JFrame("img-entropia/1");
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        iconVlMedio =  new ImageIcon(imagemVlMedio);
        iconVarianca = new ImageIcon(imagemVarianca);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(d.width+"x"+d.height);
        JPanel p = new JPanel();
        JLabel lblImagem = new JLabel();
        JLabel lblImagemVlmedio = new JLabel();
        JLabel lblImagemVarianca = new JLabel();
        
        lblImagem.setIcon(icon);
        lblImagemVlmedio.setIcon(iconVlMedio);
        lblImagemVarianca.setIcon(iconVarianca);
        
        p.add(lblImagem);
        p.add(lblImagemVlmedio, BorderLayout.EAST);
        p.add(lblImagemVarianca, BorderLayout.EAST);
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

    public void setImagemVlMedio(BufferedImage imagemVlMedio) {
            this.imagemVlMedio = imagemVlMedio;
    }

	public void setImagemVarianca(BufferedImage imagemVarianca) {
		this.imagemVarianca = imagemVarianca;
	}

    
    
   
}
