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
    
   
    public BufferedImage getImagem() {
        return imagem;
    }
}
