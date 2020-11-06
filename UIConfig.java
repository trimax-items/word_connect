package word.game.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import word.game.WordConnectGame;
import word.game.managers.LanguageManager;
import word.game.managers.ResourceManager;
import word.game.ui.calendar.Date;

public class UIConfig {

    /**
     * HOW TO CONFIGURE?
     *
     * You should enter values in a specific format.
     *
     * Colors: You can use short-hand color format such as: Color.WHITE, Color.PURPLE, Color.GREEN, etc.
     * For more fine-tuned colors you should create a Color object suppling the color value
     * in 4-byte hex format as in  new Color(0x65ab29ff). This color is in RGBA format.
     *
     * Booleans: Settings with boolean values determine whether that setting is enabled or disabled
     * by using the values true or false.
     *
     * Integers: Integers are whole numbers typically specifying the count of an item such as particles.
     *
     * Floats: Floats are numbers with decimal precision. You must enter them with a letter f at the end.
     *
     * Please play the game for a while and become familiar with the UI to understand the settings below.
     * To find out where a setting is used, select the variable name right-click and select Find Usages.
     *
     */





/**************************************************************************************************************************************************************************/

    /**
     * Preloader
     */
    public static final Color LOADING_BAR_COLOR = new Color(0x65ab29ff);

/**************************************************************************************************************************************************************************/

    /**
     * Background colors
     */
    public static final Color INTRO_SCREEN_BACKGROUND_COLOR = new Color(0x000000FF);
    public static final Color GAME_SCREEN_BACKGROUND_COLOR = new Color(0x000000FF);




    /**
     * Here you can customize which background image to show at intro screen depending on date.
     * Current implementation shows different images on Halloween, Thanksgivig and new year.
     *
     * Not every culture and country celebrate the Halloween and Thanksgiving. Background images
     * related to these two days are available to users whose selected language code is English ("en").
     * If you wish to make them available for other languages specify them in the first "if"
     * condition checking. For example, if you add Spanish, it will become like this:
     *
     * if(langCode == null || langCode.equals("en") || langCode.equals("es")){
     *
     *
     * If you want to add another festive day, note that the month part of the date object is
     * 0 based, i.e January is month 0.
     * If you want to stick to only a single background image ignoring date, then delete the function body
     * and return that specific image like: return "background/intro/my_intro_screen_bg.jpg" (without the quotes).
     * Or you can show a different image depending on the season.
     * Optimal image size is: 1080x1920 or 1280x1920. The image will be scaled to fit, keeping the aspect ratio.
     * It is best to use jpg to consume less memory.
     * You can optimize your images here: https://tinyjpg.com/
     *
     * @param wordConnectGame The base game object to obtain date
     * @return It returns the path to the background image in the assets folder
     */
    public static String getIntroScreenBackgroundImage(WordConnectGame wordConnectGame){
        Date date = wordConnectGame.dateUtil.newDate();
        String langCode = LanguageManager.getSelectedLocaleCode();

        if(langCode == null || langCode.equals("en")){
            //For halloween. Shows it on October 31. Months are 0 based not 1.
            //Gdx.app.log("date:", date.toString());

            if(ConfigProcessor.isHallowenDay(date)){
                return "background/intro/halloween.jpg";
            }else if(ConfigProcessor.isThanksGivingDay(wordConnectGame, date)){
                //For Thanksgiving. Shows it on the fourth thursday of November.
                return "background/intro/thanksgiving.jpg";
            }
        }

        //For new year
        if(ConfigProcessor.isChristmasHoliday(wordConnectGame, date)){
            return "background/intro/newyear.jpg";
        }


        //None of the above conditions are met (i.e. language is not English and it is not a festive day)
        return "background/intro/intro.jpg";
    }









