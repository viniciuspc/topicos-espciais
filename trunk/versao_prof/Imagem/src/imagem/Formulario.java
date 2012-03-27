package imagem;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Formulario {

    private BufferedImage imagem1;
    private BufferedImage imagem2;
    private BufferedImage imagem3;

    public void exibir() {

        JFrame f = new JFrame("Fiap - Visão Computacional");
        JPanel p = new JPanel();

        JLabel lblImagem1 = new JLabel();
        ImageIcon icon1 = new ImageIcon(imagem1);
        lblImagem1.setIcon(icon1);

        JLabel lblImagem2 = new JLabel();
        ImageIcon icon2 = new ImageIcon(imagem2);
        lblImagem2.setIcon(icon2);

        JLabel lblImagem3 = new JLabel();
        ImageIcon icon3 = new ImageIcon(imagem3);
        lblImagem3.setIcon(icon3);

        //String dir = "C:/Users/LuiZ/Desktop/Fiap/Projetos/Java/tESTE";
        File listax[] = new File("files").listFiles();
        String lista[] = new String[listax.length];
        for (int i = 0; i < listax.length; i++) {
            lista[i] = listax[i].getName();
        }

        JList list = new JList(lista);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        //p.add(list);
        p.add(lblImagem1);
        p.add(lblImagem2);
        p.add(lblImagem3);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public void setImagem1(BufferedImage imagem1) {
        this.imagem1 = imagem1;
    }

    public void setImagem2(BufferedImage imagem2) {
        this.imagem2 = imagem2;
    }

    public void setImagem3(BufferedImage imagem3) {
        this.imagem3 = imagem3;
    }
}
