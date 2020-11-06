package word.game.config;

import com.badlogic.gdx.math.MathUtils;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import word.game.graphics.AtlasRegions;
import word.game.i18n.Locale;
import word.game.model.Constants;
import word.game.ui.dialogs.iap.ItemContent;
import word.game.ui.dialogs.iap.ShoppingDialog;
import word.game.ui.dialogs.wheel.Slice;

import static word.game.ui.dialogs.wheel.RewardRevealType.COINS;
import static word.game.ui.dialogs.wheel.RewardRevealType.FINGER_REVEAL;
import static word.game.ui.dialogs.wheel.RewardRevealType.MULTI_RANDOM_REVEAL;
import static word.game.ui.dialogs.wheel.RewardRevealType.ROCKET_REVEAL;
import static word.game.ui.dialogs.wheel.RewardRevealType.SINGLE_RANDOM_REVEAL;


public class GameConfig {

    /**
     * WARNING: Don't forget to turn off the debug-related settings below
     * before publishing your game to the App Store.
     */



    //Displays lucky wheel as if it is time to show it. SKIP_INTRO must be false
    public static final boolean DEBUG_LUCKY_WHEEL                                = false;

    //Display the rate us dialog as if it is time to show it. SKIP_INTRO must be false
    public static final boolean DEBUG_RATE_US                                    = false;

    //To see the correct answers of levels on logcat output. Filter it using "game.log"
    public static final boolean DEBUG_LEVEL_ANSWERS                              = true;

    //Bonus words win dialog opens as if you won a reward when you click the bonus words button (button with a star on)
    public static final boolean DEBUG_BONUS_WORDS_WIN_DIALOG                     = false;


    /**
     * To debug any level you wish. It skips the preceding levels.
     * If the latest level you've played was incomplete, any data related to
     * it would be deleted when you use this option.
     * This option may not show the booster in the chosen level that displays
     * in normal game-play.
     * The number must be your desired level number minus one (it is zero-based).
     * Make it -1 when you are done.
     */
    public static final int DEBUG_LEVEL_INDEX                                   = -1;





    /**
     * Enables to skip the intro screen.
     * When intro is skipped, back navigation button via the back button at the top-left of
     * the screen will be hidden and the native back button of Android will
     * bring the app to background when tapped at game screen.
     *
     */
    public static final boolean SKIP_INTRO                                       = false;




    /**
     * Players can use reveal hints when they enough coins.
     * It is set to 0 here because they have a lot of change to win coins.
     */
    public static final int DEFAULT_COIN_COUNT                                  = 0;



    /**
     * Number of reveal counts must be at least 2 because
     * 1 of them will be used for tutorial and it is a good idea to have
     * one after the tutorial
     */
    public static final int DEFAULT_SINGLE_RANDOM_REVEAL_COUNT                  = 2;
    public static final int DEFAULT_FINGER_REVEAL_COUNT                         = 2;
    public static final int DEFAULT_MULTI_RANDOM_REVEAL_COUNT                   = 2;
    public static final int DEFAULT_ROCKET_REVEAL_COUNT                         = 2;

    //When the player finds words that don't exist on board, he/she earns some reward
    public static final int NUMBER_OF_BONUS_WORDS_TO_FIND_FOR_REWARD            = 50;
    public static final int NUMBER_OF_COINS_AWARDED_FOR_BONUS_WORDS_REWARD      = 20;



    /**
     * When the player runs out of hints, he/she can still use reveal hints
     * as long as he/she has some coins that are equal or greater than
     * the following values. The cost of reveal hint is then subtracted from coins.
     */
    public static final int COIN_COST_OF_USING_SINGLE_RANDOM_REVEAL             = 100;
    public static final int COIN_COST_OF_USING_MULTI_RANDOM_REVEAL              = 300;
    public static final int COIN_COST_OF_USING_FINGER_REVEAL                    = 200;
    public static final int COIN_COST_OF_USING_ROCKET_REVEAL                    = 200;


    //Gold coins on game board (appears after using a rocket reveal hint and after killing the monster)
    public static final int NUMBER_OF_COINS_EARNED_FOR_TAKING_1_COIN            = 3;


    public static final int NUM_OF_TILES_TO_SET_COIN_AFTER_KILLING_THE_MONSTER  = 10;


    /**
     * The rewarded video ad button display a glow effect to catch the attention
     *  of the player. By default it is a random value between 15 and 60 seconds.
     */
    public static final float REWARDED_VIDEO_BUTTON_GLOW_INTERVAL               = MathUtils.random(15, 60);





    /**
     * By default the cost of a single random reveal hint is 100. If the player
     * watches 4 videos, he/she will earn a single random reveal hint.
     * Note: You can configure rewarded video availability at res/strings.xml
     */
    public static final int NUMBER_OF_COINS_EARNED_FOR_WATCHING_VIDEO           = 10;