    /**
     * You can specify which background to show depending on the level index.
     * In the default implementation, it shows the same image for 10 levels
     * and then switches to the next, i.e. it cycles. The total number of images is 10.
     * You can add more images and in such a case you should increase the value
     * of total variable below. The images are in assets/background/game folder.
     * Use the same naming style if you want to add new images.
     *
     * @param levelIndex 0-based level number
     * @return It returns the path to the background image in the assets folder
     */
    public static String getGameScreenBackgroundImage(int levelIndex){
        int total = 10;
        int num = levelIndex / total;
        num %= total;

        return "background/game/game_" + num + ".jpg";
    }


/**************************************************************************************************************************************************************************/
    //Tutorials that pop-up on the first game play
    public static final boolean INTERACTIVE_TUTORIAL_ENABLED                    = true;
    public static final Color INTERACTIVE_TUTORIAL_TEXT_BG_COLOR                = new Color(0x9c9777ff);
    //Be careful, color format is different here
    public static final String INTERACTIVE_TUTORIAL_TEXT_COLOR                  = "[#ffffff]";
    public static final boolean INTERACTIVE_TUTORIAL_TEXT_USE_SHADOW_FONT       = true;
    //For eg, Color of B-I-R-D as in "Drag your finger to spell B-I-R-D"
    public static final String INTERACTIVE_TUTORIAL_DASHED_DIAL_WORD_COLOR      = "[#ffff00]";
    public static final Color INTERACTIVE_TUTORIAL_GOT_IT_BACKGROUND_COLOR      = new Color(0xc451b8ff);
    public static final Color INTERACTIVE_TUTORIAL_GOT_IT_TEXT_COLOR            = Color.WHITE;
    public static final boolean INTERACTIVE_TUTORIAL_GOT_IT_USE_SHADOW_FONT     = false;

/**************************************************************************************************************************************************************************/
    //For top panel
    public static final Color REMAINING_COINS_TEXT_COLOR            = Color.WHITE;
    public static final boolean REMAINING_COINS_USE_SHADOW_FONT     = false;
    public static final Color LEVEL_NUMBER_TEXT_COLOR               = Color.WHITE;
    public static final boolean LEVEL_NUMBER_TEXT_USE_SHADOW_FONT   = true;
    public static final Color COMBO_REWARD_TEXT_COLOR               = Color.WHITE;
    public static final boolean COMBO_REWARD_TEXT_USE_SHADOW_FONT   = true;


/**************************************************************************************************************************************************************************/
    //Play buttons in the intro screen and calendar dialog
    public static final Color INTRO_PLAY_BUTTON_TEXT_COLOR          = Color.WHITE;
    public static final boolean INTRO_PLAY_BUTTON_USE_SHADOW_FONT   = true;
    public static final float INTRO_PLAY_BUTTON_WIDTH_COEF          = 3.0f;
    public static final float INTRO_PLAY_BUTTON_FONT_SCALE          = 1.0f;
    public static final boolean INTRO_PLAY_BUTTON_PULSATE           = true;

/**************************************************************************************************************************************************************************/

    //Common settings for all dialogs
    public static final float DIALOG_OPEN_DURATION                      = 0.4f;
    public static final float DIALOG_CLOSE_DURATION                     = 0.6f;
    public static final Color DIALOG_MODAL_BACKGROUND_COLOR             = new Color(0, 0, 0, 0.7f);
    public static final Color DIALOG_TITLE_TEXT_COLOR                   = Color.WHITE;
    public static final boolean DIALOG_TITLE_TEXT_USE_SHADOW_FONT       = true;
    public static final float DIALOG_TITLE_FONT_SCALE                   = 1.0f;
    public static final Color DIALOG_TITLE_BACKROUND_COLOR              = new Color(0xAB4372ff);
    public static final Color DIALOG_BACKGROUND_COLOR                   = new Color(0xDFD3C1ff);
    public static final Color DIALOG_BODY_TEXT_COLOR                    = new Color(0x6e5a3cff);
    public static final boolean DIALOG_BODY_TEXT_USE_SHADOW_FONT        = false;
    public static final float DIALOG_BODY_TEXT_FONT_SCALE               = 0.8f;
    public static final float CONFIRM_DIALOG_BUTTON_WIDTH_COEF          = 0.4f;
    public static final float CONFIRM_DIALOG_BUTTON_FONT_SCALE          = 1.0f;
    public static final float CONFIRM_DIALOG_BUTTON_SCALE               = 0.85f;
    public static final boolean CONFIRM_DIALOG_BUTTON_USE_SHADOW_FONT   = true;
    public static final float ALERT_DIALOG_BUTTON_WIDTH_COEF            = 0.7f;
    public static final float ALERT_DIALOG_BUTTON_FONT_SCALE            = 1f;
    public static final boolean ALERT_DIALOG_BUTTON_USE_SHADOW_FONT     = true;

/**************************************************************************************************************************************************************************/

