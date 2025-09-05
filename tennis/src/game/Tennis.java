package game;


/**
 * Plays a match of tennis, winPoints() scores points, then games, then sets, then match won.
 * winGames() scores games, then sets, then if match won.
 * winSets() scores sets, then determine match won. 
 * @author Christian Salazar
 */
public class Tennis {
	private String playerAName;
	private String playerBName;
	private String playerAScore;
	private String playerBScore;
	private String playerACall; //To temporarily hold callOut Strings associated with the points
	private String playerBCall;
	private String callOut;
	private boolean isBestOfFive;
	private boolean isPlayingTiebreaks;
	private boolean isGrandSlam;
	private boolean playerAServing;
	private int playerAGames;
	private int playerASets;
	private int playerAPoints;
	private int playerBGames;
	private int playerBSets;
	private int playerBPoints;

	/**
	 * The Constructor for the Tennis Class. Captures both players names, and also
	 * determines the type of tennis match we will be playing. Think of it like
	 * GameModes. isBestOfFive is a boolean which, if true, means we are playing a
	 * best-of-five sets match. if false, we are playing a best of three sets match.
	 * @param playerAName
	 * @param playerBName
	 * @param isBestOfFive
	 * @param isPlayingTiebreaks
	 * @param isGrandSlam
	 */
	public Tennis(String playerAName, String playerBName, boolean isBestOfFive, boolean isPlayingTiebreaks, boolean isGrandSlam) {
		this.playerAName = playerAName;
		this.playerBName = playerBName;
		this.isBestOfFive = isBestOfFive;
		this.isPlayingTiebreaks = isPlayingTiebreaks;
		this.isGrandSlam = isGrandSlam;

		playerAGames = 0;
		playerASets = 0;
		playerBGames = 0;
		playerBSets = 0;
		playerAPoints = 0;
		playerBPoints = 0;
		
		playerAScore = " 0";
		playerBScore = " 0";
		
		playerACall = "Love";
		playerBCall = "Love";

		playerAServing = true; // Make sure that PlayerA always serves first at the beginning of match. 
	}
	/**
	 * winPoint() will determine points won by either player A and player B. 
	 * enough points will then determine if player A or player B has been won a game.
	 * When enough games are won, will then determine if player A or player B wins the Set.
	 * When enough sets are won, determines who wins the match. Assigns callOuts as well as if Tiebreak is being played.
	 * @param playerAWins
	 * @includes winGame() 
	 */
	public void winPoint(boolean playerAWins) {
		// Player A
		if (playerAWins) { //Player A wins point.

			playerAPoints += 1; // Increase Player A points by 1
			// Assign Score and Announcer variables (Player A) if not in a tie break.
			if (!isPlayingTiebreaks) {
				if (playerAPoints == 0) {
					playerAScore = " 0";
					playerACall = "Love";
				} else if (playerAPoints == 1) {
					playerAScore = "15";
					playerACall = "Fifteen";
				} else if (playerAPoints == 2) {
					playerAScore = "30";
					playerACall = "Thirty";
				} else if (playerAPoints == 3) {
					playerAScore = "40";
					playerACall = "Forty";
				}
			//If in a tie break, scoring system refers to points/numbers. Player A
			} else {
				
				if (playerAPoints < 10) {
					playerAScore = " " + playerAPoints;
				} else if (playerAPoints >= 10) {
					playerAScore = "" + playerAPoints;
				}
				
				if (playerAPoints > playerBPoints) {
					callOut = playerAPoints + "-" + playerBPoints + " " + playerAName;
				} else if (playerAPoints == playerBPoints) {
					callOut = playerAPoints + "-All";
				} else {
					callOut = playerBPoints + "-" + playerAPoints + " " + playerBName;
				}
			}
			// Player B
		} else { // !playerAWins

			playerBPoints += 1; // Increase Player B Points by 1
			// Assign Score and Announcer variables (Player B) if not in tie break.
			if (!isPlayingTiebreaks) {
				if (playerBPoints == 0) {
					playerBScore = " 0";
					playerBCall = "Love";
				} else if (playerBPoints == 1) {
					playerBScore = "15";
					playerBCall = "Fifteen";
				} else if (playerBPoints == 2) {
					playerBScore = "30";
					playerBCall = "Thirty";
				} else if (playerBPoints == 3) {
					playerBScore = "40";
					playerBCall = "Forty";
				}
			//If in a tie break, scoring system refers to points/numbers. Player B
			} else {
				
				if (playerBPoints < 10) {
					playerBScore = " " + playerBPoints;
				} else if (playerBPoints >= 10) {
					playerBScore = "" + playerBPoints;
				}
				
				if (playerAPoints > playerBPoints) {
					callOut = playerAPoints + "-" + playerBPoints + " " + playerAName; 
				} else if (playerAPoints == playerBPoints) {
					callOut = playerAPoints + "-All";
				} else {
					callOut = playerBPoints + "-" + playerAPoints + " " + playerBName;
				}
			}
		}
		//Sets the proper order of callOuts. Whoever serves is ordered first.
		if (!isPlayingTiebreaks) {
			if (playerAServing) {
				callOut = playerACall + "-" + playerBCall;
			} else { // !playerAServing
				callOut = playerBCall + "-" + playerACall;
			}
			// Announces "scoreX-All for tied scores
			if (playerAPoints == playerBPoints) {
				callOut = playerACall + "-All";
				if (playerAPoints == 3 && playerBPoints == 3) { // If "40-40", Its a Deuce.
					callOut = "Deuce";
				}
			}
		}
		//Win condition updates Games, then Sets for Player A
		if (!isPlayingTiebreaks) {
			if (playerAPoints >= 4) {
				winGame(true); // Already defined for Games -> Sets.
				playerAScore = " 0";
				playerBScore = " 0";
				playerACall = "Love";
				playerBCall = "Love";
				// Win condition updates Games, then Sets for Player B
			} else if (playerBPoints >= 4) {
				winGame(false);
				playerBScore = " 0";
				playerAScore = " 0";
				playerACall = "Love";
				playerBCall = "Love";
			}
		} else {
			if (playerAPoints >= 7 && (playerAPoints - playerBPoints) >= 2) {
				winGame(true);
				playerAScore = " 0";
				playerBScore = " 0";
				playerACall = "Love";
				playerBCall = "Love";
			} else if (playerBPoints >= 7 && (playerBPoints - playerAPoints) >= 2) {
				winGame(false);
				playerBScore = " 0";
				playerAScore = " 0";
				playerACall = "Love";
				playerBCall = "Love";
			}
		}
	}
	
