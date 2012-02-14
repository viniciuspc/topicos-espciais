package imagem;

public class Imagem {
    
    public static void main(String[] args) {
        
        Img i = new Img();
        i.lerArquivo("Imagem.jpg");
        i.lerMatriz();
        
        
        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.exibir();
        
        
        
    }
}
