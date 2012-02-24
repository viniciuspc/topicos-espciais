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
     * Usado onde é bescessário usar uma máscara
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
     * Filtro Passa Baixa (Diminuição de ruido) usar com tons de cinza.
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
     * Filtro Passa Baixa (Diminuição de ruido) usar com tons de cinza.
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
    
    /**
     * (x1(L-1, C-1) +  x2(L-1, C  ) + x3(L-1, C+1)
     * + x4(L  , C-1) +  x5(L  , C  ) + x6(L  , C+1)
     * + x7(L+1, C-1) +  x8(L+1, C  ) + x9(L+1, C+1))
     * /(x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9)
     * TODO Implementar usando mascara 3x3.
     **/
    public void metodoX(){
        
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
     * Feito por diversao o professsor n�o fez na aula mas pediu para fazer :)
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
    
    
    //Binarização usar com tons de cinza
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
    
    //nescessário usar a binarização
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
        System.out.println("Número de pixels :"+ nunPixel+" Altura: "+altura+" x Largura: "+largura);
        System.out.println("Densidade "+ densidade + "%");
   }
    
    
    public void salvar(BufferedImage imagem) throws IOException{
    	File imgSalva = new File("salvo.jpg");
    	ImageIO.write(imagem, "jpg", imgSalva);
    	System.out.println(imgSalva.getAbsolutePath());
    	
    	
    }
    
    public int retornaMediana(List<Integer> lista){
    	Collections.sort(lista);
    	return lista.get((lista.size()+1)/2);
    }
   
    public BufferedImage getImagem() {
        return imagem;
    }
    
    public BufferedImage getImagemResultado() {
        return imagemResultado;
    }
}
