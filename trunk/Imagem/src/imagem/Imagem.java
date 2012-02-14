package imagem;

public class Imagem {
    
    public static void main(String[] args) {
    	long agora = System.currentTimeMillis();
        System.out.println();
        Img i = new Img();
        //Do arquivo para o buffer para a matriz
        i.lerArquivo("Imagem3.jpg");
        //Fará modificaçõees
        //Soma os ponstos e tira a media 
       // i.cinza();
       // i.brilho(200);
        i.contraste(25);
        //Da matriz para o buffer
        i.lerMatriz();
        
        
        Formulario f = new Formulario();
        f.setImagem(i.getImagem());
        f.exibir();
        
        System.out.println("Demorou " + (System.currentTimeMillis() - agora));
        
        
        
    }
}
