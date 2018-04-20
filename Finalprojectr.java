import java.util.*;
import java.io.*;
public class Finalprojectr{
  public static void main (String[] args) throws FileNotFoundException{
    String[] x=new String[20000];
    createdata(x);
    drawframe();
    guessingprocess(x);
    System.out.println("Thanks for playing, have a wonderful day!");
  }



  public static void drawframe(){
    System.out.println("   +--+\n   |  |\n   |\n   |\n   |\n---+---");
  }

  public static void createdata(String[] x) throws FileNotFoundException{
    File data = new File("datasource.txt");
    Scanner reader =new Scanner(data);
    int z = 0;
    while(reader.hasNextLine())
    {
      x[z]=reader.nextLine();
      z++;
    }
  }


  public static String createword(String[] x){
    int value = (int)(Math.random()*30000);
    while (value > 19785){
      value = (int)(Math.random()*30000);
    }
    return x[value];
  }


  public static void guessingprocess(String[] x){
    boolean done = false;
    while (!done){
      String theword = createword(x);
      System.out.println("Let's play the game!");
      int lengths = theword.length();
      System.out.println("I have generated a word that has " + lengths + " letters.");
      System.out.println("You have six chances to get it wrong!");
      int chance = 1;
      int left = 6;
      String[] store = new String[lengths];
      String[] answer = new String[lengths];
      for (int y = 0; y < lengths; y++){
        answer[y] = " ";
      }
      checking(left, lengths, chance, store, answer, theword);
      System.out.println("Do you want to play again?");
      Scanner user = new Scanner(System.in);
      String again;
      again = user.nextLine();
      done = again.equals("no");
    }
  }

  public static void checking(int left, int lengths, int chance, String[] store, String[] answer, String theword){
    int counts = 0;
    List<String> his = new ArrayList<String>();
      loop:while (left != 0){
        int records = 0;
        String response = intro(left, chance);
        for (int digit = 0; digit < lengths; digit++){
          if (response.equals(theword.substring(digit,digit+1))){
              counts++;
              records++;
              answer[digit] = response;
            }
          }
          if (records != 0){
            System.out.println("right guess! continue");
          }else {
            left = wrongguess(left);
          }
        chance++;
        left = results(counts, left, answer, theword);
        history(response,his);
      if (left == 8){
        break loop;
      }
    }
  }


  public static String intro (int left, int chance){
    System.out.println("Please enter the letter in lowercase. You have " + left +" chances left.");
    System.out.printf(generateResponse()+"%n");
    Scanner yourguess = new Scanner(System.in);
    String response = yourguess.nextLine();
    return response;
  }

  public static String generateResponse(){
    int k;
    k=(int)(Math.random()*5);
    String[] abc = new String[5];
    abc[0]="What's your guess?";
    abc[1]="Tell us one letter";
    abc[2]="What's the letter in your mind?";
    abc[3]="Type in a correct letter, or the little man will die!";
    abc[4]="Any new letter may work here!";
    return abc[k];
  }


  public static void history (String response, List<String> his){
    his.add(response);
    System.out.print("You have already guessed: ");
    for (int x = 0; x < his.size(); x++){
      System.out.print(his.get(x)+ ", ");
    }
    System.out.println(" ");
  }



  public static int results (int counts, int left, String[] answer, String theword){
      if (counts == theword.length()){
      System.out.println(Arrays.asList(answer));
      System.out.println("Congradulations, you get the word and save a life!");
      left = 8;
    }
    System.out.println(Arrays.asList(answer));
    if (left == 0){
      System.out.println("Sorry you lose the game. Good luck next time!");
      System.out.println("The word is: " + theword);
    }
    return left;
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

}
