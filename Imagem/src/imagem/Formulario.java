package imagem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
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
    private BufferedImage histograma;
    private BufferedImage projecaoVertical;
    private BufferedImage projecaoHorizontal;
    private BufferedImage subtracao;
    private ImageIcon icon;
    private ImageIcon iconResultado;
    private ImageIcon iconProjecaoHorizontal;
    private ImageIcon iconProjecaoVertical;
    private ImageIcon iconHistograma;
    private ImageIcon iconSubtracao;
    
    public void exibir(){
        icon =  new ImageIcon(imagem);
        iconResultado =  new ImageIcon(imagemResultado);
        iconProjecaoHorizontal = new ImageIcon(projecaoHorizontal);
        iconProjecaoVertical = new ImageIcon(projecaoVertical);
        iconHistograma = new ImageIcon(histograma);
        iconSubtracao = new ImageIcon(subtracao);
        JFrame f = new JFrame("Imagem");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(d.width+"x"+d.height);
        JPanel p = new JPanel();
        JLabel lblImagem = new JLabel();
        JLabel lblImagemResultado = new JLabel();
        JLabel lblProjecaoHorizontal = new JLabel();
        JLabel lblProjecaoVertical = new JLabel();
        JLabel lblHistograma = new JLabel();
        JLabel lblSubtracao = new JLabel();
        lblImagem.setIcon(icon);
        lblImagemResultado.setIcon(iconResultado);
        lblProjecaoHorizontal.setIcon(iconProjecaoHorizontal);
        lblProjecaoVertical.setIcon(iconProjecaoVertical);
        lblHistograma.setIcon(iconHistograma);
        lblSubtracao.setIcon(iconSubtracao);
        
        p.add(lblImagem);
        p.add(lblImagemResultado, BorderLayout.EAST);
        p.add(lblSubtracao);
        //p.add(lblProjecaoHorizontal);
        //p.add(lblProjecaoVertical);
        //p.add(lblHistograma);
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

	public void setHistograma(BufferedImage histograma) {
		this.histograma = histograma;
	}

	public void setProjecaoVertical(BufferedImage projecaoVertical) {
		this.projecaoVertical = projecaoVertical;
	}

	public void setProjecaoHorizontal(BufferedImage projecaoHorizontal) {
		this.projecaoHorizontal = projecaoHorizontal;
	}
        
        public void setSubtracao(BufferedImage imagemSubtracao){
            this.subtracao = imagemSubtracao;
        }
    
    
   
}
