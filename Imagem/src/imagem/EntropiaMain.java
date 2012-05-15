/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import java.io.File;
import javax.imageio.ImageIO;

import Formulario.FormularioVarianca;

import processadoresImagem.Img;

/**
 *
 * @author rm61828
 */
public class EntropiaMain {
   public static void main (String[] args) throws Exception{
        BufferedImage imagem = ImageIO.read(new File("imagem2.jpg"));
        BufferedImage imagemVlMedio = ImageIO.read(new File("imagem2.jpg"));
        BufferedImage imagemVarianca = ImageIO.read(new File("imagem2.jpg"));
        Img i = new Img();
        int[][] matriz = i.lerBuffer(imagem);
        int[][] matrizVlMedio = i.lerBuffer(imagemVlMedio);
        int[][] matrizVarianca = i.lerBuffer(imagemVarianca);
        
        int vlMedio = i.vlMedio(matriz);
        int varianca = i.variancia(matriz);
        double entropia = i.entropia(matriz);
        
        System.out.println("Valor Médio: "+vlMedio);
        System.out.println("Variancia do Histograma: "+varianca);
        System.out.println("Entropia: "+entropia);
        
        
        matrizVlMedio = i.limiar_threshould(vlMedio, matrizVlMedio);
        
        entropia = i.entropia(matrizVlMedio);
        System.out.println("\nEntropia com binarizacao"+vlMedio+": "+entropia);
        
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
