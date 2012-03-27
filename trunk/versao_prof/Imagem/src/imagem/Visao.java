package imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Visao {

    // Arquivo p/ Matriz *******************************************************
    public int[][][] arquivo_Matriz_Rgb(String arquivo) {
        int[][][] matriz;
        try {
            BufferedImage imagem = ImageIO.read(new File(arquivo));
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            matriz = new int[largura][altura][3];
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    Color pixel = new Color(imagem.getRGB(coluna, linha));
                    matriz[coluna][linha][0] = pixel.getRed();
                    matriz[coluna][linha][1] = pixel.getGreen();
                    matriz[coluna][linha][2] = pixel.getBlue();
                }
            }
        } catch (IOException ex) {
            matriz = new int[0][0][0];
            Logger.getLogger(Visao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;
    }

    public int[][] arquivo_Matriz(String arquivo) {
        int[][] matriz;
        try {
            BufferedImage imagem = ImageIO.read(new File(arquivo));
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            matriz = new int[largura][altura];
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    Color pixel = new Color(imagem.getRGB(coluna, linha));
                    matriz[coluna][linha] = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                }
            }
        } catch (IOException ex) {
            matriz = new int[0][0];
            Logger.getLogger(Visao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;
    }

    // Matriz p/ Arquivo *******************************************************
    public void matriz_Arquivo_Rgb(int[][][] origem, String arquivo) {
        int largura = origem.length;
        int altura = origem[0].length;
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        try {
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    int r = origem[coluna][linha][0];
                    int g = origem[coluna][linha][1];
                    int b = origem[coluna][linha][2];
                    Color pixel = new Color(r, g, b);
                    imagem.setRGB(coluna, linha, pixel.getRGB());
                }
            }
            ImageIO.write(imagem, "JPEG", new File(arquivo));
        } catch (IOException ex) {
            Logger.getLogger(Visao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void matriz_Arquivo(int[][] origem, String arquivo) {
        int largura = origem.length;
        int altura = origem[0].length;
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        try {
            for (int linha = 0; linha < altura; linha++) {
                for (int coluna = 0; coluna < largura; coluna++) {
                    int valor = origem[coluna][linha];
                    Color pixel = new Color(valor, valor, valor);
                    imagem.setRGB(coluna, linha, pixel.getRGB());
                }
            }
            ImageIO.write(imagem, "JPEG", new File(arquivo));
        } catch (IOException ex) {
            Logger.getLogger(Visao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Matriz p/ Imagem ********************************************************
    public BufferedImage matriz_Imagem_Rgb(int[][][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = origem[coluna][linha][0];
                int g = origem[coluna][linha][1];
                int b = origem[coluna][linha][2];
                Color pixel = new Color(r, g, b);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
        return imagem;
    }

    public BufferedImage matriz_Imagem(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = origem[coluna][linha];
                Color pixel = new Color(valor, valor, valor);
                imagem.setRGB(coluna, linha, pixel.getRGB());
            }
        }
        return imagem;
    }

    // Matriz p/ Matriz ********************************************************
    public int[][][] copiar_Matriz_Rgb(int[][][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][][] matriz = new int[largura][altura][3];
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha][0] = origem[coluna][linha][0];
                matriz[coluna][linha][1] = origem[coluna][linha][1];
                matriz[coluna][linha][2] = origem[coluna][linha][2];
            }
        }
        return matriz;
    }

    public int[][] copiar_Matriz(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                matriz[coluna][linha] = origem[coluna][linha];
            }
        }
        return matriz;
    }

    public int[][] tons_Cinza(int[][][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = origem[coluna][linha][0];
                int g = origem[coluna][linha][1];
                int b = origem[coluna][linha][2];
                int cinza = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                matriz[coluna][linha] = cinza;
            }
        }
        return matriz;
    }

    // Auxiliar ****************************************************************
    public int limite_0_255(int valor) {
        if (valor < 0) {
            valor = 0;
        } else if (valor > 255) {
            valor = 255;
        }
        return valor;
    }

    // Brilho ******************************************************************
    public void brilho_Rgb(int[][][] matriz, int brilho) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0] + brilho;
                int g = matriz[coluna][linha][1] + brilho;
                int b = matriz[coluna][linha][2] + brilho;
                matriz[coluna][linha][0] = limite_0_255(r);
                matriz[coluna][linha][1] = limite_0_255(g);
                matriz[coluna][linha][2] = limite_0_255(b);
            }
        }
    }

    public void brilho(int[][] matriz, int brilho) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = matriz[coluna][linha] + brilho;
                matriz[coluna][linha] = limite_0_255(valor);
            }
        }
    }

    // Contraste ***************************************************************
    public void contraste_Rgb(int[][][] matriz, double contraste) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = (int) (matriz[coluna][linha][0] * contraste);
                int g = (int) (matriz[coluna][linha][1] * contraste);
                int b = (int) (matriz[coluna][linha][2] * contraste);
                matriz[coluna][linha][0] = limite_0_255(r);
                matriz[coluna][linha][1] = limite_0_255(g);
                matriz[coluna][linha][2] = limite_0_255(b);
            }
        }
    }

    public void contraste(int[][] matriz, double contraste) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = (int) (matriz[coluna][linha] * contraste);
                matriz[coluna][linha] = limite_0_255(valor);
            }
        }
    }

    // Binariza
    public void binariza(int[][] matriz, int limiar) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = matriz[coluna][linha];
                if (valor >= limiar) {
                    valor = 255;
                } else {
                    valor = 0;
                }
                matriz[coluna][linha] = valor;
            }
        }
    }

    // Negativo
    public void negativo_Rgb(int[][][] matriz) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int r = matriz[coluna][linha][0];
                int g = matriz[coluna][linha][1];
                int b = matriz[coluna][linha][2];
                matriz[coluna][linha][0] = 255 - r;
                matriz[coluna][linha][1] = 255 - g;
                matriz[coluna][linha][2] = 255 - b;
            }
        }
    }

    public void negativo(int[][] matriz) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        for (int linha = 0; linha < altura; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                int valor = matriz[coluna][linha];
                matriz[coluna][linha] = 255 - valor;
            }
        }
    }

    // Filtro Passa-Baixa
    public int[][] fpb_Media3x3(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int valor = (+1 * (origem[coluna - 1][linha - 1]) + 1 * (origem[coluna][linha - 1]) + 1 * (origem[coluna + 1][linha - 1])
                        + 1 * (origem[coluna - 1][linha]) + 1 * (origem[coluna][linha]) + 1 * (origem[coluna + 1][linha])
                        + 1 * (origem[coluna - 1][linha + 1]) + 1 * (origem[coluna][linha + 1]) + 1 * (origem[coluna + 1][linha + 1])) / 9;
                matriz[coluna][linha] = valor;
            }
        }
        return matriz;
    }

    public int[][] fpb_Mediana3x3(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int vetor[] = {
                    origem[coluna - 1][linha - 1],
                    origem[coluna][linha - 1],
                    origem[coluna + 1][linha - 1],
                    origem[coluna - 1][linha],
                    origem[coluna][linha],
                    origem[coluna + 1][linha],
                    origem[coluna - 1][linha + 1],
                    origem[coluna][linha + 1],
                    origem[coluna + 1][linha + 1]
                };
                //Ordena o vetor
                Arrays.sort(vetor);
                matriz[coluna][linha] = vetor[4];
            }
        }
        return matriz;
    }

    // Filtro Passa-Alta
    public int[][] fpa_Sobel(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int vh = (- 1 * (origem[coluna - 1][linha - 1]) - 2 * (origem[coluna][linha - 1]) - 1 * (origem[coluna + 1][linha - 1])
                        + 1 * (origem[coluna - 1][linha + 1]) + 2 * (origem[coluna][linha + 1]) + 1 * (origem[coluna + 1][linha + 1]));
                int vv = (- 1 * (origem[coluna - 1][linha - 1]) + 1 * (origem[coluna + 1][linha - 1])
                        - 2 * (origem[coluna - 1][linha]) + 2 * (origem[coluna + 1][linha])
                        - 1 * (origem[coluna - 1][linha + 1]) + 1 * (origem[coluna + 1][linha + 1]));
                int vs = (int) Math.sqrt(Math.pow(vh, 2) + Math.pow(vv, 2));
                vs = limite_0_255(vs);
                matriz[coluna][linha] = vs;
            }
        }
        return matriz;
    }

    public int[][] fpa_Gaussiana(int[][] origem) {
        int largura = origem.length;
        int altura = origem[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int valor = (-1 * (origem[coluna - 1][linha - 1]) - 1 * (origem[coluna][linha - 1]) - 1 * (origem[coluna + 1][linha - 1])
                        - 1 * (origem[coluna - 1][linha]) + 8 * (origem[coluna][linha]) - 1 * (origem[coluna + 1][linha])
                        - 1 * (origem[coluna - 1][linha + 1]) - 1 * (origem[coluna][linha + 1]) - 1 * (origem[coluna + 1][linha + 1]));
                matriz[coluna][linha] = limite_0_255(valor);
            }
        }
        return matriz;
    }
    // *************************************************************************

    public int[][] diferenca(int limiar, int[][] matriz0, int[][] matriz1) {
        int largura = matriz0.length;
        int altura = matriz0[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int valor = Math.abs(matriz1[coluna][linha] - matriz0[coluna][linha]);
                if (valor > limiar) {
                    matriz[coluna][linha] = 255;
                } else {
                    matriz[coluna][linha] = 0;
                }
            }
        }
        return matriz;
    }
    
    public int[][] soma(int[][] matriz0, int[][] matriz1) {
        int largura = matriz0.length;
        int altura = matriz0[0].length;
        int[][] matriz = new int[largura][altura];
        for (int linha = 1; linha < altura - 1; linha++) {
            for (int coluna = 1; coluna < largura - 1; coluna++) {
                int valor = matriz1[coluna][linha] + matriz0[coluna][linha];
                valor = limite_0_255(valor);
                matriz[coluna][linha] = valor;
            }
        }
        return matriz;
    }
}
