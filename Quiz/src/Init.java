import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.UnsupportedLookAndFeelException;

public class Init {
	
	static Tela tela;
	static Object[][] questoes;
	static Object[][] alternativas;
	static int themeIndex = -1;
	public static void readFile() throws IOException{
		FileReader fr = new FileReader(new File("settings/settings.in"));
		Scanner scanner = new Scanner(fr);
		{
			themeIndex = Integer.parseInt(scanner.nextLine());
			int quantidadeDeQuestoes = Integer.parseInt(scanner.nextLine());
			questoes = new Object[quantidadeDeQuestoes][3];
			alternativas = new Object[quantidadeDeQuestoes][5];
			for(int i=0; i<quantidadeDeQuestoes; i++) {
				scanner.nextLine();
				questoes[i][0] = scanner.nextLine();
				questoes[i][1] = scanner.nextLine();
				questoes[i][2] = Integer.parseInt(scanner.nextLine());
				alternativas[i][0] = scanner.nextLine();
				alternativas[i][1] = scanner.nextLine();
				alternativas[i][2] = scanner.nextLine();
				alternativas[i][3] = scanner.nextLine();
				alternativas[i][4] = scanner.nextLine();
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