	/*
	 * Calling it with a true argument means playerA won a game, while a false
	 * argument means playerB won a game. This method only tracks the numbers of
	 * games won, the number of sets won and the conclusion of the match. If a game
	 * is won, the method should determine whether that means a set was won, and if
	 * so, whether the match was won.
	 */
	public void winGame(boolean playerAWins) {
		// Player A
		if (playerAWins) {
			
			playerAGames += 1; //Player A scores a game
			playerAPoints = 0;
			playerBPoints = 0;
			
			callOut = "Game: " + playerAName;
			if (isGrandSlam && isPlayingTiebreaks && playerAGames >= 6 && playerBGames >= 6) { //Gettin messy...
				if ((playerAPoints >= 10 && (playerAPoints - playerBPoints) >= 2) || playerAGames >= 7) {
					winSet(true);
					
				}
			} else if (isPlayingTiebreaks && playerAGames >= 6 && playerBGames >= 6) {
				if ((playerAPoints >= 7 && (playerAPoints - playerBPoints) >= 2) || playerAGames >= 7) {
					winSet(true);
					
				}
			} else if (playerAGames >= 6 && playerAGames - playerBGames >= 2) {
				winSet(true);
				
			}
			// Player B
		} else if (!playerAWins) {
			
			playerBGames += 1; //Player B scores a game
			playerAPoints = 0;
			playerBPoints = 0;
			
			callOut = "Game: " + playerBName;
			if (isGrandSlam && isPlayingTiebreaks && playerBGames >= 6 && playerAGames >= 6) {
				if ((playerBPoints >= 10 && (playerBPoints - playerAPoints) >= 2) || playerBGames >= 7) {
					winSet(false);
					
				}
			} else if (isPlayingTiebreaks && playerBGames >= 6 && playerAGames >= 6) {
				if ((playerBPoints >= 7 && (playerBPoints - playerAPoints) >= 2) || playerBGames >= 7) {
					winSet(false);
					
				}
			} else if (playerBGames >= 6 && playerBGames - playerAGames >= 2) {
				winSet(false);
				
			}
		}
		playerAServing = !playerAServing; // Flips whoever serves when winGame() called.
	}
	
	/**
	 * a true argument means playerA won a set, and a false argument means playerB
	 * won a set. This just counts the sets, and determines who wins the match by
	 * comparing the number of sets won. In this case you determine the sequence of
	 * calls to make by pretending someone is only telling you who won each set, but
	 * not telling you who won each game or point.
	 * 
	 * @param playerAWins
	 */
	public void winSet(boolean playerAWins) {
		if (playerAWins) {
			playerASets += 1;
			playerAGames = 0;
			playerBGames = 0;
			playerAPoints = 0;
			playerBPoints = 0;
			callOut = "Game and Set: " + playerAName;
		} else if (!playerAWins) {
			playerBSets += 1;
			playerAGames = 0;
			playerBGames = 0;
			playerAPoints = 0;
			playerBPoints = 0;
			callOut = "Game and Set: " + playerBName;
		}

		if (isBestOfFive) {
			if (playerASets >= 3) {
				callOut = "Game, Set and Match: " + playerAName;
			} else if (playerBSets >= 3) {
				callOut = "Game, Set and Match: " + playerBName;
			}

		} else if (!isBestOfFive) {
			if(playerASets >= 2) {
				callOut = "Game, Set and Match: " + playerAName;
			} else if (playerBSets >= 2) {
				callOut = "Game, Set and Match: " + playerBName;
			}
		}
	}
	
