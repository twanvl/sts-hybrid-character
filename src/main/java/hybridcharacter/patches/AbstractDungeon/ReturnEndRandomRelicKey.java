package hybridcharacter.patches.AbstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(clz=com.megacrit.cardcrawl.dungeons.AbstractDungeon.class, method="returnEndRandomRelicKey")
public class ReturnEndRandomRelicKey {
    public static String Postfix(String retVal, Object tier) {
        if (retVal.equals("Blackend Snake Ring") && !AbstractDungeon.player.hasRelic("Burning Snake Ring")) {
            return AbstractDungeon.returnEndRandomRelicKey((AbstractRelic.RelicTier)tier);
        }
        return retVal;
    }
}

