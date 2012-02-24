package imagem;

import java.io.IOException;

public class Imagem {

    public static void main(String[] args) throws IOException {
        long agora = System.currentTimeMillis();
        System.out.println();
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        i.lerArquivo("imagem.jpg");
        //Fara modificacoes
        //Soma os ponstos e tira a media 
        //i.cinza();
        //i.brilho(0);
        //i.contraste(0);
        //i.negativo();
        //i.limiar_threshould(151);
        //i.limiar_threshould_inverso(127);
        //i.media();
        i.mediana();
        i.densidade();
        //Da matriz para o buffer
        i.lerMatriz();
        i.lerMatrizResultado();
        //i.salvar(i.getImagem());

        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.setImagemResultado(i.getImagemResultado());
        f.exibir();

        System.out.println("Demorou " + (System.currentTimeMillis() - agora));



    }
}