    /**
     * Game Menu
     * You should always keep GDPR enable or Google may terminate your google play contract.
     * GDPR menu item is visible only in EU countries and the UK. In order to test GDPR
     * functionality in other countries please refer to res/values/strings.xml.
     * Language button will be hidden if you configure only one language for the game
     * regardless of what you specify here.
     */
    public static final boolean MENU_ITEM_GDPR_ENABLED                  = true;
    public static final boolean MENU_ITEM_RATE_US_ENABLED               = true;
    public static final boolean MENU_ITEM_CONTACT_US_ENABLED            = true;
    public static final boolean MENU_ITEM_LANGUAGE_ENABLED              = true;
    public static final boolean MENU_ITEM_SOUND_ENABLED                 = true;


    public static final Color MENU_ITEM_BG_COLOR                        = new Color(0xA89D8Bff);
    public static final Color MENU_ITEM_TEXT_COLOR                      = Color.WHITE;
    public static final boolean MENU_ITEM_USE_SHADOW_FONT               = true;
    public static final Color MENU_DIALOG_VERSION_TEXT_COLOR            = new Color(0xA89D8Bff);
    public static final boolean MENU_DIALOG_VERSION_USE_SHADOW_FONT     = false;

    //Item height multiplied by icon height
    public static final float MENU_ITEM_HEIGHT_COEF                     = 1.6f;

    //Vertical space is multiplied by item height
    public static final float MENU_ITEM_SPACING_COEF                    = 0.1f;


/**************************************************************************************************************************************************************************/
    //Language dialog. Color of the circle that indicates the selected language
    public static final Color LANGUAGE_DIALOG_SELECTION_GLOW_COLOR      = new Color(0x0093AEff);

/**************************************************************************************************************************************************************************/

    //Lucky wheel
    public static final Color WHEEL_DIALOG_SPIN_BUTTON_TEXT_COLOR               = Color.WHITE;
    public static final float WHEEL_DIAL_SPIN_BUTTON_WIDTH_COEF                 = 0.9f;
    public static final boolean WHEEL_DIALOG_SPIN_BUTTON_USE_SHADOW_FONT        = true;
    public static final float WHEEL_DIALOG_SPIN_BUTTON_FONT_SCALE               = 0.9f;

    //background
    public static final Color WHEEL_DIALOG_BACKGROUND_COLOR                     = new Color(0xA89D8Bff);
    public static final Color WHEEL_DIALOG_RAYS_COLOR                           = new Color(0xc0b39dff);

    //alternating colors on dark and light slices of the wheel
    public static final Color WHEEL_DIALOG_ITEM_QUANTITY_TEXT_COLOR_DARK        = Color.BLACK;
    public static final Color WHEEL_DIALOG_ITEM_QUANTITY_TEXT_COLOR_LIGHT       = Color.WHITE;

    //flashing lights on the wheel
    public static final Color WHEEL_DIALOG_LIGHT_BULB_COLOR                     = Color.GREEN;

    //pick what you earned text
    public static final Color WHEEL_DIALOG_TAP_TO_COLLECT_TEXT_COLOR            = Color.WHITE;
    public static final boolean WHEEL_DIALOG_TAP_TO_COLLECT_USE_SHADOW_FONT     = false;

    //what you earn after spinning text and rounded-rectangle image background
    public static final Color WHEEL_DIALOG_REWARD_COUNT_BACKGROUND_COLOR        = Color.WHITE;
    public static final Color WHEEL_DIALOG_REWARD_COUNT_TEXT_COLOR              = Color.BLACK;
    public static final boolean WHEEL_DIALOG_REWARD_COUNT_TEXT_USE_SHADOW_FONT  = false;