    /**
     * Multi-random reveal button is the one with a blue bolt on.
     * You should return at least 2 so that it reveals more than the single random hint.
     * It is a good idea to decorate the board with more revealed tiles as the
     * size of the board, hence the difficulty of the game increases. So you should
     *  return a number proportional to the levelIndex parameter.
     *  Or, you can totally ignore this and return a constant number such as 3.
     * @param levelIndex 0-based level number
     * @return reveal count
     */
    public static int getNumberOfTilesToRevealForMultiRandomHint(int levelIndex){
        if(levelIndex < 100) return 3;
        else if(levelIndex < 500) return 4;
        else if(levelIndex < 3000) return 5;
        else return 6;
    }





    /**
     * This function runs at the beginning of each level to determine whether to show
     * an interstitial ad or not. You can customize it as you wish and return true or false.
     * true shows and ad, false doesn't. It doesn't run at all if remove ads was purchased
     * or interstitial ad is disabled. By default it works like so:
     *
     * 1.It doesn't show any ads before the rocket tutorial level is complete (10).
     * Setting interactive tutorials to false doesn't affect this behaviour.
     * It is not a good idea to show ads too early.
     *
     * 2.If level index is less than 30 it will show an ad once in every 3 levels.
     *
     * 3.If level index is less than 50 it will show an ad once in every 2 levels.
     *
     * 4.If level index is greater than 50 it will show it without any condition.
     *
     * @param levelIndex 0-based level number
     * @return true or false
     */
    public static boolean shouldWeShowAnInterstitialAdForThisLevel(int levelIndex){

        if(levelIndex < Constants.TUTORIAL_ROCKET_LEVEL + 1) return false;

        if(levelIndex < 30) {
            if(levelIndex % 3 == 0) return true;
            return false;
        }

        if(levelIndex < 50) {
            if(levelIndex % 2 == 0) return true;
            return false;
        }

        if(levelIndex > 50) return true;

        return false;
    }




    /**
     * After displaying an interstitial ad, the player is offered to purchase
     * Remove Ads. However, it may be distracting to see such an offering
     * too often. It is up to you to use it or not.
     */
    public static final boolean SHOW_REMOVE_ADS_DIALOG_AFTER_INTERSTITIAL   = true;




    /**
     * If the player closes the IAP dialog without making
     * a purchase, then it shows another dialog to watch
     * a rewarded video and earn coins
     */
    public static final boolean SHOW_WATCH_AD_AFTER_IAP                     = true;




    /**
     * Note that google rejects apps that open IAP dialog when a hint button is tapped.
     * Due to this rule such a functionality has not been implemented. Instead,
     * tapping the hint buttons may open a dialog prompting the user to watch an ad
     * earn coins.
     */
    public static final boolean SHOW_WATCH_AD_WHEN_NO_COINS_AND_HINTS_LEFT  = true;






    /**
     * In app purchase items. Do not change the ids, add or delete new items.
     * Only change the amounts of coins and/or reveals in ItemContent.
     */
    public static void setUpIAPToItemMapping(){

        ShoppingDialog.mapping.put("06", new ItemContent(240, AtlasRegions.coins1));//IAP_ITEM_coin_240
        ShoppingDialog.mapping.put("07", new ItemContent(760, AtlasRegions.coins2));//IAP_ITEM_coin_760
        ShoppingDialog.mapping.put("08", new ItemContent(1340, AtlasRegions.coins3));//IAP_ITEM_coin_1340
        ShoppingDialog.mapping.put("09", new ItemContent(2940, AtlasRegions.coins4));//IAP_ITEM_coin_2940
        ShoppingDialog.mapping.put("10", new ItemContent(6240, AtlasRegions.coins5));//IAP_ITEM_coin_6240
        ShoppingDialog.mapping.put("11", new ItemContent(13440, AtlasRegions.coins6));//IAP_ITEM_coin_13440

        ShoppingDialog.mapping.put("02", new ItemContent(1340, 3, 3, 3, 3, AtlasRegions.bundle1));//IAP_ITEM_pack_mini
        ShoppingDialog.mapping.put("03", new ItemContent(2940, 7, 7, 7, 7, AtlasRegions.bundle2));//IAP_ITEM_pack_medium
        ShoppingDialog.mapping.put("04", new ItemContent(6840, 14, 14, 14, 14, AtlasRegions.bundle3));//IAP_ITEM_pack_large
        ShoppingDialog.mapping.put("05", new ItemContent(15440, 30, 30, 30, 30, AtlasRegions.bundle4));//IAP_ITEM_pack_jumbo

        ShoppingDialog.mapping.put("01", new ItemContent(true, AtlasRegions.remove_ads));//IAP_ITEM_remove_ads

    };







    /**
     * Lucky Wheel
     * The first spin is done without a restriction. The subsequent ones requires watching a rewarded video ad
     * if rewarded video for spinning is enabled.
     * Make it 0 to completely disable luck wheel.
     */
    public static final int ALLOWED_SPIN_COUNT                           = 3;




