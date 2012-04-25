/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import Formulario.FormularioVisao;
import processadoresImagem.Visao;

/**
 *
 * @author rm61828
 */
public class visaoMain {
   public static void main(String[] args){
       Visao visao = new Visao();
       FormularioVisao f = new FormularioVisao();
       
       int[][] imagem = visao.arquivo_Matriz("imagem.jpg");
       f.setImagem(visao.matriz_Imagem(imagem));
       visao.binariza(imagem, 127);
       f.setImagemResultado(visao.matriz_Imagem(imagem));
       
       f.exibir();
       f.getJFrame().setVisible(true);
   }
    
    
}
