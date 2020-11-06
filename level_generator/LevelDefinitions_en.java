package generator.level_definitions;

import generator.Main.Config;

public class LevelDefinitions_en {
	
	
	/**
	 * index
	 * 
	 * The number of level. It starts at 0 not 1.
	 */
	

	
	/**
	 * letterCount
	 * 
	 * Number of letters in the longest word, it is also the number of letters in dial
	 */
	
	
	
	/**
	 * score 
	 * 
	 * it uses the words in database whose score matches this number. Use lower numbers in earlier levels and high in later ones.
	 * Score is between -1 and 4 (inclusive). In the database words table all words must be marked with a score. If you create your own
	 * language you can individually mark your words with a score or execute a database query and make them all 1 if you don't want to care.
	 * English and Turkish database tables are shipped with individual scores. The score definitions are as follows:
	 * 
	 * -1: Vulgar words. Never used in the game. If a user swipes such a word, a warning will appear saying that it is not a bonus word.
	 *  0: Non-familiar or somewhat awkward words. Never used in the game. They count towards a bonus word if the user swipes it.
	 *  1:Words that should appear in early levels.
	 *  2:Words that appear after 1.
	 *  3:Words that appear after 2.
	 *  4:They are difficult to find and not very familiar. 
	 *  You may choose to use it if you want to create more levels.
	 * 
	 */
	
	
	
	/**
	 * minWordLength
	 * 
	 * Only the words with this number of length appear in the game. Typically it is 3.
	 */
	
	
	/**
	 * minWords
	 * maxWords
	 * 
	 * Number of words that should appear on the game board.
	 */
	
	
	
	
	/**
	 * reappear
	 * 
	 * reappear helps randomization and word occurrence. Once a word appears in a level, it determines
	 * when it should appear again. it is the number here + a random number between 
	 * 1 and the number of words in that level.
	 */
	
	
	public static Config getLevelConfigByIndex(int index) {
		
		Config cfg = new Config();
		
		
		if(index < 5) {
			cfg.letterCount = 3;
			cfg.score = 1;
			cfg.minWordLength = 3;
			cfg.minWords = 2;
			cfg.maxWords = 2;
			cfg.reappear = 10;
		}
		
		if(index >= 5 && index < 10) {
			cfg.letterCount = 4;
			cfg.score = 2;
			cfg.minWordLength = 3;
			cfg.minWords = 3;
			cfg.maxWords = 5;
			cfg.reappear = 10;
		}
		
		if(index >= 10 && index < 30) {
			cfg.letterCount = 4;
			cfg.score = 2;
			cfg.minWordLength = 3;
			cfg.minWords = 5;
			cfg.maxWords = 6;
			cfg.reappear = 10;
		}
		
		if(index >= 30 && index < 50) {
			cfg.letterCount = 5;
			cfg.score = 2;
			cfg.minWordLength = 3;
			cfg.minWords = 6;
			cfg.maxWords = 7;
			cfg.reappear = 10;
		}
		
		if(index >= 50 && index < 100) {
			cfg.letterCount = 6;
			cfg.score = 2;
			cfg.minWordLength = 3;
			cfg.minWords = 7;
			cfg.maxWords = 8;
			cfg.reappear = 9;
		}
		
		if(index >= 100 && index < 200) {
			cfg.letterCount = randomBetween(6, 7);
			cfg.score = 3;
			cfg.minWordLength = 3;
			cfg.minWords = 7;
			cfg.maxWords = 8;
			cfg.reappear = 9;
		}
		
		if(index >= 200 && index < 300) {
			cfg.letterCount = randomBetween(6, 7);
			cfg.score = 3;
			cfg.minWordLength = 3;
			cfg.minWords = 7;
			cfg.maxWords = 9;
			cfg.reappear = 8;
		}
		
		if(index >= 300 && index < 400) {
			cfg.letterCount = 8;
			cfg.score = 3;
			cfg.minWordLength = 3;
			cfg.minWords = 8;
			cfg.maxWords = 10;
			cfg.reappear = 8;
		}
		
		
		
		if(index >= 400 && index < 700) {
			cfg.letterCount = randomBetween(7, 8);
			cfg.score = 3;
			cfg.minWordLength = 3;
			cfg.minWords = 9;
			cfg.maxWords = 11;
			cfg.reappear = 7;
		}
		
		
		
		
		
		if(index >= 700 && index < 1000) {
			cfg.letterCount = randomBetween(7, 8);
			cfg.score = 3;
			cfg.minWordLength = 3;
			cfg.minWords = 9;
			cfg.maxWords = 12;
			cfg.reappear = 7;
		}
		
		if(index >= 1000 && index < 5000) {
			cfg.letterCount = randomBetween(7, 8);
			cfg.score = 3;
			cfg.minWordLength = randomBetween(3, 4);
			cfg.minWords = 9;
			cfg.maxWords = 13;
			cfg.reappear = 6;
		}
		
		

		if(index >= 5000 && index < 10000) {
			cfg.letterCount = randomBetween(7, 8);
			cfg.score = 4;
			cfg.minWordLength = randomBetween(3, 4);
			cfg.minWords = 9;
			cfg.maxWords = 15;
			cfg.reappear = 5;
		}
		
		
		
		if(index >= 10000) {
			cfg.letterCount = 8;
			cfg.score = 4;
			cfg.minWordLength = 4;
			cfg.minWords = 10;
			cfg.maxWords = 16;
			cfg.reappear = 5;
		}
		
		
		return cfg;
	}
	
	
	
	public static int randomBetween(int min, int max) {// min and max inclusive
		return (int) Math.floor(Math.random() * (max - min + 1) + min);
	}
	
}
