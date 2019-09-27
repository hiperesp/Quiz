import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carregamento extends JFrame implements ActionListener{

    public JLabel apoioBarra, texto;
    public JProgressBar barra;
    public JButton botaoJogar;

    public Carregamento(){

        //Montando a tela
        setTitle("Inicializando..");
        setResizable(false);
        setSize(400,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(178, 178,255));

        apoioBarra = new JLabel("apoioBarra");
        apoioBarra.setBounds(160,150,200,50);

        texto = new JLabel("texto");
        texto.setBounds(120,250,200,50);

        botaoJogar = new JButton("Jogar");
        botaoJogar.setBounds(150,350,200,50);

        barra = new JProgressBar(0,100);
        barra.setBounds(150,150,0,50);

        getContentPane().setLayout(null);
        getContentPane().add(apoioBarra);
        getContentPane().add(barra);
        getContentPane().add(texto);
        getContentPane().add(botaoJogar);

        new Thread(new carregar()).start();
    }


    public class carregar implements Runnable{
        public void run(){
            for (int i = 0; i < 101; i++){
                try{

                    Thread.sleep(100);
                    barra.setValue(i);

                    if (barra.getValue() <= 50){
                        texto.setText("Carregando Perguntas..");
                    }
                    else if (barra.getValue() <= 70){
                        texto.setText("Carregando Banco de dados..");
                    }
                    else if (barra.getValue() <= 90){
                        texto.setText("Preparando Ambiente..");
                    }
                    else{
                        texto.setText("Clique e jogue bro :p");
                    }
                }
                catch (Exception erro){
                    JOptionPane.showMessageDialog(null, erro);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoJogar){
            Questoes janelaQuestoes = new Questoes();
            this.dispose();
            janelaQuestoes.setVisible(true);
        }
    }
}