    //appears at the end
    public static final Color WHEEL_DIALOG_COME_BACK_TEXT_COLOR                 = Color.WHITE;
    public static final boolean WHEEL_DIALOG_COME_BACK_USE_SHADOW_FONT          = true;

    //Speed of the confetti papers. pixels / frame.
    public static final float WHEEL_DIALOG_CONFETTI_VELOCITY                    = 8f;

/**************************************************************************************************************************************************************************/

    //In app purchases
    public static final Color IAP_DIALOG_TITLE_TEXT_COLOR               = Color.WHITE;
    public static final Color IAP_DIALOG_TITLE_BG_COLOR                 = new Color(0xcead92FF);

    public static final Color IAP_DIALOG_LOADING_CIRCLE_COLOR           = new Color(0xffe9d8FF);

    public static final Color IAP_ONE_TIME_OFFER_RIBBON_COLOR           = new Color(0x2cb193FF);
    public static final Color IAP_COINS_RIBBON_COLOR                    = new Color(0x3878cfFF);
    public static final Color IAP_COMBO_PACK_RIBBON_COLOR               = new Color(0xcf3883FF);
    public static final Color IAP_RIBBON_TEXT_COLOR                     = Color.WHITE;
    public static final boolean IAP_RIBBON_USE_SHADOW_FONT              = true;

    //items cards, both bundles and coins
    public static final Color IAP_CARD_TITLE_TEXT_COLOR                 = new Color(0x6e5a3cff);
    public static final boolean IAP_CARD_TITLE_TEXT_USE_SHADOW_FONT     = false;
    public static final Color IAP_CARD_BG_COLOR                         = new Color(0xffeaccFF);
    public static final Color IAP_CARD_CENTER_BG_COLOR                  = new Color(0xdfbb9eFF);
    public static final Color IAP_CARD_QUANTITY_TEXT_COLOR              = Color.WHITE;
    public static final boolean IAP_CARD_QUANTITY_TEXT_USE_SHADOW_FONT  = true;

    //common for all buy buttons
    public static final Color IAP_BUY_BUTTON_TEXT_COLOR                 = Color.WHITE;
    public static final boolean IAP_BUY_BUTTON_TEXT_USE_SHADOW_FONT     = true;

    public static final float IAP_COINS_BUY_BUTTON_TEXT_SCALE           = 1.0f;
    public static final float IAP_BUNDLE_BUY_BUTTON_TEXT_SCALE          = 1.2f;

/**************************************************************************************************************************************************************************/
     //Bonus words dialog, displaying the words you found but that don't exist on the board.
    public static final Color BWD_WORDS_TEXT_COLOR                      = new Color(0xDFD3C1ff);
    public static final Color BWD_WORDS_TITLE_COLOR                     = Color.WHITE;
    public static final Color BWD_WORDS_BG_COLOR                        = new Color(0xa7957aFF);
    public static final Color BWD_BADGE_TEXT_COLOR                      = new Color(0xeaeaeaFF);

