package hybridcharacter.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import basemod.abstracts.CustomRelic;

public class BlackendSnakeRing extends CustomRelic {
    public static final String ID = "Blackend Snake Ring";
    private static final int HEALTH_AMT = 6;
    private static final int NUM_CARDS = 2;

    public BlackendSnakeRing() {
        super(ID, new Texture("images/relics/blackendSnakeRing.png"), AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_CARDS + DESCRIPTIONS[1] + HEALTH_AMT + DESCRIPTIONS[2];
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, NUM_CARDS));
    }
    
    @Override
    public void onVictory() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(HEALTH_AMT);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackendSnakeRing();
    }
}
