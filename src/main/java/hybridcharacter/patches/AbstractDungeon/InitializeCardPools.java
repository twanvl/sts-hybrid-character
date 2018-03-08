package hybridcharacter.patches.AbstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import java.util.Map;
import hybridcharacter.patches.PlayerClassEnum;

@SpirePatch(cls="com.megacrit.cardcrawl.dungeons.AbstractDungeon", method="initializeCardPools")
public class InitializeCardPools {

    @SpireInsertPatch(rloc=26, localvars={"tmpPool"})
    public static void Insert(Object __obj_instance, Object tmpPoolObj) {
        @SuppressWarnings("unchecked")
        ArrayList<AbstractCard> tmpPool = (ArrayList<AbstractCard>) tmpPoolObj;
        if (AbstractDungeon.player.chosenClass == PlayerClassEnum.HYBRID) {
            // Add all red and green cards.
            // We can not just do
            //    self.addRedCards(tmpPool);
            //    self.addGreenCards(tmpPool);
            // because those methods are private :(
            AbstractCard card = null;
            for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                card = c.getValue();
                if ((card.color != AbstractCard.CardColor.RED && card.color != AbstractCard.CardColor.GREEN)
                   || card.rarity == AbstractCard.CardRarity.BASIC || UnlockTracker.isCardLocked(c.getKey()) && !Settings.isDailyRun) continue;
                tmpPool.add(card);
            }
        }
    }

}

