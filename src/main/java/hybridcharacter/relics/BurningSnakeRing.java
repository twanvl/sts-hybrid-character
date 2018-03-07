package hybridcharacter.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import basemod.abstracts.CustomRelic;

public class BurningSnakeRing extends CustomRelic {
    public static final String ID = "Burning Snake Ring";
    private static final int HEALTH_AMT = 3;
    private static final int NUM_CARDS = 1;
  
    public BurningSnakeRing() {
      super(ID, new Texture("images/relics/burningSnakeRing.png"), new Texture("images/relics/outline/burningSnakeRing.png"), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEALTH_AMT + DESCRIPTIONS[1];
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
        return new BurningSnakeRing();
    }
}


