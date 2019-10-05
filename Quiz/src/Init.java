import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.UnsupportedLookAndFeelException;

public class Init {
	
	static Tela tela;
	static Object[][] questoes;
	static Object[][] alternativas;
	public static void readFile() throws IOException{
		FileReader fr = new FileReader(new File("perguntas/alternativas.in"));
		Scanner scanner = new Scanner(fr);
		{
			int quantidadeDeQuestoes = scanner.nextInt();
			questoes = new Object[quantidadeDeQuestoes][3];
			alternativas = new Object[quantidadeDeQuestoes][5];
			int questaoAtual = -1;
			while(scanner.next().equalsIgnoreCase("r")) {
				scanner.nextLine();
				questaoAtual++;
				questoes[questaoAtual][0] = scanner.nextLine();
				questoes[questaoAtual][1] = scanner.nextLine();
				questoes[questaoAtual][2] = scanner.nextInt();
				scanner.nextLine();
				alternativas[questaoAtual][0] = scanner.nextLine();
				alternativas[questaoAtual][1] = scanner.nextLine();
				alternativas[questaoAtual][2] = scanner.nextLine();
				alternativas[questaoAtual][3] = scanner.nextLine();
				alternativas[questaoAtual][4] = scanner.nextLine();
			}
		}
		scanner.close();
		fr.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, InterruptedException, IOException {
		readFile();
		tela = new Tela();
	}
	
}