    //Background color when user earns reward. It should be dark because there are sparkles to be seen
    public static final Color BWD_DIALOG_REWARD_MODE_BG_COLOR           = new Color(0xb7aea1ff);
    public static final Color BWD_DIALOG_REWARD_MODE_BODY_TEXT_COLOR    = Color.WHITE;

/**************************************************************************************************************************************************************************/
    //Remove ads dialog appears after an interstitial ad
    public static final Color REMOVE_ADS_DIALOG_COST_TEXT_COLOR                     = Color.WHITE;
    public static final boolean REMOVE_ADS_DIALOG_COST_TEXT_USE_SHADOW_FONT         = false;
    public static final Color REMOVE_ADS_DIALOG_BUY_BUTTON_TEXT_COLOR               = Color.WHITE;
    public static final boolean REMOVE_ADS_DIALOG_BUY_BUTTON_TEXT_USE_SHODOW_FONT   = true;
    public static final float REMOVE_ADS_DIALOG_BUY_BUTTON_WIDTH_COEF               = 0.7f;
    public static final float REMOVE_ADS_DIALOG_BUY_BUTTON_FONT_SCALE               = 1f;

/************************************************************************************************************************************************************************/
    //Watch and earn dialog appears when the player closes the IAP dialog without making a purchase
    public static final float WATCH_AND_EARN_DIALOG_BUTTON_WIDTH_COEF   = 0.8f;
    public static final float WATCH_AND_EARN_DIALOG_BUTTON_FONT_SCALE   = 1f;

/**************************************************************************************************************************************************************************/
    //The dictionary appears after clicking a completed word on the grid or on tapping the dictionary button at level end.
    public static final Color DICTIONARY_DIALOG_CENTER_BG_COLOR                 = new Color(0xA89D8BFF);
    public static final Color DICTIONARY_DIALOG_LOADING_CIRCLE_COLOR            = new Color(0xDFD3C1ff);
    public static final Color DICTIONARY_DIALOG_NAVIGATION_ARROW_COLOR          = new Color(0x66563DFF);
    public static final Color DICTIONARY_DIALOG_WORD_TEXT_COLOR                 = new Color(0x402E32ff);
    public static final float DICTIONARY_DIALOG_WORD_TEXT_SCALE                 = 1.2f;
    public static final boolean DICTIONARY_DIALOG_WORD_TEXT_USE_SHADOW_FONT     = false;
    public static final Color DICTIONARY_DIALOG_MEANING_TEXT_COLOR              = Color.WHITE;
    public static final boolean DICTIONARY_DIALOG_MEANING_TEXT_USE_SHADOW_FONT  = true;

/**************************************************************************************************************************************************************************/
    //At the end of levels

    //particles that are emitted behind the cup view
    public static final int LEVEL_FINISHED_VIEW_PARTICLE_COUNT                          = 7;
    public static final Color LEVEL_FINISHED_VIEW_BG_COLOR                              = new Color(0x00000056);

    //appears when all levels are consumed
    public static final Color GAME_COMPLETELY_FINISHED_TEXT_COLOR                       = Color.WHITE;
    public static final boolean GAME_COMPLETELY_FINISHED_TEXT_USE_SHADOW_FONT           = false;

    //other
    public static final Color LEVEL_FINISHED_VIEW_COINS_EARNED_TEXT_COLOR               = Color.WHITE;
    public static final boolean LEVEL_FINISHED_VIEW_COINS_EARNED_TEXT_USE_SHADOW_FONT   = false;
    public static final Color LEVEL_FINISHED_VIEW_STAR_PARTICLES_COLOR                  = new Color(0xfff772ff);

    public static final float LEVEL_END_VIEW_NEXT_LEVEL_BUTTON_WIDTH_COEF               = 1.2f;
    public static final float LEVEL_END_VIEW_NEXT_LEVEL_BUTTON_FONT_SCALE               = 1f;
    public static final boolean LEVEL_END_VIEW_NEXT_LEVEL_BUTTON_USE_SHADOW_FONT        = true;

/**************************************************************************************************************************************************************************/
    //Coin animation that appears after watching a rewarded video. It shows the number of coins earned.
    public static final Color COIN_ANIM_TEXT_COLOR  = new Color(0x7fd645ff);
    public static final float COIN_ANIM_DURATION    = 0.8f;

/**************************************************************************************************************************************************************************/
    //Preview is the component that shows the letters formed as the player drags his/her finger over the letter buttons of the dial.
    public static final Color PREVIEW_TEXT_COLOR        = Color.WHITE;
    public static final float PREVIEW_FONT_SCALE        = 0.7f;

/**************************************************************************************************************************************************************************/
    //Game related

    //when a tile is solved
    public static final Color GAME_GRID_LETTER_COLOR_SOLVED                 = Color.WHITE;

    //when a reveal hint is applied not solved
    public static final Color GAME_GRID_LETTER_COLOR_REVEALED               = Color.GRAY;

