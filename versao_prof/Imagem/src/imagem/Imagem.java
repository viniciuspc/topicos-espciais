package imagem;

public class Imagem {

    public static void main(String[] args) {

        //Classe Img
        Visao visao = new Visao();


        int[][] matriz01 = visao.fpa_Sobel(visao.arquivo_Matriz("files/imagem.jpg"));
        int[][] matriz02 = visao.copiar_Matriz(visao.arquivo_Matriz("files/imagem.jpg"));
        //visao.binariza(matriz02, 127);
        int[][] matriz03 = visao.soma(matriz01, matriz02);
        visao.negativo(matriz03);
        
        
        visao.negativo(matriz03);





        //Instancia e exibe formulario com duas imagens
        Formulario f = new Formulario();
        f.setImagem1(visao.matriz_Imagem(matriz01));
        f.setImagem2(visao.matriz_Imagem(matriz02));
        f.setImagem3(visao.matriz_Imagem(matriz03));
        f.exibir();
    }
}
