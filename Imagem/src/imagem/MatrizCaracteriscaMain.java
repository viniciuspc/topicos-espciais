package imagem;

import java.io.IOException;

import processadoresImagem.Img;
import Formulario.FormularioVisao;

public class MatrizCaracteriscaMain {
	
	public static void main(String args[]) throws IOException{
		 	FormularioVisao f = new FormularioVisao();
	       Img i = new Img();
	       int[][] imagem = i.lerArquivo("objetos.jpg");
	       f.setImagem(i.lerMatriz(imagem));
	       int[][] matrizRegioes = new int[i.getLargura()][i.getAltura()];
	       i.zerarMatriz(matrizRegioes);
	       imagem = i.limiar_threshould(200, imagem);
	       i.contarObjetos(imagem, matrizRegioes);
	       
	       f.setImagemResultado(i.lerMatriz(imagem));
	       
	       f.exibir();
	       f.getJFrame().setVisible(true);
	}

}