    /**
     * Lucky wheel has 8 slices to configure, do not add or delete new slices.
     * Each slice has the following parameters:
     *
     * text: the label of the slice (string). It is typically the quantity and language independent.
     * reward: the type of reward(Slice.Reward enum type). Possible values: COINS, SINGLE_RANDOM_REVEAL, FINGER_REVEAL, MULTI_RANDOM_REVEAL, ROCKET_REVEAL
     * quantity: the number of rewards to give away (int)
     * probability: chance of winning (int). Total number is 100(%). When you add all of them (8), they must be equal to exactly 100,
     * the lesser, the less chance of winning. This total value is not checked in code, so be careful.
     */
    public static Slice[] slices = new Slice[]{
            new Slice("25",     COINS,                  25, 17),
            new Slice("50",     COINS,                  50, 16),
            new Slice("1",      FINGER_REVEAL,          1,  16),
            new Slice("500",    COINS,                  500,1),
            new Slice("1",      ROCKET_REVEAL,          1,  16),
            new Slice("1",      SINGLE_RANDOM_REVEAL,   1,  17),
            new Slice("1",      MULTI_RANDOM_REVEAL,    1,  16),
            new Slice("750",    COINS,                  750,1)
    };





    /**
     * Supported languages by the game. Each control both UI labels and game words.
     * k: two-letter language code such en, de, es, pt. Please refer to the help
     * file for the instructions to add a new language.
     * If you set only 1 language, language selection will not be prompted in the
     * first run of the game and it will not be visible in menu.
     * If you add new levels or delete existing ones, you must modify it below.
     * The number of levels is not read automatically by code because it is
     * a very slow process (this is tested and discarded). Therefore, it is
     * best to hard code it here.
     */
    public static Map<String, Locale> availableLanguages = new LinkedHashMap<String, Locale>(){
        {
            put("en", new Locale(10034));
            put("tr", new Locale(10032));
        }
    };




    /**
     * Booster config.
     *
     * Start level is 0-based.
     * Don't let them start in small grids such as 3x3 because they need some space.
     * The first booster is the UFO below.
     * Don't try to offer more than one booster in a single level, the result is undefined behaviour.
     * You can disable any of them.
     */
    public static final int BOOSTERS_START_LEVEL = 14;



    /**
     * The boosters show in every n levels.
     * After 4 of the boosters are shown they reappear again
     * starting from the first one.
     */
    public static final int OFFER_BOOSTER_EVERY_N_LEVEL = 5;


    //Appearance order for boosters. If you wish to change their order.
    public static final int BOOSTER_UFO       = 0;
    public static final int BOOSTER_BOMB      = 1;
    public static final int BOOSTER_GOLD_PACK = 2;
    public static final int BOOSTER_MONSTER   = 3;



    //If you don't like any of the boosters, leave it out by specifying it as false below
    public static final Map<Integer, Boolean> ENABLED_BOOSTERS = new HashMap<Integer, Boolean>(){
        {
            put(BOOSTER_UFO,          true);
            put(BOOSTER_BOMB,         true);
            put(BOOSTER_GOLD_PACK,    true);
            put(BOOSTER_MONSTER,      true);
        }
    };



    //The ufo flies away after the time specified below. Starts when the ufo finishes landing.
    public static final float SECONDS_BEFORE_UFO_DISAPPEARS = 10f;

    //If the player hits the ufo before it flies away.
    public static final int NUMBER_OF_COINS_EARNED_FOR_HITTING_UFO = 15;



    //If the player can't disarm the bomb with the given moves, he/she
    //will be given some more moves in return to watching a rewarded ad.
    public static final int EXTRA_BOMB_MOVES_FOR_WATCHING_AD = 5;





    /**
     * Rate us dialog settings.
     * The settings here do not affect the rate us option in the menu dialog.
     * If the user clicks the "later" button, the dialog will not be shown
     * again. In this case, he/she can rate the game using the menu option later on.
     * Rate us dialog pops up when the user opens the app and if it is not time for lucky wheel.
     */
    public static final boolean SHOW_RATE_DIALOG        = true;
    public static final int DAYS_TO_ELAPSE_BEFORE_RATE  = 3;
    public static final int APP_LAUNCHES_BEFORE_RATE    = 5;





    /**
     * The single random hint button tries to catch the attention of the player.
     * How long should the single random hint button bulb should wait
     * before the user makes a move? When the user stops for a while
     * the bulb icon starts animating.
     * By default, after waiting for 10 seconds, it animates every other 4 seconds.
     */
    public static final float IDLE_TIMER_DURATION = 15;
    public static final float INTERVAL_BETWEEN_HINT_INDICATIONS = 5;






    //Speed of animating letters after a bonus word is found
    public static final float BONUS_WORD_LETTER_ANIM_SPEED = 0.3f;


}
