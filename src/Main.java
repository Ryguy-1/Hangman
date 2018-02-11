import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main implements KeyListener{
	
	static int lives = 10;
	static String word = "";
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel labelLives = new JLabel();
	JLabel labelWord = new JLabel();
	JLabel letters = new JLabel();
	static ArrayList<String> list = new ArrayList<String>();
	Font font = new Font(labelLives.getFont().getName(),labelLives.getFont().getStyle(), 50);
	Font font2 = new Font(labelWord.getFont().getName(),labelWord.getFont().getStyle(), 100);
	static String oldNum = "";
	static int rounds = 0;
	static String realWord = "";
	static String tempWord = "";
	static int counter = 0;
	static boolean win = false;
	static boolean lose = false;
	static String lettersGuessed = "";
public static void main(String[] args) {
	Main main = new Main();
	main.setup();
	oldNum = JOptionPane.showInputDialog("Rounds: ");
	rounds = Integer.parseInt(oldNum);
	for (int i = 0; i < rounds; i++) {
		try {
			Random rand = new Random();
			int randy = rand.nextInt(3000);
			BufferedReader br = new BufferedReader(new FileReader("/Users/league/Desktop/Hangman/src/dictionary.txt"));
			String line = br.readLine();
			int ctr = 0;
			
			while(line!=null) {
				
				if(ctr++ < randy) {
					line = br.readLine();
					
				}else {
					list.add(line);
					
					break;
				}
			}
			br.close();
		}catch(IOException e){
			
			
		}
			
	}
	realWord = list.get(0);
	//System.out.println(realWord);
	for (int i = 0; i < realWord.length(); i++) {
			word+="-";
			main.updateText();
	}
	
	
}




void setup() {
	frame.add(panel);
	frame.addKeyListener(this);
	frame.setVisible(true);
	panel.add(labelLives);
	panel.add(labelWord);
	panel.add(letters);
	letters.setFont(font);
	letters.setText("Letters Guessed: "+lettersGuessed);
	labelWord.setText(System.lineSeparator()+word);
	labelLives.setText("Lives: "+lives);
	labelLives.setFont(font);
	labelWord.setFont(font2);
	
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
void updateText() {
	labelWord.setText(System.lineSeparator()+word);
	labelLives.setText("Lives: "+lives);
	letters.setText("Letters Guessed: "+lettersGuessed);
	frame.pack();
	if(win == true) {
		labelLives.setVisible(false);
		letters.setVisible(false);
		labelWord.setText("!!YOU WIN!!");
		frame.pack();	
	}else if(lose == true) {
		labelLives.setText("Real Word: "+realWord);
		labelWord.setText("!!YOU LOSE!!");
		frame.pack();
	}
}
	
	

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
//	System.out.println(word);
	if(realWord.contains(""+e.getKeyChar())) {
		for (int i = 0; i < realWord.length(); i++) {
			if(realWord.charAt(i)==e.getKeyChar()) {
				tempWord+=e.getKeyChar();
			}else {
				tempWord+=word.charAt(i);
			}
		}
		word=tempWord;
		tempWord = "";
	}else {
		lives-=1;
		lettersGuessed += e.getKeyChar();
		lettersGuessed += " ";
	}
	//System.out.println();
	//System.out.println("word: "+word);
	//System.out.println("realWord: "+realWord);

	 if(lives==0) {
		lose = true;
	}else if(word.equals(realWord)) {
		//System.out.println("Next");
		lettersGuessed="";
		lives+=1;
		counter+=1;
		if(list.size()>counter) {
			realWord=list.get(counter);
		}else {
			win=true;
		}
		word = "";
		for (int i = 0; i < realWord.length(); i++) {
			
			word+="-";
		}
		tempWord = "";
	}
	
	
	updateText();
	//System.out.println("word: "+word);
	//System.out.println("realWord: "+realWord);
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
