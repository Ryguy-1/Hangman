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
	String word = "";
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel labelLives = new JLabel();
	JLabel labelWord = new JLabel();
	static ArrayList<String> list = new ArrayList<String>();
	static Stack<String> stack = new Stack<String>();
	static ArrayList<Character> letters;
	Font font = new Font(labelLives.getFont().getName(),labelLives.getFont().getStyle(), 50);
	Font font2 = new Font(labelWord.getFont().getName(),labelWord.getFont().getStyle(), 100);
	static String oldNum = "";
	static int rounds = 0;
public static void main(String[] args) {
	Main main = new Main();
	main.setup();
	oldNum = JOptionPane.showInputDialog("Rounds: ");
	rounds = Integer.parseInt(oldNum);
	for (int i = 0; i < rounds; i++) {
		try {
			Random rand = new Random();
			int randy = rand.nextInt(3000);
			BufferedReader br = new BufferedReader(new FileReader("/Hangman/src/dictionary.txt"));
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
	
	for (int i = 0; i < list.size(); i++) {
		stack.push(list.get(i));
	}
	
	


}

void setup() {
	word = "HELLO";
	frame.add(panel);
	frame.addKeyListener(this);
	frame.setVisible(true);
	panel.add(labelLives);
	panel.add(labelWord);
	labelWord.setText(System.lineSeparator()+word);
	labelLives.setText("Lives: "+lives);
	labelLives.setFont(font);
	labelWord.setFont(font2);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	letters = new ArrayList<Character>();
	String tempWord = stack.pop();
	for(int i = 0;i<tempWord.length();i++) {
		if(e.getKeyChar()==tempWord.charAt(i)) {
			letters.add(tempWord.charAt(i));
			
		}
	}
	for (int i = 0; i < tempWord.length(); i++) {
		word+="_";
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
