import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Yahtzee {

	static YahtzeePlayer Player1 = new YahtzeePlayer("Alan");

	public static Scanner s = new Scanner(System.in);

	private static String askAnswer(String query){

		String input;

		while(true){

			try{
				System.out.println(query);
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

	private static int askInt(String query){

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

	private static boolean askAgain(){

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

		Player1.roll(); // -> Player1.rawDice

		Player1.sortedDice = Player1.rawDice.clone(); //Actual separate array, not just pointer
		Arrays.sort(Player1.sortedDice);

		System.out.println(Arrays.toString(Player1.rawDice));
		while(true){
		Player1.reroll();
		System.out.println(Arrays.toString(Player1.rawDice));
		}
	}
}