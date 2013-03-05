import java.util.Arrays;
import java.util.ArrayList;

public class YahtzeePlayer {

	String name;
	int[] rawDice, sortedDice, scoreList;
	boolean[] scoredCategories;
	static ArrayList<Integer> temp = new ArrayList<Integer>(5);
	int score;

	/* index 0 - aces
	 * index 1 = twos
	 * index 2 = threes
	 * index 3 = fours
	 * index 4 = fives
	 * index 5 = sixes
	 * index 6 = threeInRow
	 * index 7 = fourInRow
	 * index 8 = Full House
	 * index 9 = Small Straight
	 * index 10 = Large Straight
	 * index 11 = Yahtzee
	 * index 12 = Yahtzee Bonus
	 * index 13 = Chance
	 */

	public YahtzeePlayer() {
		name = null;
		rawDice = null;
		sortedDice = null;
		scoreList = null;
		scoredCategories = null;
		score = 0;
	}

	public YahtzeePlayer(String s){
		name = s;
		rawDice = new int[5];
		sortedDice = new int[5];
		scoreList = new int[14];
		scoredCategories = new boolean[14];
		score = 0;
	}

	public String toString(){

		String result = "";
		result += name;
		result += "\n";
		result += Arrays.toString(rawDice);
		result += "\n";
		result += score;

		return result;
	}

	public void roll(){

		for(int i = 0; i < 5; i++){
			rawDice[i] = (int)(Math.random() * 6) + 1;
		}

		System.out.println(name + "'s roll is " + Arrays.toString(rawDice));
	} //Tested working

	public void reroll(){

		String[] tempArray = Yahtzee.s.nextLine().split(" ", -1);

		for(int i = 0; i < tempArray.length; i++){

			try{
				temp.add(Integer.parseInt(tempArray[i]));
			}

			catch(NumberFormatException e){
				System.out.println("Don't enter Strings, you dummy!");
			}
		}

		for(int i = 0; i < temp.size(); i++){

			try{
				rawDice[ temp.get(i) - 1 ] = (int)(Math.random() * 6) + 1; // [Player's raw dice @ (diceargs @ index 0 -> diceargs.length)] = random dice
			}

			catch(ArrayIndexOutOfBoundsException e){
				System.out.println(temp.get(i) + " is not within 1-5: ignoring!");
			}
		}
		temp.clear(); // Clear ArrayList after use
	} //Working. Kinda well..

	public void checkSetYahtzee(){ //This method needs to CHECK for existence of Yahtzee, fill in a score, fill in the scored values, ask for joker,

		boolean bool = true;

		for(int i = 0; i < 4; i++){
			if(sortedDice[i] != sortedDice[i+1])
			bool = false;
		}//Checking for Yahtzee

		if(bool){ // If valid Yahtzee

			System.out.println("You got a Yahtzee!");

			if(scoredCategories[11] && scoreList[11] == 50){ // If the Yahtzee category is SCORED as 50,

				System.out.println("Yahtzee bonus! You get 100 points!");

				scoreList[12] += 100;  //ADD 100 to the Yahtzee Bonus category.
			}

			else if (!scoredCategories[11]){ // Else if Yahtzee category isn't SCORED

				System.out.println("Scoring the Yahtzee Category as 50!");

				scoredCategories[11] = true; //SET Yahtzee category to SCORED
				scoreList[11] = 50; //SET Yahtzee Score to 50
			}
		}

		else if (bool == false && scoredCategories[11] == false){ //otherwise if Yahtzee isn't valid && Yahtzee hasn't been scored yet

			System.out.println("Scoring the Yahtzee category as 0.");

			scoredCategories[11] = true;
			scoreList[11] = 0;
		}

		else{ //Else if Yahtzee isn't valid and Yahtzee category has already been scored in
			System.out.println("You didn't roll a yahtzee, and you already scored in the Yahtzee category. Are you crazy?");
		}

	} //Tested working

	public boolean checkFourInRow(){
		boolean bool1 = true, bool2 = true;

		for(int i = 0; i < 3; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool1 = false;
			}
		}

		for(int i = 1; i < 4; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool2 = false;
			}
		}

		int scoreMulti = sortedDice[2];
		return bool1 != bool2; //NOT functions as an XOR :D
	} //Tested working: recognizes Yahtzee as !FourInRow

	public void checkSetFourInRow(){
		boolean bool1 = true, bool2 = true;

		for(int i = 0; i < 3; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool1 = false;
			}
		}

		for(int i = 1; i < 4; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool2 = false;
			}
		}

		int scoreMulti = sortedDice[2];
		/*return bool1 != bool2;*/ //NOT functions as an XOR :D
	} //Tested working: recognizes Yahtzee as !FourInRow

	public boolean checkSetThreeInRow(){

		boolean bool1 = true, bool2 = true, bool3 = true;

		for(int i = 0; i < 2; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool1 = false;
			}
		}

		for(int i = 1; i < 3; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool2 = false;
			}
		}

		for(int i = 2; i < 4; i++){
			if(sortedDice[i] == sortedDice[i+1]){

			}
			else{
				bool3 = false;
			}
		}

		int scoreMulti = sortedDice[2];

		if(bool1 == true && bool2 == false && bool3 == false) return true;

		else if(bool1 == false && bool2 == true && bool3 == false) return true;

		else if(bool1 == false && bool2 == false && bool3 == true) return true;

		else return false;
	}//Working, exclusively true for threeInRow.

	public void setChance(){

	}
}