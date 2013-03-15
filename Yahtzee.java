import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Yahtzee {

	static YahtzeePlayer Player1 = new YahtzeePlayer("Alan");

	static String answer;

	public static Scanner s = new Scanner(System.in);

	public static String askAnswer(String query){

		String input;

		while(true){

			try{
				System.out.print(query);
				input = s.nextLine();
				return input;
			}

			catch(InputMismatchException e){
				System.out.println("\nHow did you get InputMismatchException, you dummy?\n");
				s.nextLine();
			}

			catch(Exception e){
				System.out.println("Something else went wrong...\n" + e);
			}
		}
	}

	public static int askInt(String query){

		int input;

		while(true){

			try{
				System.out.print(query);
				input = s.nextInt();
				s.nextLine();
				return input;
			}

			catch(InputMismatchException e){
				System.out.println("\nPlease enter a whole number.\n");
				s.nextLine();
			}

			catch(Exception e){
				System.out.println("Something else went wrong...\n" + e);
			}
		}
	}

	public static boolean askAgain(){

		String input;

		while(true){

			try{
				System.out.print("Repeat? ");
				input = s.nextLine();

				if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
					return true;
				}

				else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
					return false;
				}

				else{
					System.out.println("\nNo comprendo.\n");
				}
			}

			catch(InputMismatchException e){
				System.out.println("How did you get InputMismatchException? You failed!");
				s.nextLine();
			}

			catch(Exception e){
				System.out.println("Something else went wrong..." + e);
			}
		}
	}

	public static void testCode(){
		int players;

		players = askInt("How many players? ");

	}

	public static void main(String[] args) {

		//testCode();

		//Player1.roll(); // -> Player1.rawDice

		Player1.rawDice[0] = 6;
		Player1.rawDice[1] = 6;
		Player1.rawDice[2] = 6;
		Player1.rawDice[3] = 6;
		Player1.rawDice[4] = 6;

		Player1.sortedDice = Player1.rawDice.clone(); //Actual separate array, not just pointer
		Arrays.sort(Player1.sortedDice);

		System.out.println(Arrays.toString(Player1.rawDice));

		for(int i = 6; i <= 13; i++){

		Player1.scoredCategories[i] = true;

		}

		do{

			Player1.checkSetYahtzee();

			System.out.println(Player1.scoreList[YahtzeePlayer.parseNames(answer)]);
		}while(askAgain());
	}
}