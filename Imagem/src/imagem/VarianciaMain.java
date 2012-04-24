/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import processadoresImagem.Img;

/**
 *
 * @author rm61828
 */
public class VarianciaMain {
   public static void main (String[] args) throws Exception{
        BufferedImage imagem = ImageIO.read(new File("imagem3.jpg"));
        BufferedImage imagemResultado = ImageIO.read(new File("imagem3.jpg"));
        Img i = new Img();
        int[][] matriz = i.lerArquivo(imagem);
        System.out.println(i.vlMedio(matriz));
        System.out.println(i.variancia(matriz));
        Formulario f = new Formulario();
        
        i.lerMatriz(matriz, imagem);
        //i.lerMatriz(matrizResultado, imagemResultado);
        f.setImagem(imagem);
        f.setImagemResultado(imagemResultado);
        
        f.exibir2();
        f.getJFrame().setVisible(true);
        
    } 
}
