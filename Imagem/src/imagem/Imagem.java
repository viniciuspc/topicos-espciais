package imagem;

public class Imagem {
    
    public static void main(String[] args) {
        
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        i.lerArquivo("Imagem.jpg");
        //Fará modificações
        //Lê os ponstos e tira a media 
        i.cinza();
        i.brilho(30);
        //Da matriz para o buffer
        i.lerMatriz();
        
        
        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.exibir();
        
        
        
    }
}
