package generator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import db.Database;


public class Main {
	
	
	
	/**
	 * Path to the database file. Don't delete the protocol identifier "jdbc:sqlite:" part at the beginning.
	 */
	public static String databaseJDBCPath = "jdbc:sqlite:C:\\Users\\umit\\Google Drive\\main\\lydia_games\\word_connect\\db\\word_source.db";
	
	
	
	/**
	 * Path to the data folder in the Android/assets directory
	 */
	private static String gameDataFolder = "C:\\Users\\umit\\Documents\\WordConnect\\android\\assets\\data";
	
	

	
	
	
	/**
	 * Number of levels to produce.
	 * Don't forget to update the number of level count at availableLanguages setting in GameConfig.java file 
	 * in Android Studio game project. After generating levels, in your hard drive, go to android project's
	 * asset/data/language/levels folder and take the number of files (levels) in this folder and write it
	 * on the mentioned setting.
	 */
	private static int numberOfLevelsToGenerate = 15000;
	
	
	
	
	
	/**
	 * The letters in the dial. Will they be chosen from the list of words in the database or generated randomly?
	 * Use this option when all the words are exhausted and you can't generate any more levels. 
	 * Normally, the longest word on the board is exactly the full word on the dial. 
	 * When you enable this option, instead of this, a random is formed from the alphabet letters below
	 * and new words are produced from this word. In such a case, the longest word on the board
	 * is not the word on the dial any more. It is a good way to create more levels. But note
	 * that it may be slow due to brute force word generation.
	 */
	private static boolean tryRandomLetters = false;
	
	

	
	
	
	/**
	 * Possible letters to use when tryRandomLetters is true. 
	 */
	//private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//for English
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZĞÜŞİÖÇ";//for Turkish
	
	
	/**
	 * Repeat levels or not. When false, it checks the level folder to avoid duplicate levels. 
	 */
	private static boolean allowDuplicateLevels = false;
	
	
	
	/**
	 * The width and height ratio of generated grids. If you want many many unique levels make it false. 
	 */
	private static boolean forceHorizontalGrid = false;
	
	
	
	/**
	 * The language code. Words belonging to this letter must be in the database and you should make a translation for UI, refer to help file.
	 */
	private static String generateForlanguageCode = "en";
	
	
	/**
	 * Restricts the width and height of the resulting grid.
	 * This is and optimum value for both tall and wide devices.
	 */
	public static int maxRows = 10;
	public static int maxCols = 12;
	
	
	
	
/*********************************************************************************************************************************************************/	
	
	
	
	
	public static void main(String[] args) throws SQLException, IOException {
		generateAllLevelsForALanguage(generateForlanguageCode);
	}
	
	
	
	
	
	
	
	
	private static void generateAllLevelsForALanguage(String language) throws SQLException, IOException {
		
		
		try {
			Class<?> clazz = Class.forName("generator.level_definitions.LevelDefinitions_" + language);
			Method method = clazz.getMethod("getLevelConfigByIndex", int.class);
			
			int startIndex = Database.findNextJsonFileNameFromLevelsFolder(language, gameDataFolder);
			
			for (int i = startIndex; i < startIndex + numberOfLevelsToGenerate; i++) {
				Object o = method.invoke(null, i);
				Config cfg = (Config)o;
				cfg.language = language;
				generateLevel(cfg);
				System.out.println("generated index: " + i);
				
			
			}
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

	
	
	
	
	private static void generateLevel(Config cfg) throws SQLException, IOException {
		
		if(cfg.letterCount < 2) {
			System.err.println("letterCount must be greater or equal 2");
			return;
		}
		
		if(cfg.score < 1 || cfg.score > 4) {
			System.err.println("score must be between 1 and 4 (inclusive)");
			return;
		}
		
		if(cfg.minWords < 2) {
			System.err.println("minWords must be greater than 1");
			return;
		}
			
		
		
		if(cfg.maxWords < 2) {
			System.err.println("maxWords must be greater than 1");
			return;
		}
			
		
		if(cfg.minWords > cfg.maxWords) {
			System.err.println("minWords must be less than maxWords");
			return;
		}
			
		
		Database.writeAllWordsAsJson(cfg.language, gameDataFolder);
		Database.writeVulgarWords(cfg.language, gameDataFolder);

		
		while(true) {
			
			String randomLetters = null;
			DBWord dbword = null;
			
			
			if(tryRandomLetters)
				randomLetters = getRandomString(cfg.letterCount);
			else {
				dbword = Database.findRandomWordTobeLetters(gameDataFolder, cfg.letterCount, cfg.score, cfg.language);
				if(dbword == null) {
					System.out.println("ERROR: COULD NOT SELECT THE MAIN WORD FOR DIAL USING CONFIG: " + cfg.toString());
					System.exit(0);
				}
				randomLetters = dbword.word;
			}
			

			
			List<DBWord> words = Database.selectWords(gameDataFolder, randomLetters, cfg.score, cfg.minWordLength, cfg.language);
			
			if(dbword != null) {
				if(!words.contains(dbword))
					words.add(0, dbword);
			}
				

			
			if(words.size() >= cfg.minWords) {
				
				Generator g = new Generator();
				int count = ThreadLocalRandom.current().nextInt(cfg.minWords, cfg.maxWords + 1);
				g.setExactNumberOFWordsToGenerate(count);
				g.setDataSource(words);
				Board board = g.generate();
				
				if(board == null) {
					System.out.println("board == null");
					continue;
				}
					
				if(forceHorizontalGrid && board.getWidth() < board.getHeight()) {
					System.out.println("board.getWidth() < board.getHeight()");
					continue;
				}
					
				
				
				int totalWords = board.getAcrossWords().size() + board.getDownWords().size();
				
				
				
				if(totalWords < cfg.minWords) {
					System.out.println("not enough words, totalWords < cfg.minWords:" + totalWords +", "+cfg.minWords);
					continue;
				}
				
				boolean exists = false;
				
				if(!allowDuplicateLevels) {
					File levelsFolder = new File(gameDataFolder + File.separator + cfg.language + File.separator + Database.LEVELS_FOLDER_NAME);
					exists = Database.doesLevelAlreadyExistInLevelsFolder(board, randomLetters, cfg.language, levelsFolder);
					if(exists) System.out.println("duplicate level, discard");
					
				}
					

				
				if(board != null && !exists) {
					Database.markUsedWords(board, cfg, gameDataFolder);
					System.out.println("{" + randomLetters + "}, " + board.getAcrossWords() + ", " + board.getDownWords() + ": " + (board.getAcrossWords().size() + board.getDownWords().size()));
					Database.saveLevelAsJsonFile(randomLetters, board, cfg, gameDataFolder);
					break;
				}
			}
		
				
				
		}
	}
	
	
	
	
	
	

	
	
	
	
	
	static String getRandomString(int n){ 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index = (int)(alphabet.length() * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(alphabet.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
	
	public static class Config{
		public int letterCount = 3;
		public int score = 2;
		public int minWordLength = 3;
		public int minWords;
		public int maxWords = 100;
		public String language;
		public int reappear = 10;
		
		@Override
		public String toString() {
			return "Config [letterCount=" + letterCount + ", score=" + score + ", minWordLength=" + minWordLength
					+ ", minWords=" + minWords + ", maxWords=" + maxWords + ", language=" + language + "]";
		}

		
		
		
	}
}