    //When a tile is solved or a reveal hint is applied
    public static final int TILE_SUCCESS_SPARKLE_PARTICLE_COUNT             = 8;
    public static final Color TILE_SUCCESS_SPARKLE_PARTICLE_COLOR           = new Color(0xe2d143ff);


    /**
     * Board tiles background color.
     *
     * This and the subsequent few functions take in the level index as parameter
     * and depending on the level you can change the output color. Do this if
     * you have a special purpose. By default, it returns a constant color
     * regardless of the level index.
     */
    public static Color getTileBackgroundUnsolvedColorByLevelIndex(int levelIndex){
        return new Color(0xffffffb3);
    }



    //Customize dial background color depending on the level
    public static Color getDialBackgroundColorByLevelIndex(int levelIndex){
        return new Color(0xffffffb3);
    }

    //Customize the dial button text up color depending on the level
    public static Color getDialButtonTextColorUpStateByLevelIndex(int levelIndex){
        return Color.BLACK;
    }

    //Customize dial button text down color depending on the level
    public static Color getDialButtonTextColorDownStateByLevelIndex(int levelIndex){
        return Color.WHITE;
    }


    //font scale of board letters in relation to tile size
    public static final float TILE_LETTER_FONT_SCALE                        = 0.55f;


    //return font scale for dial buttons depending on the number of letters on the dial
    public static float getDialButtonLetterFontScale(int numLetters){
        switch (numLetters){
            case 3:
                return 0.6f;
            case 4:
                return 0.7f;
            case 5:
            case 6:
                return 0.75f;
            case 7:
                return 0.8f;
            case 8:
                return 0.85f;
            default:
                return 1;
        }
    }


    //distance of dial buttons from the perimeter of the dial.
    //It is a coefficient multiplied by the diameter of the dial.
    public static final float DIAL_BUTTON_MARGIN                            = 0.02f;





    /**
     * When the user swipes letters a line appears between letters...
     * The thickness of the line is adjusted here depending on the number
     * of letters on the dial.
     */
    public static final float getLengthOfLinesBetweenDialLetters(int buttonCount){
        if(buttonCount <= 5) return 10.0f;
        else return 8.0f;
    }




    //tail of rocket hint
    public static final Color ROCKET_FIRE_COLOR                             = new Color(0xf2c327ff);

    //When applying finger hint. Alpha wll be ignored in the following color
    public static final Color FINGER_SELECTED_TILE_COLOR                    = Color.GREEN;

    //Alternate colors for dial combo particles
    public static final Color DIAL_ROUND_PARTICLE_COLOR                     = Color.GREEN;
    public static final Color DIAL_STAR_PARTICLE_COLOR                      = Color.YELLOW;
    public static final Color DIAL_STRIP_PARTICLE_COLOR                     = Color.WHITE;
    public static final Color DIAL_PARTICLE_ALTERNATE_COLOR                 = Color.ORANGE;


    /**
     * Specifies how large the dial buttons are according to the
     * total number of buttons. The diameter of the buttons are
     * multiplied with the result. 3-letter levels have the
     * largest, 8-letter levels the smallest.
     *
     * @param total: number of buttons, i.e. letters on dial
     * @return: scale
     */
    public static float calculateDialButtonScale(int total){
        switch (total){
            case 3:
                return 1.0f;
            case 4:
                return 0.8f;
            case 5:
                return 0.75f;
            case 6:
                return 0.7f;
            case 7:
                return 0.65f;
            case 8:
                return 0.6f;
            default:
                return 1.0f;
        }

    }



    //ribbon color when the user disarms the bomb
    public static final Color BOMB_DEFUSED_RIBBON_COLOR                     = new Color(0x30a8b6ff);

    //feedback appears after successful combos
    public static final Color FEEDBACK_RIBBON_COLOR                         = new Color(0xb82121ff);



    //feedback common config for combo and bomb disarm
    public static final Color FEEDBACK_TEXT_COLOR                           = Color.WHITE;
    public static final boolean FEEDBACK_TEXT_USE_SHADOW_FONT               = false;
    //don't make the duration too long or it will stay on screen after the level ends
    public static final float FEEDBACK_SHOW_DURATION                        = 0.5f;


