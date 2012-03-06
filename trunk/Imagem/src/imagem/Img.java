package imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Img {

    private BufferedImage imagem;
    private BufferedImage imagemResultado;
    private int largura;
    private int altura;
    int[][][] matriz;
    /**
     * Usado onde Ã© bescessÃ¡rio usar uma mÃ¡scara
     */
    int[][][] matrizResultado;

    public void lerArquivo(String arquivo) {
        try {
            imagem = ImageIO.read(new File(arquivo));
            imagemResultado = ImageIO.read(new File(arquivo));
            largura = getImagem().getWidth();
            altura = getImagem().getHeight();
            matriz = new int[largura][altura][3];
            matrizResultado = new int[largura][altura][3];
            /*
             * Percore a matriz de cima para baixo da esquerda para a direita
             */
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    Color pixel = new Color(getImagem().getRGB(coluna, linha));
                    matriz[coluna][linha][0] = pixel.getRed();
                    matrizResultado[coluna][linha][0] = pixel.getRed();
                    matriz[coluna][linha][1] = pixel.getGreen();
                    matrizResultado[coluna][linha][1] = pixel.getGreen();
                    matriz[coluna][linha][2] = pixel.getBlue();
                    matrizResultado[coluna][linha][2] = pixel.getBlue();
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Img.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lerMatriz() {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                Color pixel = new Color(r, g, b);
                getImagem().setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    public void lerMatrizResultado() {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matrizResultado[coluna][linha][0];
                int g = matrizResultado[coluna][linha][1];
                int b = matrizResultado[coluna][linha][2];
                Color pixel = new Color(r, g, b);
                getImagemResultado().setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    public void lerMatrizCinza() {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][0];
                int b = matriz[coluna][linha][0];
                Color pixel = new Color(r, g, b);
                getImagem().setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    public void azul() {
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                Color pixel = new Color(r, g, b);
                getImagem().setRGB(coluna, linha, pixel.getRGB());
            }
        }
    }
    
    /**
     * Altera a matriz original
     */
    public void cinza(){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
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
     * Deixa a matriz original e altera a matrizResultado
     * NÃO SERVE PARA TRANSFORMAR A IMAGEM EM PRETRO E BRANCO PARA SER USADO EM OUTRA FUNÇÂO PARA ISSO USE cinza()
     */
    public void cinzaResultado(){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                //tons de cinza
               // int cinza = (r + g + b)/3;
                int cinza = (int) (r*0.3+g*0.59+b*0.11);
                //seta os tons de cinza na matriz
                matrizResultado[coluna][linha][0] = cinza;
                matrizResultado[coluna][linha][1] = cinza;
                matrizResultado[coluna][linha][2] = cinza;
            }
        }
    }
    
    /**
     * Filtro Passa Baixa (DiminuiÃ§Ã£o de ruido) usar com tons de cinza.
     * (L-1, C-1)   (L-1, C  )  (L-1, C+1)
     * (L  , C-1)   (L  , C  )  (L  , C+1)
     * (L+1, C-1)   (L+1, C  )  (L+1, C+1)
     **/
    public void media(){
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
     **/
    public void mediana(){
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
    
    public void fpbMaior(){
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
    
    public void fpbMenor(){
        
    }
    
    public void sobel(){
        
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
    
    public void prewitt(){
        
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
     */
    public void laplaciano_8(){
        
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
     */
    public void laplaciano_menos_8(){
        
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
     */
    public void laplaciano_4(){
        
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
     */
    public void laplaciano_menos_4(){
        
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
     */
    public void dilataco_pixel(){
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
    
    public void dilataco(){
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
    
    public void erosao(){
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
     */
    public void erosao_pixel(){
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
    
    public void brilho(Integer valor){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0] + valor;
                int g = matriz[coluna][linha][1] + valor;
                int b = matriz[coluna][linha][2] + valor;
                
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
     * Feito por diversao o professsor nï¿½o fez na aula mas pediu para fazer :)
     * @param valor
     */
    public void contraste(double valor){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                double r = matriz[coluna][linha][0] * (valor);
                double g = matriz[coluna][linha][1] * (valor);
                double b = matriz[coluna][linha][2] * (valor);
                
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
    
    public void negativo(){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = 255-matriz[coluna][linha][0];
                int g = 255-matriz[coluna][linha][1];
                int b = 255-matriz[coluna][linha][2];
                
                matriz[coluna][linha][0] = r;
                matriz[coluna][linha][1] = g;
                matriz[coluna][linha][2] = b;
            }
        }
    }
    
    public void negativoResultado(){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = 255-matrizResultado[coluna][linha][0];
                int g = 255-matrizResultado[coluna][linha][1];
                int b = 255-matrizResultado[coluna][linha][2];
                
                matrizResultado[coluna][linha][0] = r;
                matrizResultado[coluna][linha][1] = g;
                matrizResultado[coluna][linha][2] = b;
            }
        }
    }
    
    
    //BinarizaÃ§Ã£o usar com tons de cinza
    public void limiar_threshould(int limiar){
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
    
    public void limiar_threshould_resultado(int limiar){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matrizResultado[coluna][linha][0];
                int g = matrizResultado[coluna][linha][1];
                int b = matrizResultado[coluna][linha][2];
                
                if(r > limiar)
                    matrizResultado[coluna][linha][0] = 255;
                else
                    matrizResultado[coluna][linha][0] = 0;
                
                if(g > limiar)
                    matrizResultado[coluna][linha][1] = 255;
                else
                    matrizResultado[coluna][linha][1] = 0;
                
                if(b > limiar)
                    matrizResultado[coluna][linha][2] = 255;
                else
                    matrizResultado[coluna][linha][2] = 0;
            }
        }
    }
    
    public void limiar_threshould_inverso(int limiar){
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
    
    //nescessÃ¡rio usar a binarizaÃ§Ã£o
    public void densidade(){
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
        System.out.println("NÃºmero de pixels :"+ nunPixel+" Altura: "+altura+" x Largura: "+largura);
        System.out.println("Densidade "+ densidade + "%");
   }
    
    public void densidadeResultado(){
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
        System.out.println("Densidade Resultado: ");
        System.out.println("Pontos Pretos: "+ ptosPreto);
        System.out.println("NÃºmero de pixels :"+ nunPixel+" Altura: "+altura+" x Largura: "+largura);
        System.out.println("Densidade "+ densidade + "%");
   }
    
    
    public void salvar(BufferedImage imagem, String nome) throws IOException{
    	File imgSalva = new File(nome+".jpg");
    	ImageIO.write(imagem, "jpg", imgSalva);
    	System.out.println(imgSalva.getAbsolutePath());
    	
    	
    }
    
    public int retornaMediana(List<Integer> lista){
      //Ordena a lista
    	Collections.sort(lista);
      //Pega o tamanho da lista soma 1 e divide por 2 para pegar o indice da mediana
      //Exemplo o tamanho da lista é 9 soma 1 da 10 e divide por 2 igual a 5 que é a mediana
    	return lista.get((lista.size()+1)/2);
    }
    
    public int limitar(int x){
        if(x > 255)
            return 255;
        else 
            if(x<0)
                return 0;
            else
                return x;
    }
   
    public BufferedImage getImagem() {
        return imagem;
    }
    
    public BufferedImage getImagemResultado() {
        return imagemResultado;
    }
}
