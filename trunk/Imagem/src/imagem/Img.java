package imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

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
    public int[][][]  lerArquivo(BufferedImage imagem) {
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
    /**
     * 
     * @param matrizOriginal
     * @param matriz
     * @param imagem
     */
    public void azul(int[][][] matrizOriginal, int[][][] matriz, BufferedImage imagem) {
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
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
    	
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
    	
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
    	
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
    	
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
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
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
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
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
    public void limiar_threshould(int limiar, int[][][] matrizOrigem, int[][][] matriz){
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
     * 
     * @param limiar
     * @param matrizOriginal
     * @param matriz
     */
    public void limiar_threshould_inverso(int limiar, int[][][] matrizOriginal, int[][][] matriz){
    	if(matrizOriginal == matriz)
    		matrizOriginal = matriz.clone();
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
    public void media(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
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
    public void mediana(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
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
    public void fpbMaior(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
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
    public void prewitt(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
    	
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
    public void laplaciano_8(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
    	
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
    public void laplaciano_menos_8(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
    	
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
    public void laplaciano_4(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
    	
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
    public void laplaciano_menos_4(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz, matrizResultado);
    	
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
    public void abertura_pixel(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz,matrizResultado);
    	
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
    public void fechamento_pixel(int[][][] matriz, int[][][] matrizResultado){
    	if(matriz == matrizResultado)
    		copiar(matriz,matrizResultado);
    	
    	dilatacao_pixel(matriz, matrizResultado);
    	int[][][] matrizAux = new int[largura][altura][3];
    	copiar(matrizResultado, matrizAux);
    	erosao_pixel(matrizResultado, matrizAux);
    	copiar(matrizAux, matrizResultado);
    }
    
    public void vizinhanca_4(int[][][] matriz, int[][] matrizRegioes){
        int c = 0;
        List<Integer> listaRelacao = new ArrayList<Integer>();
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
                        listaRelacao.add(c);
                    } else {
                        matrizRegioes[coluna][linha] = c;
                        if((vs != 0 && ve != 0) && (vs != ve)){
                            
                            listaRelacao.add(c, vs);
                        }
                        /*
                    	if(vs!=0){
                    		matrizRegioes[coluna][linha] = vs;
                    	} else {
                    		if(ve != 0){
                        		matrizRegioes[coluna][linha] = ve;
                    		}
                    	}*/
                    	
                    }
                }
            }
        }
        
        System.out.println("Numero de objetos: "+c+" lista.size "+listaRelacao.size());
        for (Integer integer : listaRelacao) {
            System.out.println(integer);
        }
    }
    
    /**
     * 
     * @param vs
     * @param ve
     * @return 
     */
    public int regraB(int vs, int ve){
        return ve;
    }
    
    /**
     * 
     * @param vs
     * @param ve
     * @return 
     */
    public int regraC(int vs, int ve){
        return vs;
    }
    
    /**
     * 
     * @param vs
     * @param ve
     * @return 
     */
    public int regraD(int vs, int ve){
        return vs;
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

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }
    
    
    
}
