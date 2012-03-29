package imagem;

public class Imagem {

    public static void main(String[] args) {

        //Classe Img
        Visao visao = new Visao();


        int[][][] matriz01 = visao.arquivo_Matriz_Rgb("files/pessoa.jpeg");
        int[][][] matriz02 = visao.arquivo_Matriz_Rgb("files/pessoa.jpeg");
        //visao.binariza(matriz02, 127);
        int[][][] matriz03 = visao.pele(matriz01);
        
        visao.matriz_Arquivo_Rgb(matriz03, "resultado_05.jpg");






        //Instancia e exibe formulario com duas imagens
        Formulario f = new Formulario();
        f.setImagem1(visao.matriz_Imagem_Rgb(matriz01));
        f.setImagem2(visao.matriz_Imagem_Rgb(matriz02));
        f.setImagem3(visao.matriz_Imagem_Rgb(matriz03));
        f.exibir();
    }
}
