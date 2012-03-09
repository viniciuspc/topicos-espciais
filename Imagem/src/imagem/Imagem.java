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
        i.limiar_threshould(60, matriz, matriz);
        i.sobel(matriz, matriz);
        i.dilatacao(matriz, matrizResultado);
        
        //i.cinza(matriz, matriz);
        //i.cinzaResultado();
        //i.brilho(80);
        //i.contraste(3);
        //i.negativo();
        //i.limiar_threshould_inverso(127);
        //i.media();
        //i.mediana();
        //i.sobel(matriz, matrizResultado);
        //i.prewitt();
        //i.laplaciano_8();
        //i.laplaciano_menos_8(matriz, matrizResultado);
        //i.limiar_threshould(60, matrizResultado, matrizResultado);
        //i.laplaciano_4();
        //i.laplaciano_menos_4();
        //i.limiar_threshould_resultado(100);
        //i.erosao(matrizResultado, matrizResultado);
        //i.dilatacao(matrizResultado, matrizResultado);
        //i.erosao(matrizResultado, matrizResultado);
        //i.erosao_pixel(matrizResultado, matrizResultado);
        //i.dilatacao(matriz, matrizResultado);
        //i.dilatacao_pixel(matrizResultado, matrizResultado);
        //i.abertura(matrizResultado, matrizResultado);
        //i.fechamento(matrizResultado, matrizResultado);
        //i.fpbMaior(matriz, matrizResultado);
        //i.fpbMenor(matriz, matrizResultado);
        //i.abertura_pixel(matriz, matrizResultado);
        
        
        //i.negativo(matrizResultado, matrizResultado);
        i.densidade(matriz);
        i.densidade(matrizResultado);
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
