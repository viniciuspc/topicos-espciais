package imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	
	

    public static void main(String[] args) throws IOException {
        long agora = System.currentTimeMillis();
        String arquivo = "lego1.jpg";
       
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
        
        i.cinza(matriz, matriz);
        i.sobel(matriz, matriz);
        i.limiar_threshould(100, matriz, matriz);
        i.fechamento(matriz, matrizResultado);
        
        //Para a foto do corrolla
        /*
        i.cinza(matriz, matriz);
        i.mediana(matriz, matriz);
        i.laplaciano_menos_8(matriz, matriz);
        i.limiar_threshould(70, matriz, matriz);
        i.dilatacao(matriz, matriz);
        i.negativo(matriz, matriz);
        */
        
        /*
        i.contraste(2.90, matriz, matriz);
        i.cinza(matriz, matriz);
        i.limiar_threshould(240, matriz, matriz);
        i.erosao(matriz, matriz);
        i.media(matriz, matriz);
        i.vizinhanca_4(matriz, matrizRegioes);
        */
        
        int[][] projecaoHorizontal = new int[i.getLargura()][i.getAltura()];
        BufferedImage grficoProjecaoHorizontal = new BufferedImage(i.getLargura(), i.getAltura(), BufferedImage.TYPE_INT_RGB );
        i.projecaoHorizontal(matriz, projecaoHorizontal);
        int[][] projecaoVertical = new int[i.getLargura()][i.getAltura()];
        BufferedImage grficoProjecaoVertical = new BufferedImage(i.getLargura(), i.getAltura(), BufferedImage.TYPE_INT_RGB );
        i.projecaoVertical(matriz, projecaoVertical);
        int[][] histograma = new int[256][101];
        BufferedImage graficoHistograma = new BufferedImage(256, 101, BufferedImage.TYPE_INT_RGB );
        i.histograma(matriz, histograma);
        //Da matriz para o buffer
        i.lerMatriz(matriz, imagem);
        i.lerMatriz(matrizResultado, imagemResultado);
        i.lerMatriz(projecaoHorizontal, grficoProjecaoHorizontal);
        i.lerMatriz(projecaoVertical, grficoProjecaoVertical);
        i.lerHistograma(histograma, graficoHistograma);
        
        i.salvar(imagemResultado, "lego-abertura");

        Formulario f = new Formulario();
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        f.setProjecaoHorizontal(grficoProjecaoHorizontal);
        f.setProjecaoVertical(grficoProjecaoVertical);
        f.setHistograma(graficoHistograma);
        f.exibir();

        System.out.println("Demorou " + (System.currentTimeMillis() - agora));



    }
}