    //It specifies the color of particles left behind coin animations. (after hitting the gold pack, ufo and at level end)
    public static final Color COIN_ANIMATION_SPARKLE_COLOR                  = new Color(0xe7d418ff);


    /**
     * The color of tiles' background, preview, dial buttons when they are touched down and the connecting lines among these buttons.
     * It cycles when the final color is consumed. You can change and add new colors below.
     */
    public static final Color[] levelColors = {
            Color.ROYAL,
            Color.FOREST,
            Color.ORANGE,
            Color.FIREBRICK,
            Color.SCARLET,
            Color.VIOLET,
            Color.GRAY,
            Color.TEAL,
            Color.OLIVE,
            Color.BROWN,
            Color.PINK
    };


    //After a successful combo a small star appears on the left of the screen and moves to top.
    public static final Color SIDE_COMBO_STAR_COLOR                 = new Color(0xfffac2ff);






    /**
     * If the user solves the words successively, the screen shakes.
     * The intensity of the shake increases with the combo count.
     */
    public static final boolean ENABLE_CAMERA_SHAKE                 = true;

    /**
     * The following number determines the amount of shaking.
     * This number is multiplied by the width of the screen
     * to achieve a proportional shaking across different
     * screen sizes and combo count. The shake amout increases
     * with subsequent combos. More than 0.008f can be unpleasant.
     */
    public static final float getComboShakeAmount(int comboCount){
        switch (comboCount){
            case 2: return 0.005f;
            case 3: return 0.006f;
            case 4: return 0.0065f;
            case 5: return 0.007f;
            case 6: return 0.0075f;
            default: return 0.008f;

        }
    }



/**************************************************************************************************************************************************************************/
    //Reveal hint buttons

    //cost text appears  when the quantity of reveal hints is 0.
    public static final Color HINT_BUTTON_COST_TEXT_COLOR           = Color.BLACK;

    //number of remaining hints on reveal hint buttons
    public static final Color HINT_BUTTON_REMANING_TEXT_COLOR       = Color.WHITE;
    public static final Color HINT_BUTTON_REMANING_BG_COLOR         = new Color(0x000000c9);

    //common for cost and quantity
    public static final boolean HINT_BUTTON_TEXT_USE_SHADOW_FONT    = false;

    /**************************************************************************************************************************************************************************/
    //The toast displays some information or warning on a strip
    public static final Color TOAST_BACKGROUND_COLOR        = new Color(0x00000095);
    public static final Color TOAST_TEXT_COLOR              = new Color(0xfece57ff);
    public static final boolean TOAST_TEXT_USE_SHADOW_FONT  = false;
    public static final float TOAST_RUN_TIME                = 2f;

    /**************************************************************************************************************************************************************************/

    //Tooltips. They appear only when the player taps a locked reveal hint button at early stages of the game.
    //width of tooltip multiplied by screen width
    public static final float TOOLTIP_WIDTH_COEF                = 0.3f;
    public static final float TOOLTIP_FONT_SCALE                = 0.7f;
    public static final float TOOLTIP_SHOW_DURATION             = 3f;
    public static final Color TOOLTIP_BG_COLOR                  = Color.BLACK;
    public static final Color TOOLTIP_TEXT_COLOR                = Color.WHITE;
    public static final boolean TOOLTIP_TEXT_USE_SHADOW_FONT    = false;

/**************************************************************************************************************************************************************************/

    //Game screen margins

    //Specifies margin top. Wide screen is typically a tablet.
    //The numbers below are multiplied by the height of coin view.
    public static final float MARGIN_TOP_WIDE_SCREEN = 1.8f;
    public static final float MARGIN_TOP_NORMAL_SCREEN = 2.0f;

    //The numbers below are multiplied by the height of dial.
    public static final float MARGIN_BOTTOM_WIDE_SCREEN = 0.05f;
    public static final float MARGIN_BOTTOM_NORMAL_SCREEN = 0.1f;

    //The following number is multiplied by the width of the screen
    public static final float LEFT_AND_RIGHT_MARGIN = 0.03f;


}
