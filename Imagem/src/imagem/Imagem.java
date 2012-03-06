package imagem;

import java.io.IOException;

public class Imagem {

    public static void main(String[] args) throws IOException {
        long agora = System.currentTimeMillis();
        System.out.println();
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        i.lerArquivo("carro_menos_8.jpg");
        //Fara modificacoes
        //Soma os ponstos e tira a media 
        //i.cinza();
        //i.cinzaResultado();
        //i.brilho(80);
        //i.contraste(3);
        //i.negativo();
        //i.limiar_threshould(127);
        //i.limiar_threshould_inverso(127);
        //i.media();
        //i.mediana();
        //i.sobel();
        //i.prewitt();
        //i.laplaciano_8();
        //i.laplaciano_menos_8();
        //i.laplaciano_4();
        //i.laplaciano_menos_4();
        //i.limiar_threshould_resultado(100);
        
        i.erosao();
        //i.dilataco();
        
        //i.fpbMaior();

        
        //i.negativoResultado();
        i.densidade();
        i.densidadeResultado();
        //Da matriz para o buffer
        i.lerMatriz();
        i.lerMatrizResultado();
        //i.salvar(i.getImagemResultado(), "imagem3_prewitt");

        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.setImagemResultado(i.getImagemResultado());
        f.exibir();

        System.out.println("Demorou " + (System.currentTimeMillis() - agora));



    }
}
