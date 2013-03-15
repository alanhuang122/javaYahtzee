import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.*;

public class YahtzeePlayer {

	String name;
	int[] rawDice, sortedDice, scoreList;
	boolean[] scoredCategories;
	static ArrayList<Integer> temp = new ArrayList<Integer>(5); //Used for reroll and varargs
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
	 * index 12 = Chance
	 * index 13 = Yahtzee Bonus
	 *
	 * Check if scored already in main
	 * Detections all working; refine
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

	public String lowerCategoryNames(){
		String names = "";
		names += "Three of a Kind\n";
		names += "Four of a Kind\n";
		names += "Full House\n";
		names += "Small Straight\n";
		names += "Large Straight\n";
		names += "Chance";
		return names;
	}

	public int addArray(int[] a){
		int sum = 0;

		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum;
	} //Adds up array values

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

	public void checkSetOnes(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 1){
				total++;
			}
		}

		if(!scoredCategories[0]){
			scoredCategories[0] = true;

			scoreList[0] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetTwos(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 2){
				total += 2;
			}
		}
		if(!scoredCategories[1]){
			scoredCategories[1] = true;

			scoreList[1] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetThrees(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 3){
				total += 3;
			}
		}
		if(!scoredCategories[2]){
			scoredCategories[2] = true;

			scoreList[2] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetFours(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 4){
				total += 4;
			}
		}
		if(!scoredCategories[3]){
			scoredCategories[3] = true;

			scoreList[3] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetFives(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 5){
				total += 5;
			}
		}
		if(!scoredCategories[4]){

			scoredCategories[4] = true;

			scoreList[4] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetSixes(){
		int total = 0;

		for(int i = 0; i < 5; i++){
			if(rawDice[i] == 6){
				total += 6;
			}
		}
		if(!scoredCategories[5]){

			scoredCategories[5] = true;

			scoreList[5] = total;
		}
		else System.out.println("Already Scored");
	} //Working

	public void checkSetThreeInRow(){

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

		if(!scoredCategories[6]){

			scoredCategories[6] = true;

			if(bool1 == true && bool2 == false && bool3 == false) scoreList[6] = addArray(rawDice);

			else if(bool1 == false && bool2 == true && bool3 == false) scoreList[6] = addArray(rawDice);

			else if(bool1 == false && bool2 == false && bool3 == true) scoreList[6] = addArray(rawDice);

			else scoreList[6] = 0;
		}

	}//Working, exclusively true for threeInRow.

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

		if(!scoredCategories[7]){

			scoredCategories[7] = true;

			if(bool1 || bool2){
				scoreList[7] = addArray(rawDice);
			}

			else {
				scoreList[7] = 0;
			}
		}

	} //Tested working: recognizes Yahtzee as !FourInRow

	public int[] createDiceMap(){

		int[] diceMap = new int[6];

		for(int i = 1; i < 7; i++){

			for(int j = 0; j < 5; j++){

				if(sortedDice[j] == i){ //IF number @ index (loop index) == (number to check) (loop index)
					diceMap[i-1]++; //Dicemap @ index (number to check) - 1 (loop index)
				}

			} //Cycles through array ONCE

		}//Cycles through 1-6

		return diceMap;
	} //Creates map of dice for regex

	public String regexRawArray(String s){
		String regex = "";

		Pattern pattern = Pattern.compile("[^ \\[ 0 , \\] ]");

		Matcher matcher = pattern.matcher(Arrays.toString(createDiceMap()));

		while(matcher.find()){
			regex += matcher.group();
		}

		return regex;
	} //Strips characters([ ],0)

	public void checkSetFullHouse(){
		String regex = regexRawArray(Arrays.toString(rawDice));

		boolean matched = false;

		matched = (regex.equals("23") || regex.equals("32"));

		if( matched && !scoredCategories[8]){
			scoredCategories[8] = true;
			scoreList[8] = 25;
		}

		else if(!matched && !scoredCategories[8]){
			scoredCategories[8] = true;
			scoreList[8] = 0;
		}
	} //Working

	public void checkSetSmallStraight(){
		String regex = regexRawArray(Arrays.toString(rawDice));

		System.out.println(regex);

		boolean matched = false;

		matched = (regex.equals("2111") || regex.equals("1211") || regex.equals("1121") || regex.equals("1112") || regex.equals("11111"));

		if(matched && !scoredCategories[9]){
			scoredCategories[9] = true;
			scoreList[9] = 30;
		}

		else if(!matched && !scoredCategories[9]){
			scoredCategories[9] = true;
			scoreList[9] = 0;
		}
	} //Working

	public void checkSetLargeStraight(){
		String regex = regexRawArray(Arrays.toString(rawDice));

		System.out.println(regex);

		boolean matched = false;

		matched = regex.equals("11111");

		if(matched && !scoredCategories[10]){
			scoredCategories[10] = true;
			scoreList[10] = 40;
		}

		else if(!matched && !scoredCategories[10]){
			scoredCategories[10] = true;
			scoreList[10] = 0;
		}
	} //Working

	public void checkSetYahtzee(){ //This method needs to CHECK for existence of Yahtzee, fill in a score, fill in the scored values, ask for joker,

		boolean yahtzee = true;

		for(int i = 0; i < 4; i++){
			if(sortedDice[i] != sortedDice[i+1]) yahtzee = false;
		}//Checking for Yahtzee

		if(yahtzee){ // If valid Yahtzee

			System.out.println("You got a Yahtzee!");

			//Start normal Yahzee scoring ladder

			if(scoredCategories[11] && scoreList[11] == 50){ // If the Yahtzee category is SCORED as 50,

				System.out.println("Yahtzee bonus! You get 100 points!");

				scoreList[13] += 100;  //ADD 100 to the Yahtzee Bonus category.
			}

			else if (!scoredCategories[11]){ // Else if Yahtzee category isn't SCORED

				System.out.println("Scoring the Yahtzee Category as 50!");

				scoredCategories[11] = true; //SET Yahtzee category to SCORED
				scoreList[11] = 50; //SET Yahtzee Score to 50
			}

			//End normal Yahzee scoring ladder

			//Then, check for the scoring of the yahtzee number category and that all lowers have not been scored

			if(scoredCategories[sortedDice[0] - 1] && !allLowerScored()){
				int choiceIndex = 0;
				boolean doAgain = true;

				do{
					System.out.println("You got a Yahtzee, and you've already scored in the " + sortedDice[0] + " category. \nPick another section to score in.");
					System.out.println(lowerCategoryNames());
					choiceIndex = parseNames(Yahtzee.askAnswer(""));
					System.out.println(choiceIndex);

						switch(choiceIndex){
							case 6: scoredCategories[6] = true;
									scoreList[6] = addArray(sortedDice);
									doAgain = false;
									break;

							case 7: scoredCategories[7] = true;
									scoreList[7] = addArray(sortedDice);
									doAgain = false;
									break;

							case 8: scoredCategories[8] = true;
									scoreList[8] = 25;
									doAgain = false;
									break;

							case 9: scoredCategories[9] = true;
									scoreList[9] = 30;
									doAgain = false;
									break;

							case 10:scoredCategories[10] = true;
									scoreList[10] = 40;
									doAgain = false;
									break;

							case 12:scoredCategories[12] = true;
									scoreList[12] = addArray(sortedDice);
									doAgain = false;
									break;

							default:System.out.println("Number out of range.");
									doAgain = true;
									break;
						}
				}while(doAgain);
			}

			else if(scoredCategories[sortedDice[0] - 1] && allLowerScored()){
				System.out.println("You got a Yahtzee, you've already scored in the " + sortedDice[0] + " category, but all the Lower Score categories are filled.");
				System.out.println("You must score a 0 in any upper score category. Pick a category to score as 0.");
			}

			else{
				System.out.println("Scoring the " + sortedDice[0] + " category as " + addArray(sortedDice));
				scoreList[sortedDice[0] - 1] = addArray(sortedDice);
				System.out.println("Score = " + scoreList[sortedDice[0] - 1]);
			}
		}

		else if (yahtzee == false && scoredCategories[11] == false){ //otherwise if Yahtzee isn't valid && Yahtzee hasn't been scored yet

			System.out.println("Scoring the Yahtzee category as 0.");

			scoredCategories[11] = true;
			scoreList[11] = 0;
		}

		else{ //Else if Yahtzee isn't valid and Yahtzee category has already been scored in //CHECK THIS IN MAIN
			System.out.println("You didn't roll a yahtzee, and you already scored in the Yahtzee category. Are you crazy?");
		}
	} //Tested working, TODO

	public void setChance(){
		if(!scoredCategories[12]){

			scoredCategories[12] = true;
			scoreList[12] = addArray(rawDice);
		}
	} //Working

	public static int parseNames(String t){
		Yahtzee.answer = t;

		while(true){

			switch(t){

				case "Ones": return 0;

				case "Twos": return 1;

				case "Threes": return 2;

				case "Fours": return 3;

				case "Fives": return 4;

				case "Sixes": return 5;

				case "Three of a Kind": return 6;

				case "Four of a Kind": return 7;

				case "Full House": return 8;

				case "Small Straight": return 9;

				case "Large Straight": return 10;

				case "Yahtzee": return 11;

				case "Chance": return 12;

				default:System.out.println("No match found; try again.");
						t = Yahtzee.s.nextLine();
						break;
			}
		}
	}

	public boolean allLowerScored(){
		return scoredCategories[6] && scoredCategories[7] && scoredCategories[8] && scoredCategories[9] && scoredCategories[10] && scoredCategories[12];
	}
}