package hybridcharacter.patches.AbstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(cls="com.megacrit.cardcrawl.dungeons.AbstractDungeon", method="returnRandomRelicKey")
public class ReturnRandomRelicKey {
    public static String Postfix(String retVal, Object tier) {
        if (retVal.equals("Blackend Snake Ring") && !AbstractDungeon.player.hasRelic("Burning Snake Ring")) {
            return AbstractDungeon.returnRandomRelicKey((AbstractRelic.RelicTier)tier);
        }
        return retVal;
    }
}

