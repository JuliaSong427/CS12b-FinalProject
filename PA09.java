import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
* This demo creates a telephone keyboard of buttons.
*/
public class PA09 extends JPanel{

  Map<Integer,String> vocabulary = new HashMap<Integer,String>();

	public PA09(){
    super();
		JPanel content = this;
		content.setLayout(new BorderLayout());

    String word = createword(vocabulary);
    JLabel title = new JLabel("<html><h1>Hangled Man</h1></html>");
    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    JButton start = new JButton("start");
    JTextArea intro = new JTextArea();
    intro.setText("At the beginning of this game, there is a randomly selected word.\n"
      +"The user is able to guess this word letter by letter,\n"
      +"and the user has 5 opportunities to have a wrong guess.\n"
      +"After the man in the middle of the window is drawn completely,the user loses the game.\n"
      +"The user can use Start Button to start the game, and the End Button to exit the game.");
    intro.setEditable(false);
    JTextArea center = new JTextArea();
    JButton end = new JButton("end");
    JPanel wordguess = new JPanel();
    wordguess.setLayout(new GridLayout(1,word.length()+1));
    JPanel ending = new JPanel();
    ending.setLayout(new GridLayout(2,0));

    JPanel ending1 = new JPanel();
    ending1.setLayout(new GridLayout(2,0));

    JTextField[] guess = new JTextField[word.length()];
    wordguess.add(new JLabel("correct"));
    for(int i=0; i<word.length();i++){
      guess[i] = new JTextField();
      wordguess.add(guess[i]);
    }
    JPanel wrongletter = new JPanel();
    wrongletter.setLayout(new FlowLayout());
    JTextArea wrong = new JTextArea(2,50);
    JLabel wrongguess = new JLabel("Wrong guess:");
    wrongletter.add(wrongguess);
    wrongletter.add(wrong);

    ending1.add(wordguess);
    ending1.add(wrongletter);
    ending.add(ending1);
    ending.add(intro);

    content.add(title,BorderLayout.PAGE_START);
    content.add(start,BorderLayout.LINE_START);
    content.add(center,BorderLayout.CENTER);
    content.add(end,BorderLayout.LINE_END);
    content.add(ending,BorderLayout.PAGE_END);
	}


	private static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("Note");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //Create and set up the content pane.
      JComponent newContentPane = new PA09();
      newContentPane.setOpaque(true); //content panes must be opaque
      frame.setContentPane(newContentPane);
			frame.setLocation(100,70);
			frame.setSize(700,500);
      //Display the window.
      //frame.pack();
      frame.setVisible(true);
  }

  public static void main(String[] args) throws NullPointerException {
      //Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              createAndShowGUI();
          }
      });


  }

  public static void createdata(Map<Integer, String>vocabulary) throws FileNotFoundException{
    File data = new File("datasource.txt");
    Scanner reader =new Scanner(data);
    String x;
    for (int i=1; i<=19785; i++){
      x=reader.nextLine();
      vocabulary.put(i, x);
    }
  }


  public static String createword(Map<Integer, String>vocabulary) throws NullPointerException{
    int value = (int)(Math.random()*30000);
    while (value > 19785){
      value = (int)(Math.random()*30000);
    }
    return vocabulary.get(value);
  }
}
