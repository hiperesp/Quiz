import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class Tela extends JFrame implements QuizConstants, ActionListener {
	
	private static final long serialVersionUID = 8711731406589100246L;

	Object[][] questoes = Init.questoes;
	Object[][] alternativas = Init.alternativas;
	
	public Tela() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, InterruptedException, IOException {
		setTitle("Quiz do Hiperesp");
		setSize(480, 480);
		//setIconImage(ImageIO.read(new File("assets/logo.jpg")));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setTheme(Init.themeIndex==-1?UIManager.getSystemLookAndFeelClassName():getThemes()[Init.themeIndex].getClassName());
		setup();
		setVisible(true);
	}	
	public void resetContentPane() {
		JPanel container = new JPanel();
		container.setLayout(null);
		setContentPane(container);
	}
	
	public void setup() {
		setView(INICIO);
	}
	
	int questaoAtual, acertos;

	
	public void setView(int view) {
		resetContentPane();
		if(view==INICIO) inicioView();
		if(view==QUESTOES) questoesView();
		SwingUtilities.updateComponentTreeUI(this);
	}
	JButton inicioView_play;
	JButton themeButton;
	public void inicioView() {
		{
			questaoAtual = -1;
			acertos = 0;
			Font logoFont = new Font("Arial", Font.BOLD, 40);
			JLabel logo = new JLabel("QUIZ!");
			logo.setFont(logoFont);
			logo.setHorizontalAlignment(JLabel.CENTER);
			logo.setBounds(0, 80, getWidth(), 80);
			add(logo);
		}
		{
			inicioView_play = new JButton("Jogar");
			inicioView_play.setBounds(120, 300, 230, 80);
			inicioView_play.addActionListener(this);
			add(inicioView_play);
			
			/*themeButton = new JButton("Alterar Tema");
			themeButton.setBounds(230, 300, 120, 80);
			themeButton.addActionListener(this);
			add(themeButton);*/
		}
	}
	
	JLabel questoesView_image;
	JLabel questoesView_titulo;
	ButtonGroup questoesView_a;
	JRadioButton questoesView_a0, questoesView_a1, questoesView_a2, questoesView_a3, questoesView_a4;
	JButton questoesView_proximo;
	Font questoesView_tituloFont = new Font("Arial", Font.BOLD, 20);
	Font questoesView_alternativas = new Font("Arial", Font.PLAIN, 16);
	public void questoesView() {
		questoesView_titulo = new JLabel("1 - Que animal é esse?");
		questoesView_titulo.setBounds(20, 20, 420, 20);
		questoesView_titulo.setHorizontalAlignment(JLabel.CENTER);
		questoesView_titulo.setFont(questoesView_tituloFont);
		add(questoesView_titulo);
		questoesView_image = new JLabel();
		//questoesView_image.setBackground(new Color(255, 0, 0));
		//questoesView_image.setOpaque(true);
		questoesView_image.setBounds(160, 70, 160, 160);
		add(questoesView_image);

		questoesView_a = new ButtonGroup();
		questoesView_a0 = new JRadioButton("A - Alternativa 0");
		questoesView_a0.setBounds(20, 260, 320, 20);
		questoesView_a0.setFont(questoesView_alternativas);
		questoesView_a.add(questoesView_a0);
		add(questoesView_a0);
		questoesView_a1 = new JRadioButton("B - Alternativa 1");
		questoesView_a1.setBounds(20, 290, 320, 20);
		questoesView_a1.setFont(questoesView_alternativas);
		questoesView_a.add(questoesView_a1);
		add(questoesView_a1);
		questoesView_a2 = new JRadioButton("C - Alternativa 2");
		questoesView_a2.setBounds(20, 320, 320, 20);
		questoesView_a2.setFont(questoesView_alternativas);
		questoesView_a.add(questoesView_a2);
		add(questoesView_a2);
		questoesView_a3 = new JRadioButton("D - Alternativa 3");
		questoesView_a3.setBounds(20, 350, 320, 20);
		questoesView_a3.setFont(questoesView_alternativas);
		questoesView_a.add(questoesView_a3);
		add(questoesView_a3);
		questoesView_a4 = new JRadioButton("E - Alternativa 4");
		questoesView_a4.setBounds(20, 380, 320, 20);
		questoesView_a4.setFont(questoesView_alternativas);
		questoesView_a.add(questoesView_a4);
		add(questoesView_a4);
		
		questoesView_proximo = new JButton("Próximo");
		questoesView_proximo.setBounds(160, 410, 160, 30);
		questoesView_proximo.addActionListener(this);
		add(questoesView_proximo);
		proximaQuestao();
	}
	
	public boolean addPonto() {
		if(questoesView_a.getSelection()==null) {
			JOptionPane.showMessageDialog(this, "Selecione uma opção");
			return false;
		}
		if(((int)questoes[questaoAtual][2]==0&&questoesView_a0.isSelected())
		|| ((int)questoes[questaoAtual][2]==1&&questoesView_a1.isSelected())
		|| ((int)questoes[questaoAtual][2]==2&&questoesView_a2.isSelected())
		|| ((int)questoes[questaoAtual][2]==3&&questoesView_a3.isSelected())
		|| ((int)questoes[questaoAtual][2]==4&&questoesView_a4.isSelected())) {
			acertos++;
		}
		return true;
	}
	
	public void proximaQuestao() {
		questaoAtual++;
		questoesView_a.clearSelection();
		if(questaoAtual<questoes.length) {
			questoesView_titulo.setText((String)questoes[questaoAtual][0]);
			questoesView_image.setIcon(new ImageIcon(new ImageIcon("assets/questoes/"+(String)questoes[questaoAtual][1]).getImage().getScaledInstance(questoesView_image.getWidth(), questoesView_image.getHeight(), Image.SCALE_SMOOTH)));
			questoesView_a0.setText((String)alternativas[questaoAtual][0]);
			questoesView_a1.setText((String)alternativas[questaoAtual][1]);
			questoesView_a2.setText((String)alternativas[questaoAtual][2]);
			questoesView_a3.setText((String)alternativas[questaoAtual][3]);
			questoesView_a4.setText((String)alternativas[questaoAtual][4]);
		} else {
			JOptionPane.showMessageDialog(this, "Parabéns!\n"
					+ "Acertos: "+acertos+"\n"
					+ "Erros: "+(questoes.length-acertos)+"\n");
			setView(INICIO);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inicioView_play) {
			setView(QUESTOES);
		} else if(e.getSource()==questoesView_proximo) {
			if(addPonto())	proximaQuestao();
		} else if(e.getSource()==themeButton) {
			try {
				switchTheme();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
	public void setTheme(String theme) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(theme);
        SwingUtilities.updateComponentTreeUI(this);
	}
	
	public LookAndFeelInfo[] getThemes() {
		return UIManager.getInstalledLookAndFeels();
	}
	
	public String getTheme() {
		return UIManager.getLookAndFeel().getClass().getName();
	}
	
	public void switchTheme() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		LookAndFeelInfo[] themes = getThemes();
		if(++Init.themeIndex>themes.length-1) Init.themeIndex = 0;
		setTheme(themes[Init.themeIndex].getClassName());
		JOptionPane.showMessageDialog(this, "Tema alterado para "+themes[Init.themeIndex].getName()+".");
	}
	
}