	/**
	 * Returns true if PlayerA is serving, false if PlayerB serves.
	 * Make sure playerA always serves first in any match
	 * @return
	 */
	public boolean getPlayerAServing() {

		return playerAServing;
	}

	/**
	 * Returns PlayerA's name input in constructor call.
	 * 
	 * @return
	 */
	public String getPlayerAName() {
		return playerAName;
	}

	/**
	 * Returns int value of PlayerA's sets for a match. One component for total
	 * score.
	 * 
	 * @return
	 */
	public int getPlayerASets() {

		return playerASets;
	}

	/**
	 * returns int value of PlayerA's Games played. One component for total score.
	 * 
	 * @return
	 */
	public int getPlayerAGames() {

		return playerAGames;
	}

	/**
	 * Returns PlayerA's points in an up to 2 digit format: 0, 15, 30, 40.
	 * 
	 * @return
	 */
	public String getPlayerAScore() {
		
		return playerAScore;
	}

	/**
	 * Returns PlayerB's name input in Constructor call.
	 * 
	 * @return
	 */
	public String getPlayerBName() {

		return playerBName;
	}

	/**
	 * Returns int value of PlayerB's games played. One component for total score.
	 * 
	 * @return
	 */
	public int getPlayerBGames() {

		return playerBGames;
	}

	/**
	 * Returns int value of PlayerB's Sets played. One component for total score.
	 * 
	 * @return
	 */
	public int getPlayerBSets() {

		return playerBSets;
	}

	/**
	 * Returns int value of PlayerB's points in an up to 2 digit format: 0, 15, 30,
	 * 40
	 * 
	 * @return
	 */
	public String getPlayerBScore() {
		
		return playerBScore;
	}

	/**
	 * Prints out Announcer message.
	 * 
	 * @return
	 */
	public String getCallOut() {

		return callOut;
	}
	
	/*
	 * Everything below this line is given to students, either because we haven't
	 * covered loops yet, or because we want to ensure very precise formatting so
	 * that we can test by comparing strings which are printed by the same function.
	 */

	/**
	 * Prints out what the scoreboard must indicate. It counts sets and games in a
	 * natural way, with whole numbers. However points within a game are counted
	 * using the nonconsecutive numbers 0, 15, 30, 40. When there is a deuce, it is
	 * indicated as 40 for both players. If one player has an advantage, their score
	 * is represented as "AD", while the other player's score is simultaneously
	 * indicated as "--". When counting score in tiebreak games, we simply use
	 * normal counting.
	 */
	@Override
	public String toString() {
		String playerAServingIndicator;
		String playerBServingIndicator;
		if (getPlayerAServing()) {
			playerAServingIndicator = "S>";
			playerBServingIndicator = "  ";
		} else {
			playerAServingIndicator = "  ";
			playerBServingIndicator = "S>";
		}
		String returned = String.format("%2s %-12s %2d %2d %6s\n%2s %-12s %2d %2d %6s\n", playerAServingIndicator,
				getPlayerAName(), getPlayerASets(), getPlayerAGames(), getPlayerAScore(), playerBServingIndicator,
				getPlayerBName(), getPlayerBSets(), getPlayerBGames(), getPlayerBScore());
		// System.out.println(returned);
		return returned;
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winPoint, using an argument of true if the corresponding character
	 * is an a, and false if the corresponding character is a b. Provides a
	 * convenient way to run many winPoint method calls with very concise notation.
	 *
	 * @param pointList - "script" that is converted into winPoint method calls.
	 */
	public void runPoints(String pointList) {
		for (int i = 0; i < pointList.length(); ++i) {
			if (pointList.charAt(i) == 'a') {
				winPoint(true);
			} else if (pointList.charAt(i) == 'b') {
				winPoint(false);
			} else {
				// skip the character silently
			}
		}
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winGame, using an argument of true if the corresponding character is
	 * an a, and false if the corresponding character is a b. Provides a convenient
	 * way to run many winGame method calls with very concise notation.
	 *
	 * @param gameList - "script" that is converted into winGame method calls.
	 */
	public void runGames(String gameList) {
		for (int i = 0; i < gameList.length(); ++i) {
			if (gameList.charAt(i) == 'a') {
				winGame(true);
			} else if (gameList.charAt(i) == 'b') {
				winGame(false);
			} else {
				// skip the character silently
			}
		}
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winSet, using an argument of true if the corresponding character is
	 * an a, and false if the corresponding character is a b. Provides a convenient
	 * way to run many winSet method calls with very concise notation.
	 *
	 * @param setList - "script" that is converted into winSet method calls.
	 */
	public void runSets(String setList) {
		for (int i = 0; i < setList.length(); ++i) {
			if (setList.charAt(i) == 'a') {
				winSet(true);
			} else if (setList.charAt(i) == 'b') {
				winSet(false);
			} else {
				// skip the character silently
			}
		}
	}
}


