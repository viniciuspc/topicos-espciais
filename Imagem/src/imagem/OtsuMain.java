package imagem;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import processadoresImagem.Img;

public class OtsuMain {
	
	public static void main(String args[]) throws Exception{
		Img i = new Img();
		BufferedImage imagem = ImageIO.read(new File("imagem2.jpg"));
        BufferedImage imagemResultado = ImageIO.read(new File("imagem2.jpg"));
        int[][] matriz = i.lerArquivo(imagem);
        int[][] matrizResultado = i.otsu(matriz);
        
        Formulario f = new Formulario();
        
        i.lerMatriz(matriz, imagem);
        i.lerMatriz(matrizResultado, imagemResultado);
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        
        f.exibir2();
        f.getJFrame().setVisible(true);
	}

}
