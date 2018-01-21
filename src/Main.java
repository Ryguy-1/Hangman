import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main implements KeyListener{
	
	int lives = 10;
	String word = "";
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel labelLives = new JLabel();
	JLabel labelWord = new JLabel();
	static ArrayList<String> list = new ArrayList();
	Font font = new Font(labelLives.getFont().getName(),labelLives.getFont().getStyle(), 100);
public static void main(String[] args) {
	Main main = new Main();
	main.setup();
	String oldNum = JOptionPane.showInputDialog("Rounds: ");
	int num = Integer.parseInt(oldNum);
	for (int i = 0; i < num; i++) {
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
		}catch(IOException e){
			
			
		}
			
			
		
	}
}

void setup() {
	
	frame.add(panel);
	frame.addKeyListener(this);
	frame.setVisible(true);
	panel.add(labelLives);
	panel.add(labelWord);
	labelWord.setText(word);
	labelLives.setText(""+lives);
	labelLives.setFont(font);
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
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
