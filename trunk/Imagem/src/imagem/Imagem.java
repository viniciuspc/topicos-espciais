package imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	
	

    public static void main(String[] args) throws IOException {
        long agora = System.currentTimeMillis();
        String arquivo = "objetos.jpg";
    	BufferedImage imagem = ImageIO.read(new File(arquivo));
        BufferedImage imagemResultado = ImageIO.read(new File(arquivo));
        int[][][] matriz;
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        matriz = i.lerArquivo(imagem);
        /**
         * Usado onde for nescessario usar uma mascara
         */
        int[][][] matrizResultado;
        matrizResultado = i.lerArquivo(imagem);
        
        
        int[][] matrizRegioes = new int[i.getLargura()][i.getAltura()];
        i.zerarMatriz(matrizRegioes);
        //i.cinza(matriz, matriz);
        i.limiar_threshould(127, matriz, matriz);
        i.vizinhanca_4(matriz, matrizRegioes);
        
        
        
        //i.negativo(matrizResultado, matrizResultado);
        //i.densidade(matriz);
        //i.densidade(matrizResultado);
        //Da matriz para o buffer
        i.lerMatriz(matriz, imagem);
        i.lerMatriz(matrizResultado, imagemResultado);
        //i.salvar(i.getImagemResultado(), "carro_menos_8_erosao2");

        Formulario f = new Formulario();
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        f.exibir();

        System.out.println("Demorou " + (System.currentTimeMillis() - agora));



    }
}
