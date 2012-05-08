/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Formulario.Formulario;
import Formulario.FormularioVarianca;
import processadoresImagem.Img;

/**
 *
 * @author rm61828
 */
public class VarianciaMain {
   public static void main (String[] args) throws Exception{
        BufferedImage imagem = ImageIO.read(new File("imagem.jpg"));
        BufferedImage imagemVlMedio = ImageIO.read(new File("imagem.jpg"));
        BufferedImage imagemVarianca = ImageIO.read(new File("imagem.jpg"));
        Img i = new Img();
        int[][] matriz = i.lerBuffer(imagem);
        int[][] matrizVlMedio = i.lerBuffer(imagemVlMedio);
        int[][] matrizVarianca = i.lerBuffer(imagemVarianca);
        int vlMedio = i.vlMedio(matriz);
        int varianca = i.variancia(matriz);
        System.out.println("Valor Médio: "+vlMedio);
        System.out.println("Variancia do Histograma: "+varianca);
        matrizVlMedio = i.limiar_threshould(vlMedio, matrizVlMedio);
        matrizVarianca = i.limiar_threshould(varianca, matrizVarianca);
        FormularioVarianca f = new FormularioVarianca();
        
        imagem = i.lerMatriz(matriz);
        imagemVlMedio = i.lerMatriz(matrizVlMedio);
        imagemVarianca = i.lerMatriz(matrizVarianca);
        f.setImagem(imagem);
        f.setImagemVlMedio(imagemVlMedio);
        f.setImagemVarianca(imagemVarianca);
        
        f.exibir();
        f.getJFrame().setVisible(true);
        
    } 
}
