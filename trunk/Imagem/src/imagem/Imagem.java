package imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	
	

    public static void main(String[] args) throws IOException, InterruptedException {
        long agora = System.currentTimeMillis();
        
        for(int k = 1; k < 23; k++){
            Thread.sleep(200);
            String arquivo;
            String arquivo1;
         if(k<10)
            arquivo = "imgs-rastreamento/0"+k+".jpg";
         else
             arquivo = "imgs-rastreamento/"+k+".jpg";
         
        if(k+1 <10)
            arquivo1 = "imgs-rastreamento/0"+(k+1)+".jpg";
        else
            arquivo1 = "imgs-rastreamento/"+(k+1)+".jpg";
       
    	BufferedImage imagem = ImageIO.read(new File(arquivo));
        BufferedImage imagemResultado = ImageIO.read(new File(arquivo1));
       
        int[][][] matriz;
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        matriz = i.lerArquivoRgb(imagem);
        /**
         * Usado onde for nescessario usar uma mascara
         */
        int[][][] matrizResultado;
        matrizResultado = i.lerArquivoRgb(imagemResultado);
        
        
        int[][] matrizRegioes = new int[i.getLargura()][i.getAltura()];
        i.zerarMatriz(matrizRegioes);
        
        int[][] matrizSubtracao = new int[i.getLargura()][i.getAltura()];
        
        i.limiar_threshould(200, matriz, matriz);
        i.limiar_threshould(200, matrizResultado, matrizResultado);
        
        matrizSubtracao = i.subtrairMatriz(matriz, matrizResultado);
        
        
        
        //i.contraste(2.9, matriz, matriz);
        //i.cinza(matriz, matriz);
        //i.limiar_threshould(240, matriz, matriz);
        //i.abertura(matriz, matriz);
        
        //i.vizinhanca_4(matriz, matrizRegioes);
        
        /*
        i.cinza(matriz, matriz);
        i.sobel(matriz, matriz);
        i.limiar_threshould(100, matriz, matriz);
        i.abertura(matriz, matrizResultado);
        */
        
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
        
        BufferedImage imagemSubtracao = new BufferedImage(i.getLargura(), i.getAltura(), BufferedImage.TYPE_INT_RGB );

        
        i.histograma(matriz, histograma);
        //Da matriz para o buffer
        i.lerMatriz(matriz, imagem);
        i.lerMatriz(matrizResultado, imagemResultado);
        i.lerMatriz(projecaoHorizontal, grficoProjecaoHorizontal);
        i.lerMatriz(projecaoVertical, grficoProjecaoVertical);
        i.lerHistograma(histograma, graficoHistograma);
        i.lerMatriz(matrizSubtracao, imagemSubtracao);
        
        //i.salvar(imagem, "lego-negativo-cor");

        Formulario f = new Formulario();
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        f.setProjecaoHorizontal(grficoProjecaoHorizontal);
        f.setProjecaoVertical(grficoProjecaoVertical);
        f.setHistograma(graficoHistograma);
        f.setSubtracao(imagemSubtracao);
        
        f.exibir();

        
        }
        System.out.println("Demorou " + (System.currentTimeMillis() - agora));


    }
}
