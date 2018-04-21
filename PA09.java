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
  Map<String,Integer> repeat = new HashMap<String,Integer>();
  JTextArea intro,intro1;
  String response;
  int times;
  int left = 6;
  int lengths;
  int records = 0;
  int counts = 0;
  JTextField[] guess;
  JTextArea check;
  ArrayList<String> his = new ArrayList<String>();
  String[] store = new String[lengths];
  String[] answer = new String[lengths];

	public PA09(){
    super();
    JPanel content = this;
    content.setLayout(new BorderLayout());

    getFile("datasource.txt");
    String word = getWord();
    lengths = word.length();
    JLabel title = new JLabel("<html><h1>Hangled Man</h1></html>");
    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    JButton start = new JButton("Start");
    intro = new JTextArea();
    intro.setText("At the beginning of this game, there is a randomly selected word."
      +" You are able to guess this word letter by letter.\n"
      +"You have 6 opportunities to have a wrong guess."
      +" After the man in the middle of the window is drawn completely, you lose.\n"
      +"Press “Start” Button to start the game, and the “End” Button to exit the game.");
    intro.setEditable(false);

    intro1 = new JTextArea();
    intro1.setEditable(false);

    JPanel ending2 = new JPanel();
    ending2.setLayout(new GridLayout(2,0));

    JTextArea center = new JTextArea();
    center.setEditable(false);
    JButton end = new JButton("End");
    JPanel wordguess = new JPanel();
    wordguess.setLayout(new GridLayout(1,word.length()+1));
    JPanel ending = new JPanel();
    ending.setLayout(new GridLayout(2,0));

    JPanel ending1 = new JPanel();
    ending1.setLayout(new GridLayout(2,0));

    JPanel checkletter = new JPanel();
    checkletter.setLayout(new FlowLayout());
    //String[] check1= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","e"};
    check = new JTextArea(2,5);
    JLabel checkword = new JLabel("guess");
    JButton checkB = new JButton("check");
    checkletter.add(checkword);
    checkletter.add(check);
    checkletter.add(checkB);


    guess = new JTextField[word.length()];
    wordguess.add(new JLabel("Correct word:"));
    for(int i=0; i<word.length();i++){
      guess[i] = new JTextField();
      wordguess.add(guess[i]);
      guess[i].setEditable(false);
    }

    ending1.add(checkletter);
    ending1.add(wordguess);
    ending2.add(intro);
    ending2.add(intro1);
    ending.add(ending1);
    ending.add(ending2);

    content.add(title,BorderLayout.PAGE_START);
    content.add(start,BorderLayout.LINE_START);
    content.add(center,BorderLayout.CENTER);
    content.add(end,BorderLayout.LINE_END);
    content.add(ending,BorderLayout.PAGE_END);
    System.out.print(word);

    start.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        guessGame(word);
      }
    });

    checkB.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String responseInfo = check.getText();
        if(responseInfo.length()!=1){
          intro1.setText("Please enter one letter.");
        }
        else if(repeat.containsKey(responseInfo)){
          intro1.setText("You have entered that letter. Please try again.");
        }
        else{
          response = responseInfo;
          repeat.put(response,times);
          times++;
          checking(word);
        }
        check.setText("");
      }
    });
	}

  public void guessGame(String word){
    String inst = "I have generated a word that has " + lengths + " letters.\n"
     + "You will have six chances to get it wrong.";
    intro.setText(inst);
  }

  public void checking(String word){
    for (int digit = 0; digit < lengths; digit++){
      if (response.equals(word.substring(digit,digit+1))){
          counts++;
          records++;
          guess[digit].setText(response);
        }
      }
      if (records != 0){
        intro1.setText("Right guess! Continue");
        records=0;
      }
      else {
        left --;
        String pop = "Wrong guess! You have " + left +" chances left.";
        intro1.setText(pop);
      }
    history(response);
    checkresults(counts, left, word);
  //}
  }


  public void history (String response){
    his.add(response);
    String currentguess= "You have already guessed: ";
    for (int x = 0; x < his.size(); x++){
      currentguess+=his.get(x);
      currentguess+=", ";
    }
    intro.setText(currentguess);
  }


  public void checkresults (int counts, int left, String word){
    if (counts == word.length()){
      intro1.setText("Congratulations, you get the word and save a life!");
      check.setEditable(false);
    }
    if (left == 0){
      String sentence = "Sorry you lose the game. Good luck next time!\n"
        +"The word is: " + word;
      intro1.setText(sentence);
      check.setEditable(false);
    }
  }


  public static int wrongguess(int left){
      if (left == 6){
        System.out.println("   +--+\n   |  |\n   |  O\n   |\n   |\n   |\n---+---");
      }else if (left == 5){
        System.out.println("   +--+\n   |  |\n   |  O\n   |  |\n   |\n   |\n---+---");
      }else if (left == 4){
        System.out.println("   +--+\n   |  |\n   |  O\n   | /|\n   |\n   |\n---+---");
      }else if (left == 3){
        System.out.println("   +--+\n   |  |\n   |  O\n   | /|\\\n   |\n   |\n---+---");
      }else if (left == 2){
        System.out.println("   +--+\n   |  |\n   |  O\n   | /|\\\n   | / \n   |\n---+---");
      }else if (left == 1){
        System.out.println("   +--+\n   |  |\n   |  O\n   | /|\\\n   | / \\\n   |\n---+---");
      }
      left--;
      System.out.println("Wrong guess !!!");
      return left;
  }

  public void getFile(String filename){
    File play;
    String lastWord = "";
    String word;
    int i = 1;
    try{
      play = new File(filename);
      Scanner scanner = new Scanner(play);
      while (scanner.hasNext()){
        word = scanner.next();
        vocabulary.put(i,word);
        i ++;
      }
    } catch(Exception e) {
      System.out.println("Exception:  "+e);
    }
  }

  public String getWord(){
    int value = (int) (19785 * Math.random());
    String word = vocabulary.get(value);
    return word;
  }


	private static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("HangledMan");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //Create and set up the content pane.
      JComponent newContentPane = new PA09();
      newContentPane.setOpaque(true); //content panes must be opaque
      frame.setContentPane(newContentPane);
			frame.setLocation(100,70);
			frame.setSize(1000,500);
      //Display the window.
      //frame.pack();
      frame.setVisible(true);
  }

  public static void main(String[] args) {
      //Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              createAndShowGUI();
          }
      });
  }
}
