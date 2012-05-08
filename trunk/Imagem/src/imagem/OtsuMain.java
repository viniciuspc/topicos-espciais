package imagem;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Formulario.Formulario;

import processadoresImagem.Img;

public class OtsuMain {
	
	public static void main(String args[]) throws Exception{
		Img i = new Img();
		BufferedImage imagem = ImageIO.read(new File("lego1.jpg"));
        BufferedImage imagemResultado = ImageIO.read(new File("lego1.jpg"));
        int[][] matriz = i.lerBuffer(imagem);
        int[][] matrizResultado = i.contraste(3, matriz);
        matrizResultado = i.otsu(matrizResultado);
        
        Formulario f = new Formulario();
        
        imagem = i.lerMatriz(matriz);
        imagemResultado = i.lerMatriz(matrizResultado );
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        
        f.exibir2();
        f.getJFrame().setVisible(true);
	}

}
