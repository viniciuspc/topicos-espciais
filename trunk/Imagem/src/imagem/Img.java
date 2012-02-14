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

    public void lerArquivo(String arquivo) {
        try {
            imagem = ImageIO.read(new File(arquivo));
            largura = getImagem().getWidth();
            altura = getImagem().getHeight();
            matriz = new int[largura][altura][3];
            /*
             * Percore a matriz de cima para baixo da esquerda para a direita
             */
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    Color pixel = new Color(getImagem().getRGB(coluna, linha));
                    matriz[coluna][linha][0] = pixel.getRed();
                    matriz[coluna][linha][1] = pixel.getGreen();
                    matriz[coluna][linha][2] = pixel.getBlue();
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
     * Feito por diversão o professsor não fez na aula mas pediu para fazer :)
     * @param valor
     */
    public void contraste(int valor){
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                double r = matriz[coluna][linha][0] * (valor/10);
                double g = matriz[coluna][linha][1] * (valor/10);
                double b = matriz[coluna][linha][2] * (valor/10);
                
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
   
    public BufferedImage getImagem() {
        return imagem;
    }
}
