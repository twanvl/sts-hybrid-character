package hybridcharacter.patches.BossRelicSelectionScreen;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(clz=com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen.class, method="relicObtainLogic")
public class RelicObtainLogic {
    public static void Postfix(Object _self, Object _r) {
        AbstractRelic r = (AbstractRelic)_r;
        if (r.relicId.equals("Blackend Snake Ring")) {
            r.instantObtain(AbstractDungeon.player, 0, false);
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25f;
        }
    }
}

