package hybridcharacter.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

@SpirePatch(clz = com.megacrit.cardcrawl.events.city.BackToBasics.class, method = "buttonEffect")
public class BackToBasicsPatch {

	@SpireInsertPatch(rloc = 30)
	public static void Insert(Object __obj_instance, int buttonPressed) {
		AbstractPlayer.PlayerClass selection = AbstractDungeon.player.chosenClass;
		if (!selection.toString().equals("IRONCLAD") && !selection.toString().equals("THE_SILENT")
				&& !selection.toString().equals("CROWBOT")) {
			for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
				if ((c instanceof Strike_Red || c instanceof Defend_Green) && c.canUpgrade()) {
					c.upgrade();
					AbstractDungeon.player.bottledCardUpgradeCheck(c);
					AbstractDungeon.effectList.add(
							new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * Settings.WIDTH,
							MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT));
				}
			}
		}
	}

}
