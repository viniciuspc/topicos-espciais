package imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Img {

    private BufferedImage imagem;
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
                int cinza = (r + g + b)/3;
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
     * TODO Implementar usando mascara 3x3.
     **/
    public void media(){
        int[][][] mascara = new int[3][3][3];
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                for(int linhaMascara = 0; linhaMascara < 3;linhaMascara ++ ){
                    for(int colunaMascara = 0; colunaMascara < 3;colunaMascara ++ ){
                        int r = matriz[coluna][linha][0];
                        int g = matriz[coluna][linha][1];
                        int b = matriz[coluna][linha][2];
                        //tons de cinza
                        int cinza = (r + g + b)/3;
                        
                    }
                }
                       //Atribuir os pontos
                
            }
        }
    }
    
    /**
     * Filtro Passa Baixa (Diminuição de ruido) usar com tons de cinza.
     * Mediana ordena e pega o do meio.
     * (L-1, C-1)   (L-1, C  )  (L-1, C+1)
     * (L  , C-1)   (L  , C  )  (L  , C+1)
     * (L+1, C-1)   (L+1, C  )  (L+1, C+1)
     * TODO Implementar usando mascara 3x3.
     **/
    public void mediana(){
        
        for (int linha = 1; linha < altura-2; linha++) {
            for (int coluna = 1; coluna < largura-2; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
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
   
    public BufferedImage getImagem() {
        return imagem;
    }
}
