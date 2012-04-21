package processadoresImagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Vinicius
 *
 */
public class Img {

	private int largura;
	private int altura;
	
	/**
	 * 
	 * @param imagem
	 * @return
	 */
    public int[][][]  lerArquivoRgb(BufferedImage imagem) {
    	int[][][] matriz;
        largura = imagem.getWidth();
        altura = imagem.getHeight();
        matriz = new int[largura][altura][3];
        /*
         * Percore a matriz de cima para baixo da esquerda para a direita
         */
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                Color pixel = new Color(imagem.getRGB(coluna, linha));
                matriz[coluna][linha][0] = pixel.getRed();
                matriz[coluna][linha][1] = pixel.getGreen();
                matriz[coluna][linha][2] = pixel.getBlue();
            }
        }
        return matriz;
        
    }
    
    public int[][]  lerArquivo(BufferedImage imagem) {
    	int[][] matriz;
        largura = imagem.getWidth();
        altura = imagem.getHeight();
        matriz = new int[largura][altura];
        /*
         * Percore a matriz de cima para baixo da esquerda para a direita
         */
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                Color pixel = new Color(imagem.getRGB(coluna, linha));
                matriz[coluna][linha] = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
            }
        }
        return matriz;
        
    }
    
    
    
    /**
     * 
     * @param matriz
     * @param imagem
     */
    public void lerMatriz(int[][][] matriz, BufferedImage imagem) {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                Color pixel = new Color(r, g, b);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    public void lerMatriz(int[][] matriz, BufferedImage imagem) {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha];
                int g = matriz[coluna][linha];
                int b = matriz[coluna][linha];
                Color pixel = new Color(r, g, b);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    public void lerHistograma(int[][] matriz, BufferedImage imagem) {
    	
        for (int linha = 0; linha < 101; linha++) {
            for (int coluna = 0; coluna < 256; coluna++) {
                int r = matriz[coluna][linha];
                int g = matriz[coluna][linha];
                int b = matriz[coluna][linha];
                Color pixel = new Color(r, g, b);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    /**
     * 
     * @param matrizOriginal
     * @param matriz
     * @param imagem
     */
    public void azul(int[][][] matrizOriginal, int[][][] matriz, BufferedImage imagem) {
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                Color pixel = new Color(0, 0, b);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    /**
     * 
     * @param matrizOriginal
     * @param matriz
     */
    public void cinza(int[][][] matrizOriginal, int[][][] matriz){
    	
    	
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matrizOriginal[coluna][linha][0];
                int g = matrizOriginal[coluna][linha][1];
                int b = matrizOriginal[coluna][linha][2];
                //tons de cinza
               // int cinza = (r + g + b)/3;
                int cinza = (int) (r*0.3+g*0.59+b*0.11);
                //seta os tons de cinza na matriz
                matriz[coluna][linha][0] = cinza;
                matriz[coluna][linha][1] = cinza;
                matriz[coluna][linha][2] = cinza;
            }
        }
    }
    /**
     * 
     * @param valor
     * @param matrizOriginal
     * @param matriz
     */
    public void brilho(Integer valor, int[][][] matrizOriginal, int[][][] matriz){
    	
    	
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matrizOriginal[coluna][linha][0] + valor;
                int g = matrizOriginal[coluna][linha][1] + valor;
                int b = matrizOriginal[coluna][linha][2] + valor;
                
                //seta os tons de cinza na matriz
                if(r > 255)
                    matriz[coluna][linha][0] = 255;
                else
                    if(r<0)
                        matriz[coluna][linha][0] = 0;
                    else
                        matriz[coluna][linha][0] = r;
                if(g > 255)
                    matriz[coluna][linha][1] = 255;
                else
                    if(g<0)
                        matriz[coluna][linha][1] = 0;
                    else
                        matriz[coluna][linha][1] = g;
                if(b > 255)
                    matriz[coluna][linha][2] = 255;
                else
                    if(b<0)
                        matriz[coluna][linha][2] = 0;
                    else
                        matriz[coluna][linha][2] = b;
            }
        }
    }
    
    /**
     * 
     * @param valor
     * @param matrizOriginal
     * @param matriz
     */
    public void contraste(double valor, int[][][] matrizOriginal, int[][][] matriz){
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                double r = matrizOriginal[coluna][linha][0] * (valor);
                double g = matrizOriginal[coluna][linha][1] * (valor);
                double b = matrizOriginal[coluna][linha][2] * (valor);
                
                //seta os tons de cinza na matriz
                if(r > 255)
                    matriz[coluna][linha][0] = 255;
                else
                    if(r<0)
                        matriz[coluna][linha][0] = 0;
                    else
                        matriz[coluna][linha][0] = (int) r;
                if(g > 255)
                    matriz[coluna][linha][1] = 255;
                else
                    if(g<0)
                        matriz[coluna][linha][1] = 0;
                    else
                        matriz[coluna][linha][1] = (int) g;
                if(b > 255)
                    matriz[coluna][linha][2] = 255;
                else
                    if(b<0)
                        matriz[coluna][linha][2] = 0;
                    else
                        matriz[coluna][linha][2] = (int) b;
            }
        }
    }
    /**
     * 
     * @param matrizOriginal
     * @param matriz
     */
    public void negativo(int[][][] matrizOriginal, int[][][] matriz){
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizOriginal, matriz);
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = 255-matrizOriginal[coluna][linha][0];
                int g = 255-matrizOriginal[coluna][linha][1];
                int b = 255-matrizOriginal[coluna][linha][2];
                
                matriz[coluna][linha][0] = r;
                matriz[coluna][linha][1] = g;
                matriz[coluna][linha][2] = b;
            }
        }
    }
    
    /**
     * Binarizacao usar com tons de cinza
     * @param limiar
     * @param matrizOriginal
     * @param matriz
     * 
     */
    public void limiar_threshould_rgb(int limiar, int[][][] matrizOrigem, int[][][] matriz){
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizOrigem, matrizAux);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                
                if(r > limiar)
                    matriz[coluna][linha][0] = 255;
                else
                    matriz[coluna][linha][0] = 0;
                
                if(g > limiar)
                    matriz[coluna][linha][1] = 255;
                else
                    matriz[coluna][linha][1] = 0;
                
                if(b > limiar)
                    matriz[coluna][linha][2] = 255;
                else
                    matriz[coluna][linha][2] = 0;
            }
        }
    }
    
    /**
     * Binarizacao usar com tons de cinza
     * @param limiar
     * @param matrizOriginal
     * @param matriz
     * 
     */
    public int[][] limiar_threshould(int limiar, int[][] matriz){
    	int[][] matrizAux = new int[largura][altura];
    	brancoMatriz(matrizAux);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int c = matriz[coluna][linha];
                
                if(c > limiar)
                	matrizAux[coluna][linha] = 255;
                else
                	matrizAux[coluna][linha] = 0;
                
            }
        }
        return matrizAux;
    }
    
    /**
     * 
     * @param limiar
     * @param matrizOriginal
     * @param matriz
     */
    public void limiar_threshould_inverso(int limiar, int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                
                if(r < limiar)
                    matriz[coluna][linha][0] = 255;
                else
                    matriz[coluna][linha][0] = 0;
                
                if(g < limiar)
                    matriz[coluna][linha][1] = 255;
                else
                    matriz[coluna][linha][1] = 0;
                
                if(b < limiar)
                    matriz[coluna][linha][2] = 255;
                else
                    matriz[coluna][linha][2] = 0;
            }
        }
    }
    
    /**
     * nescessario usar a binarizacao
     * @param matriz
     */
    public void densidade(int[][][] matriz){
        double nunPixel = altura*largura;
        double ptosPreto = 0;
        double densidade;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                
                if(r == 0 && g == 0 && b == 0){
                    ptosPreto++;
                }
                
            }
        }
        densidade=(ptosPreto/nunPixel) * 100;
        System.out.println("Densidade: ");
        System.out.println("Pontos Pretos: "+ ptosPreto);
        System.out.println("Numero de pixels :"+ nunPixel+" Altura: "+altura+" x Largura: "+largura);
        System.out.println("Densidade "+ densidade + "%");
   }

    /**
     * 
     * Filtro Passa Baixa (DiminuiÃ§Ã£o de ruido) usar com tons de cinza.
     * (L-1, C-1)   (L-1, C  )  (L-1, C+1)
     * (L  , C-1)   (L  , C  )  (L  , C+1)
     * (L+1, C-1)   (L+1, C  )  (L+1, C+1)
     *
      * @param matriz
      * @param matrizResultado
      */
    public void media(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                	matrizResultado[coluna][linha][0] = (matriz[coluna-1][linha-1][0]+matriz[coluna][linha-1][0]+matriz[coluna+1][linha-1][0]
                    		+matriz[coluna-1][linha][0]+matriz[coluna][linha][0]+matriz[coluna+1][linha][0]
                    		+matriz[coluna-1][linha+1][0]+matriz[coluna][linha+1][0]+matriz[coluna+1][linha+1][0])/9;
                    
                	matrizResultado[coluna][linha][1] = (matriz[coluna-1][linha-1][1]+matriz[coluna][linha-1][1]+matriz[coluna+1][linha-1][1]
                    		+matriz[coluna-1][linha][1]+matriz[coluna][linha][1]+matriz[coluna+1][linha][1]
                    		+matriz[coluna-1][linha+1][1]+matriz[coluna][linha+1][1]+matriz[coluna+1][linha+1][1])/9;
                    
                	matrizResultado[coluna][linha][2] = (matriz[coluna-1][linha-1][2]+matriz[coluna][linha-1][2]+matriz[coluna+1][linha-1][2]
                    		+matriz[coluna-1][linha][2]+matriz[coluna][linha][2]+matriz[coluna+1][linha][2]
                    		+matriz[coluna-1][linha+1][2]+matriz[coluna][linha+1][2]+matriz[coluna+1][linha+1][2])/9;         
            }
        }
    }
    
    /**
     * Filtro Passa Baixa (DiminuiÃ§Ã£o de ruido) usar com tons de cinza.
     * Mediana ordena e pega o do meio.
     * (L-1, C-1)   (L-1, C  )  (L-1, C+1)
     * (L  , C-1)   (L  , C  )  (L  , C+1)
     * (L+1, C-1)   (L+1, C  )  (L+1, C+1)
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void mediana(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	List<Integer> lista = new ArrayList<Integer>();
    	for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	
            		lista.clear();
                	lista.add(matriz[coluna-1][linha-1][0]);
                	lista.add(matriz[coluna][linha-1][0]);
                	lista.add(matriz[coluna+1][linha-1][0]);
                	lista.add(matriz[coluna-1][linha][0]);
                	lista.add(matriz[coluna][linha][0]);
                	lista.add(matriz[coluna+1][linha][0]);
                	lista.add(matriz[coluna-1][linha+1][0]);
                	lista.add(matriz[coluna][linha+1][0]);
                	lista.add(matriz[coluna+1][linha+1][0]);
                    
                	matrizResultado[coluna][linha][0] = retornaMediana(lista);
                	
                	lista.clear();
                	lista.add(matriz[coluna-1][linha-1][1]);
                	lista.add(matriz[coluna][linha-1][1]);
                	lista.add(matriz[coluna+1][linha-1][1]);
                	lista.add(matriz[coluna-1][linha][1]);
                	lista.add(matriz[coluna][linha][1]);
                	lista.add(matriz[coluna+1][linha][1]);
                	lista.add(matriz[coluna-1][linha+1][1]);
                	lista.add(matriz[coluna][linha+1][1]);
                	lista.add(matriz[coluna+1][linha+1][1]);
                    
                	matrizResultado[coluna][linha][1] = retornaMediana(lista);
                	
                	lista.clear();
                	lista.add(matriz[coluna-1][linha-1][2]);
                	lista.add(matriz[coluna][linha-1][2]);
                	lista.add(matriz[coluna+1][linha-1][2]);
                	lista.add(matriz[coluna-1][linha][2]);
                	lista.add(matriz[coluna][linha][2]);
                	lista.add(matriz[coluna+1][linha][2]);
                	lista.add(matriz[coluna-1][linha+1][2]);
                	lista.add(matriz[coluna][linha+1][2]);
                	lista.add(matriz[coluna+1][linha+1][2]);
                    
                	matrizResultado[coluna][linha][2] = retornaMediana(lista);                	       
            }
        }
    	
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void fpbMaior(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	int maior = 0;
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                for(int l = 0; l < 3; l++){
                    for(int c = 0; c < 3; c++){
                        if(matriz[coluna-(c-1)][linha-(l-1)][0] > maior)
                            maior = matriz[coluna-(c-1)][linha-(l-1)][0];
                    }
                }
                matrizResultado[coluna][linha][0] = maior;

                matrizResultado[coluna][linha][1] = maior;

                matrizResultado[coluna][linha][2] = maior;  
                maior = 0;
                        
            }
        }
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void fpbMenor(int[][][] matriz, int[][][] matrizResultado){
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	int menor = matriz[coluna][linha][0];
                for(int l = 0; l < 3; l++){
                    for(int c = 0; c < 3; c++){
                        if(matriz[coluna-(c-1)][linha-(l-1)][0] < menor)
                        	menor = matriz[coluna-(c-1)][linha-(l-1)][0];
                    }
                }
                matrizResultado[coluna][linha][0] = menor;

                matrizResultado[coluna][linha][1] = menor;

                matrizResultado[coluna][linha][2] = menor;  
                        
            }
        }
    	
    	
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void sobel(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                
                int sobelVertical = (-1*(matriz[coluna-1][linha-1][0])+(-2*matriz[coluna][linha-1][0])+(-1*matriz[coluna+1][linha-1][0])
                    		+(0*matriz[coluna-1][linha][0])+(0*matriz[coluna][linha][0])+(0*matriz[coluna+1][linha][0])
                    		+(1*matriz[coluna-1][linha+1][0])+(2*matriz[coluna][linha+1][0])+(1*matriz[coluna+1][linha+1][0]));
                sobelVertical = sobelVertical*sobelVertical;
                
                int sobelHorizontal = (-1*(matriz[coluna-1][linha-1][0])+(0*matriz[coluna][linha-1][0])+(1*matriz[coluna+1][linha-1][0])
                    		+(-2*matriz[coluna-1][linha][0])+(0*matriz[coluna][linha][0])+(2*matriz[coluna+1][linha][0])
                    		+(-1*matriz[coluna-1][linha+1][0])+(0*matriz[coluna][linha+1][0])+(1*matriz[coluna+1][linha+1][0]));
                
                sobelHorizontal = sobelHorizontal*sobelHorizontal;
                
                //Soma
                int ponto = (int) Math.sqrt(sobelHorizontal+sobelVertical);
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void prewitt(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                
                int sobelVertical = (-1*(matriz[coluna-1][linha-1][0])+(-1*matriz[coluna][linha-1][0])+(-1*matriz[coluna+1][linha-1][0])
                    		+(0*matriz[coluna-1][linha][0])+(0*matriz[coluna][linha][0])+(0*matriz[coluna+1][linha][0])
                    		+(1*matriz[coluna-1][linha+1][0])+(1*matriz[coluna][linha+1][0])+(1*matriz[coluna+1][linha+1][0]));
                sobelVertical = sobelVertical*sobelVertical;
                
                int sobelHorizontal = (-1*(matriz[coluna-1][linha-1][0])+(0*matriz[coluna][linha-1][0])+(1*matriz[coluna+1][linha-1][0])
                    		+(-1*matriz[coluna-1][linha][0])+(0*matriz[coluna][linha][0])+(1*matriz[coluna+1][linha][0])
                    		+(-1*matriz[coluna-1][linha+1][0])+(0*matriz[coluna][linha+1][0])+(1*matriz[coluna+1][linha+1][0]));
                
                sobelHorizontal = sobelHorizontal*sobelHorizontal;
                
                //Soma
                int ponto = (int) Math.sqrt(sobelHorizontal+sobelVertical);
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    /**
     * -1   -1  -1
     * -1   8   -1
     * -1   -1  -1
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void laplaciano_8(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                      
                //Soma
                int ponto = (-1*(matriz[coluna-1][linha-1][0])+(-1*matriz[coluna][linha-1][0])+(-1*matriz[coluna+1][linha-1][0])
                            +(-1*matriz[coluna-1][linha][0])+(8*matriz[coluna][linha][0])+(-1*matriz[coluna+1][linha][0])
                            +(-1*matriz[coluna-1][linha+1][0])+(-1*matriz[coluna][linha+1][0])+(-1*matriz[coluna+1][linha+1][0]));
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    /**
     * 1   1  1
     * 1   -8   1
     * 1   1  1
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void laplaciano_menos_8(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                      
                //Soma
                int ponto = (-1*(matriz[coluna-1][linha-1][0])+(-1*matriz[coluna][linha-1][0])+(-1*matriz[coluna+1][linha-1][0])
                            +(-1*matriz[coluna-1][linha][0])+(8*matriz[coluna][linha][0])+(-1*matriz[coluna+1][linha][0])
                            +(-1*matriz[coluna-1][linha+1][0])+(-1*matriz[coluna][linha+1][0])+(-1*matriz[coluna+1][linha+1][0]));
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    
    
    /**
     * 0   -1  0
     * -1   4   -1
     * 0   -1  0
     * 
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void laplaciano_4(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                      
                //Soma
                int ponto = (0*(matriz[coluna-1][linha-1][0])+(-1*matriz[coluna][linha-1][0])+(0*matriz[coluna+1][linha-1][0])
                            +(-1*matriz[coluna-1][linha][0])+(4*matriz[coluna][linha][0])+(-1*matriz[coluna+1][linha][0])
                            +(0*matriz[coluna-1][linha+1][0])+(-1*matriz[coluna][linha+1][0])+(0*matriz[coluna+1][linha+1][0]));
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    /**
     * 0   1  0
     * 1   -4   1
     * 0   1  0
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void laplaciano_menos_4(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                      
                //Soma
                int ponto = (0*(matriz[coluna-1][linha-1][0])+(1*matriz[coluna][linha-1][0])+(0*matriz[coluna+1][linha-1][0])
                            +(1*matriz[coluna-1][linha][0])+(-4*matriz[coluna][linha][0])+(1*matriz[coluna+1][linha][0])
                            +(0*matriz[coluna-1][linha+1][0])+(1*matriz[coluna][linha+1][0])+(0*matriz[coluna+1][linha+1][0]));
                
                //limita
                ponto = limitar(ponto);
                
                //deve estar em tons de cinza
                matrizResultado[coluna][linha][0] = ponto;

                matrizResultado[coluna][linha][1] = ponto;

                matrizResultado[coluna][linha][2] = ponto;
            }
        }
    }
    
    /**
     * USAR IMAGEM BINARIZADA
     * Preto - fundo e Branco - o objeto
     * O pixel do objeto continuado sendo do objeto se algum dos pixels envolta forem do objeto.
     *
     * @param matriz
     * @param matrizResultado
     */
    public void dilatacao_pixel(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	final int COR_OBJETO = 255;
    	final int COR_FUNDO = 0;
    	for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
        		if(matriz[coluna-1][linha-1][0] == COR_OBJETO || matriz[coluna][linha-1][0] == COR_OBJETO || matriz[coluna+1][linha-1][0] == COR_OBJETO
        		|| matriz[coluna-1][linha][0] == COR_OBJETO || matriz[coluna][linha][0] == COR_OBJETO || matriz[coluna+1][linha][0] == COR_OBJETO
        		|| matriz[coluna-1][linha+1][0] == COR_OBJETO || matriz[coluna][linha+1][0] == COR_OBJETO || matriz[coluna+1][linha+1][0] == COR_OBJETO
        		){
        			matrizResultado[coluna][linha][0] = COR_OBJETO;
                                matrizResultado[coluna][linha][1] = COR_OBJETO;
                                matrizResultado[coluna][linha][2] = COR_OBJETO;
        		} else {
        			matrizResultado[coluna][linha][0] = COR_FUNDO;
                                matrizResultado[coluna][linha][1] = COR_FUNDO;
                                matrizResultado[coluna][linha][2] = COR_FUNDO;
        		}
            }
        }
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void dilatacao(int[][][] matrizOrigem, int[][][] matrizResultado){
    	
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	pretoMatriz(matrizResultado);
    	
    	final int COR_OBJETO = 255;
    	final int COR_FUNDO = 0;
    	for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                if(matriz[coluna][linha][0] == COR_OBJETO){
                    matrizResultado[coluna-1][linha-1][0] = COR_OBJETO; matrizResultado[coluna][linha-1][0] = COR_OBJETO; matrizResultado[coluna+1][linha-1][0] = COR_OBJETO;
                    matrizResultado[coluna-1][linha][0] = COR_OBJETO; matrizResultado[coluna][linha][0] = COR_OBJETO; matrizResultado[coluna+1][linha][0] = COR_OBJETO;
                    matrizResultado[coluna-1][linha+1][0] = COR_OBJETO; matrizResultado[coluna][linha+1][0] = COR_OBJETO; matrizResultado[coluna+1][linha+1][0] = COR_OBJETO;
                    
                    matrizResultado[coluna-1][linha-1][1] = COR_OBJETO; matrizResultado[coluna][linha-1][1] = COR_OBJETO; matrizResultado[coluna+1][linha-1][1] = COR_OBJETO;
                    matrizResultado[coluna-1][linha][1] = COR_OBJETO; matrizResultado[coluna][linha][1] = COR_OBJETO; matrizResultado[coluna+1][linha][1] = COR_OBJETO;
                    matrizResultado[coluna-1][linha+1][1] = COR_OBJETO; matrizResultado[coluna][linha+1][1] = COR_OBJETO; matrizResultado[coluna+1][linha+1][1] = COR_OBJETO;
                    
                    matrizResultado[coluna-1][linha-1][2] = COR_OBJETO; matrizResultado[coluna][linha-1][2] = COR_OBJETO; matrizResultado[coluna+1][linha-1][2] = COR_OBJETO;
                    matrizResultado[coluna-1][linha][2] = COR_OBJETO; matrizResultado[coluna][linha][2] = COR_OBJETO; matrizResultado[coluna+1][linha][2] = COR_OBJETO;
                    matrizResultado[coluna-1][linha+1][2] = COR_OBJETO; matrizResultado[coluna][linha+1][2] = COR_OBJETO; matrizResultado[coluna+1][linha+1][2] = COR_OBJETO;
                }
            }
        }
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void erosao(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	brancoMatriz(matrizResultado);
    	
    	
    	
    	final int COR_OBJETO = 255;
    	final int COR_FUNDO = 0;
    	for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                if(matriz[coluna][linha][0] == COR_FUNDO){
                    matrizResultado[coluna-1][linha-1][0] = COR_FUNDO; matrizResultado[coluna][linha-1][0] = COR_FUNDO; matrizResultado[coluna+1][linha-1][0] = COR_FUNDO;
                    matrizResultado[coluna-1][linha][0] = COR_FUNDO; matrizResultado[coluna][linha][0] = COR_FUNDO; matrizResultado[coluna+1][linha][0] = COR_FUNDO;
                    matrizResultado[coluna-1][linha+1][0] = COR_FUNDO; matrizResultado[coluna][linha+1][0] = COR_FUNDO; matrizResultado[coluna+1][linha+1][0] = COR_FUNDO;
                    
                    matrizResultado[coluna-1][linha-1][1] = COR_FUNDO; matrizResultado[coluna][linha-1][1] = COR_FUNDO; matrizResultado[coluna+1][linha-1][1] = COR_FUNDO;
                    matrizResultado[coluna-1][linha][1] = COR_FUNDO; matrizResultado[coluna][linha][1] = COR_FUNDO; matrizResultado[coluna+1][linha][1] = COR_FUNDO;
                    matrizResultado[coluna-1][linha+1][1] = COR_FUNDO; matrizResultado[coluna][linha+1][1] = COR_FUNDO; matrizResultado[coluna+1][linha+1][1] = COR_FUNDO;
                    
                    matrizResultado[coluna-1][linha-1][2] = COR_FUNDO; matrizResultado[coluna][linha-1][2] = COR_FUNDO; matrizResultado[coluna+1][linha-1][2] = COR_FUNDO;
                    matrizResultado[coluna-1][linha][2] = COR_FUNDO; matrizResultado[coluna][linha][2] = COR_FUNDO; matrizResultado[coluna+1][linha][2] = COR_FUNDO;
                    matrizResultado[coluna-1][linha+1][2] = COR_FUNDO; matrizResultado[coluna][linha+1][2] = COR_FUNDO; matrizResultado[coluna+1][linha+1][2] = COR_FUNDO;
                }
            }
        }
    }
    
    /**
     * USAR IMAGEM BINARIZADA
     * Preto - fundo e Branco - o objeto
     * O pixel do objeto continuado sendo do objeto se todos os pixels envolta forem do objeto.
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void erosao_pixel(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
        copiar(matrizOrigem, matriz);
    	
    	final int COR_OBJETO = 255;
    	final int COR_FUNDO = 0;
    	for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	//Verifica se o pixel pertence ao objeto
            	if(matriz[coluna][linha][0] == COR_OBJETO){
            		if(matriz[coluna-1][linha-1][0] == COR_OBJETO && matriz[coluna][linha-1][0] == COR_OBJETO && matriz[coluna+1][linha-1][0] == COR_OBJETO
            		&& matriz[coluna-1][linha][0] == COR_OBJETO && matriz[coluna][linha][0] == COR_OBJETO && matriz[coluna+1][linha][0] == COR_OBJETO
            		&& matriz[coluna-1][linha+1][0] == COR_OBJETO && matriz[coluna][linha+1][0] == COR_OBJETO && matriz[coluna+1][linha+1][0] == COR_OBJETO
            		){
            			matrizResultado[coluna][linha][0] = COR_OBJETO;
                        matrizResultado[coluna][linha][1] = COR_OBJETO;
                        matrizResultado[coluna][linha][2] = COR_OBJETO;
            		} else {
            			matrizResultado[coluna][linha][0] = COR_FUNDO;
                        matrizResultado[coluna][linha][1] = COR_FUNDO;
                        matrizResultado[coluna][linha][2] = COR_FUNDO;
            		}
            	}
            }
        }
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void abertura(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	erosao(matriz, matrizResultado);
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizResultado, matrizAux);
    	dilatacao(matrizResultado, matrizAux);
    	copiar(matrizAux, matrizResultado);
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void fechamento(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	dilatacao(matriz, matrizResultado);
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizResultado, matrizAux);
    	erosao(matrizResultado, matrizAux);
    	copiar(matrizAux, matrizResultado);
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void abertura_pixel(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	erosao_pixel(matriz, matrizResultado);
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizResultado, matrizAux);
    	dilatacao_pixel(matrizAux, matrizResultado);
    }
    
    /**
     * 
     * @param matriz
     * @param matrizResultado
     */
    public void fechamento_pixel(int[][][] matrizOrigem, int[][][] matrizResultado){
    	int[][][] matriz = new int[largura][altura][3];
    	copiar(matrizOrigem, matriz);
    	
    	dilatacao_pixel(matriz, matrizResultado);
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizResultado, matrizAux);
    	erosao_pixel(matrizResultado, matrizAux);
    	copiar(matrizAux, matrizResultado);
    }
    

    /**
     * Realiza a contagem de objetos usando o conceito de vizinhaca de 4 pixels
     * @param matriz
     * @param matrizRegioes
     */
    public void vizinhanca_4(int[][][] matriz, int[][] matrizRegioes){
    	
        int c = 0;
        List<Integer> listaRelacao = new ArrayList<Integer>();
        listaRelacao.add(0, null);
    	int vs;
        int ve;
        
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                if(matriz[coluna][linha][0] == 0){
                    vs = matrizRegioes[coluna][linha-1];
                    ve = matrizRegioes[coluna-1][linha];
                    
                    if(vs == 0 && ve == 0){
                        c++;
                        matrizRegioes[coluna][linha] = c;
                        	
                        listaRelacao.add(c,c);
                    } else {
                        if((vs != 0 && ve != 0) && (vs != ve)){
                        	matrizRegioes[coluna][linha] = vs;
                            listaRelacao.set(c, vs);
                            
                        }
                        
                    	if(vs!=0){
                    		matrizRegioes[coluna][linha] = vs;
                    	} else {
                    		if(ve != 0){
                        		matrizRegioes[coluna][linha] = ve;
                    		}
                    	}
                    }
                }
            }
        }
        
        
        boolean teveTroca = false;
        
        do{
        	teveTroca = false;
	        ArrayList<Integer> listaRelacaoCopia = new ArrayList<Integer>(listaRelacao);
	        for (int i = 1; i<listaRelacaoCopia.size(); i++) {
	        	if(i != listaRelacaoCopia.get(i)){
	        		if(listaRelacaoCopia.get(listaRelacao.get(i)) != listaRelacao.get(i) ){
	        			listaRelacao.set(i, listaRelacaoCopia.get(listaRelacao.get(i)));
	        			teveTroca=true;
	        		}
	        	}
	        		
			}
        } while(teveTroca);
        
        
        System.out.println("Contador: "+c+" lista.size "+listaRelacao.size());
        for(int i = 1; i<listaRelacao.size(); i++){
        	System.out.println(i+"\t"+listaRelacao.get(i));
        }
        
        
        
        //L� a listaRelacao e substitui na matrizRegioes
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	if(matrizRegioes[coluna][linha] != 0)
            		matrizRegioes[coluna][linha] = listaRelacao.get(matrizRegioes[coluna][linha]);
            }    
        }
        
        int nObjetos=0;
        List<Integer> listaRelacaoSemRepitacao = new ArrayList<Integer>();
        for (Integer integer : listaRelacao) {
			if(!listaRelacaoSemRepitacao.contains(integer)){
				listaRelacaoSemRepitacao.add(integer);
			}
		}
        //no listaRelacaoSemRepitacao.size() deve subtrair 1 por que na posicao 0 da lista e null
        nObjetos = listaRelacaoSemRepitacao.size()-1;
        
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	
            	if(matrizRegioes[coluna][linha] != 0){
            		matrizRegioes[coluna][linha] = listaRelacaoSemRepitacao.indexOf(matrizRegioes[coluna][linha]);
            	}
            		
            		
            }    
        }
        
        /*
        for (Integer integer : listaRelacaoSemRepitacao) {
			System.out.println(integer);
		}*/
        
        System.out.println("N Objetos: "+ nObjetos);
        
        int tonsDeCinza = 255/nObjetos;
        
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
            	matriz[coluna][linha][0] = matrizRegioes[coluna][linha]*tonsDeCinza;
            	matriz[coluna][linha][1] = matrizRegioes[coluna][linha]*tonsDeCinza;
            	matriz[coluna][linha][2] = matrizRegioes[coluna][linha]*tonsDeCinza;
            }    
        }
    }
    
    public void histograma(int[][][] matriz, int[][] histograma){
    	final int NUM_PIXEL = largura*altura;
    	System.out.println(NUM_PIXEL);
    	int[] arrayHistograma = new int[256];
    	
    	 for (int linha = 0; linha <= 100; linha++) {
             for (int coluna = 0; coluna <= 255; coluna++) {
            	 histograma[coluna][linha] = 255;
             }
         }
    	
    	for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
            	arrayHistograma[matriz[coluna][linha][0]]++;
            }    
        }
    	
    	for (int i = 0; i<arrayHistograma.length; i++) {
    		double porcentagem = arrayHistograma[i]*100/NUM_PIXEL;
    		arrayHistograma[i] = (int)porcentagem;
			//System.out.println(arrayHistograma[i]);
		}
    	
    	for(int i = 0; i<arrayHistograma.length; i++){
    		if(arrayHistograma[i] > 0){
    			for(int k = 0; k<=arrayHistograma[i]; k++){
    				histograma[255-i][100-(arrayHistograma[i]-k)] = 0;
    			}
    		}
    	}
    	
    }
    
    public int[] histograma(int[][] matriz){
        int hg[] = new int[256];
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int c = matriz[coluna][linha];
                hg[c]++;
            }
        }
        return hg;
    }
    
    public int[][] histograma_rgb(int[][][] matriz){
        int hg[][] = new int[256][3];
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                hg[r][0]++;
                hg[g][1]++;
                hg[b][2]++;
            }
        }
        return hg;
    }
    
    public void projecaoVertical(int[][][] matriz, int[][] projecaoVertical){
    	final int NUM_PIXEL = largura*altura;
    	System.out.println(NUM_PIXEL);
    	int[] arrayProjecaoVertical = new int[largura+1];
    	
    	brancoMatriz(projecaoVertical);
    	for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
            	if(matriz[coluna][linha][0] == 0)
            		arrayProjecaoVertical[coluna]++;
            }    
    	}
    	
    	
    	/*
    	for (int i : arrayProjecaoVertical) {
			System.out.println(i);
		}*/
    	
    	for(int i = 0; i<arrayProjecaoVertical.length; i++){
    		if(arrayProjecaoVertical[i] > 0){
    			for(int k = 0; k<=arrayProjecaoVertical[i]; k++){
    				//projecaoVertical[i][(arrayProjecaoVertical[i]-k)] = 0;
    			}
    		}
    	}
    	
    }
    
    public void projecaoHorizontal(int[][][] matriz, int[][] projecaoHorizontal ){
    	final int NUM_PIXEL = largura*altura;
    	System.out.println(NUM_PIXEL);
    	int[] arrayHorizontal = new int[altura+1];
    	brancoMatriz(projecaoHorizontal);
    	
    	for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
            	if(matriz[coluna][linha][0] == 0)
            		arrayHorizontal[linha]++;
            }    
        }
    	
    	for(int i = 0; i<arrayHorizontal.length; i++){
    		if(arrayHorizontal[i] > 0){
    			for(int k = 0; k<=arrayHorizontal[i]; k++){
    				//projecaoHorizontal[arrayHorizontal[i]-k][i] = 0;
    			}
    		}
    		
    	}
    }
    
    public int[][][] equalizar_rgb(int[][][] imagem){
    	int result[][][] = new int[imagem.length][imagem[0].length][3];
    	
    	int r[][] = new int[imagem.length][imagem[0].length];
    	int g[][] = new int[imagem.length][imagem[0].length];
    	int b[][] = new int[imagem.length][imagem[0].length];
    	
    	for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
            	r[coluna][linha] = imagem[coluna][linha][0];
            	g[coluna][linha] = imagem[coluna][linha][1];
            	b[coluna][linha] = imagem[coluna][linha][2];
            }
    	}
    	
    	r = equalizar(r);
    	g = equalizar(g);
    	b = equalizar(b);
    	
    	for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
            	result[coluna][linha][0] = r[coluna][linha];
            	result[coluna][linha][1] = g[coluna][linha];
            	result[coluna][linha][2] = b[coluna][linha];
            }
    	}
    	
    	
    	return result;
    }
    
    public int[][] equalizar(int[][] imagem){ //recebe imagem original
        int hg[]; //histograma da imagem
        int he[] = new int[256]; //Histograma equalizadoo.
        int result[][] = new int[imagem.length][imagem[0].length]; //imagem equalizado
       
        double cdf[] = new double[256]; //Cumulativo
        int i,j, k,n;
        hg = histograma(imagem); //gera histograma da imagem original
        n = imagem.length * imagem[0].length; //pega qtd total de pixels da imagem
       
        cdf[0] = hg[0];
        for (i=1;i<256;i++) {       // cdf of image
            cdf[i] = cdf[i-1] + hg[i];
            he[i]=(int)Math.round(cdf[i]/n*255); //monta o mapa
        }
       
        for(i=0;i<imagem.length;i++) { //pega o mapa e transforma a imagem
            for(j=0;j<imagem[0].length;j++) {
                k = imagem[i][j];
                result[i][j] = (int)(he[k]);
            }
        }
       
        return result; //devolve imagem equalizado
    }

	private void gerarGrafico(int[] array, double[] cdf) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for(int c = 0; c < 256; c++){
            dataset.setValue(cdf[c], "Vertical", String.valueOf(c));
            dataset2.setValue(array[c], "Vertical", String.valueOf(c));
            
        }
       
        JFreeChart chart = ChartFactory.createBarChart("Histograma","Horizontal", "Vertical", dataset, PlotOrientation.VERTICAL, false,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        ChartFrame frame1 = new ChartFrame("Bar Chart",chart);
        frame1.setVisible(true);
        frame1.setSize(800, 600);
        
        
        JFreeChart chart2 = ChartFactory.createBarChart("Histograma Equalizado","Horizontal", "Vertical", dataset2, PlotOrientation.VERTICAL, false,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p2 = chart2.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        ChartFrame frame2 = new ChartFrame("Bar Chart",chart2);
        frame2.setVisible(true);
        frame2.setSize(800, 600);
	}
    
    public void projecaoVertical(int[][] matriz){

        int largura = matriz.length;
        int altura = matriz[0].length;
       
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        for(int coluna = 0; coluna < largura; coluna++){
            long cont = 0;

            for(int linha = 0; linha < altura; linha++){
                if(matriz[coluna][linha] == 0){
                    cont++;
                }
            }
            dataset.setValue(cont, "Vertical", String.valueOf(coluna));

        }
       
        JFreeChart chart = ChartFactory.createBarChart("Proje��o Vertical","Horizontal", "Vertical", dataset, PlotOrientation.VERTICAL, false,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        ChartFrame frame1 = new ChartFrame("Bar Chart",chart);
        frame1.setVisible(true);
        frame1.setSize(1024, 768);
    }
    
    public int[][] subtrairMatriz(int[][][] matrizAnterior, int[][][] matrizAtual){
        int[][] matrizRetorno = new int[largura][altura];
        brancoMatriz(matrizRetorno);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                if(matrizAnterior[coluna][linha][0] != matrizAtual[coluna][linha][0]){
                    matrizRetorno[coluna][linha] =  255;
                    
                } else {
                    matrizRetorno[coluna][linha] = 0;
                }
               
            }
        }
        
        return matrizRetorno; 
    }
    
    public int[][] subtracao(int[][][] matrizAnterior, int[][][] matrizAtual){
    	int[][] matrizRetorno = new int[largura][altura];
        brancoMatriz(matrizRetorno);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = Math.abs(matrizAnterior[coluna][linha][0] - matrizAtual[coluna][linha][0]);
                matrizRetorno[coluna][linha] = valor;
                
            }
        }
        
        return matrizRetorno;
        
        
    }
    
    
    
    /**
     * 
     * @param matriz 
     */
    public void zerarMatriz(int[][] matriz){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha] = 0;
            }
        }
    }
    
    public void brancoMatriz(int[][] matriz){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha] = 255;
            }
        }
    }
    
    public void brancoMatriz(int[][][] matriz){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha][0] = 255;
                matriz[coluna][linha][1] = 255;
                matriz[coluna][linha][2] = 255;
            }
        }
    }
    
    public void pretoMatriz(int[][][] matriz){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha][0] = 0;
                matriz[coluna][linha][1] = 0;
                matriz[coluna][linha][2] = 0;
            }
        }
    }
    
    /**
     * 
     * @param imagem
     * @param nome
     * @throws IOException
     */
    public void salvar(BufferedImage imagem, String nome) throws IOException{
    	File imgSalva = new File(nome+".jpg");
    	ImageIO.write(imagem, "jpg", imgSalva);
    	System.out.println(imgSalva.getAbsolutePath());
    	
    	
    }
    /**
     * 
     * @param lista
     * @return
     */
    public int retornaMediana(List<Integer> lista){
      //Ordena a lista
    	Collections.sort(lista);
      //Pega o tamanho da lista soma 1 e divide por 2 para pegar o indice da mediana
      //Exemplo o tamanho da lista é 9 soma 1 da 10 e divide por 2 igual a 5 que é a mediana
    	return lista.get((lista.size()+1)/2);
    }
    
    /**
     * 
     * @param x
     * @return
     */
    public int limitar(int x){
        if(x > 255)
            return 255;
        else 
            if(x<0)
                return 0;
            else
                return x;
    }
    /**
     * 
     * @param matrizOriginal
     * @param matriz
     */
    public void copiar(int[][][] matrizOriginal, int[][][] matriz){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matrizOriginal[coluna][linha][0];
                int g = matrizOriginal[coluna][linha][1];
                int b = matrizOriginal[coluna][linha][2];
                
                matriz[coluna][linha][0] = r;
                matriz[coluna][linha][1] = g;
                matriz[coluna][linha][2] = b;
            }
        }
    }
    
    public int[][] otsu(int[][] matriz){
    	byte[] srcData = new byte[matriz.length*matriz[0].length];
    	int c = 0;
    	for (int linha = 0; linha < matriz[0].length; linha++) {
            for (int coluna = 0; coluna < matriz.length; coluna++) {
            	srcData[c] = (byte) matriz[coluna][linha];
            	c++;
            }
    	}
    	OtsuThresholder otsu = new OtsuThresholder();
    	int threshold = otsu.doThreshold(srcData);
    	System.out.println("Limiar calculado: "+threshold);
    	return limiar_threshould(threshold, matriz);
    	
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }
    
    
    
}
