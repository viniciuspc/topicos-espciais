package imagem;

import java.io.IOException;

public class Imagem {
    
    public static void main(String[] args) throws IOException {
    	long agora = System.currentTimeMillis();
        System.out.println();
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        i.lerArquivo("Imagem.jpg");
        //Far� modifica��ees
        //Soma os ponstos e tira a media 
       i.cinza();
       // i.brilho(50);
       //i.contraste(50);
        //Da matriz para o buffer
        i.lerMatriz();
        i.salvar(i.getImagem());
        
        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.exibir();
        
        System.out.println("Demorou " + (System.currentTimeMillis() - agora));
        
        
        
    }
}
