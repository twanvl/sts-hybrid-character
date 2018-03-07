package hybridcharacter.characters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.characters.TheSilent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import basemod.abstracts.CustomPlayer;
import java.util.ArrayList;
import hybridcharacter.patches.Enums;

//public class HybridCharacter extends CustomPlayer {
public class HybridCharacter extends AbstractPlayer {
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Hybrid");
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    public static final int START_HP = (Ironclad.START_HP + TheSilent.START_HP) / 2;
    public static final int CARD_DRAW = 5;
    public static final int ENERGY = 3;
    public static final int START_GOLD = 99;

    public HybridCharacter(String name, AbstractPlayer.PlayerClass setClass) {
        super(name, setClass);
        this.dialogX = this.drawX + 0.0f * Settings.scale;
        this.dialogY = this.drawY + 220.0f * Settings.scale;
        this.initializeClass(null,
            "images/characters/hybrid/shoulder2.png",
            "images/characters/hybrid/shoulder.png",
            "images/characters/hybrid/corpse.png",
            getLoadout(), 20.0f, -10.0f, 220.0f, 290.0f, new EnergyManager(ENERGY));
        this.loadAnimation("images/characters/hybrid/idle/skeleton.atlas", "images/characters/hybrid/idle/skeleton.json", 1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public static ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<String>();
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Bash");
        retVal.add("Neutralize");
        retVal.add("Survivor");
        return retVal;
    }

    public static ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<String>();
        retVal.add("Burning Snake Ring");
        UnlockTracker.markRelicAsSeen("Burning Snake Ring");
        return retVal;
    }

    public static CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], START_HP, START_HP, START_GOLD, CARD_DRAW, Enums.HYBRID, getStartingRelics(), getStartingDeck(), false);
    }
}

