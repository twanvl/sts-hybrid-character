package hybridcharacter.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.core.Settings;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import basemod.BaseMod;
import hybridcharacter.patches.PlayerClassEnum;

// Add red and green relics to the Hybrid's relic library
public class RelicLibraryPatches {
	public static final Logger logger = LogManager.getLogger(RelicLibraryPatches.class.getName());

    @SpirePatch(cls = "com.megacrit.cardcrawl.helpers.RelicLibrary", method = "addClassSpecificRelics")
    public static class RelicLibrary {
        @SpireInsertPatch(rloc=0, localvars={"redRelics","greenRelics"})
        public static void Insert(ArrayList<AbstractRelic> relicPool, Object _redRelics, Object _greenRelics) {
            if (AbstractDungeon.player.chosenClass == PlayerClassEnum.HYBRID) {
                @SuppressWarnings("unchecked")
                HashMap<String, AbstractRelic> redRelics = (HashMap<String, AbstractRelic>)_redRelics;
                @SuppressWarnings("unchecked")
                HashMap<String, AbstractRelic> greenRelics = (HashMap<String, AbstractRelic>)_greenRelics;
                for (Map.Entry<String, AbstractRelic> r : redRelics.entrySet()) {
                    relicPool.add(r.getValue());
                    logger.info("Adding hybrid relic: " + r.getValue().name);
                }
                for (Map.Entry<String, AbstractRelic> r : greenRelics.entrySet()) {
                    relicPool.add(r.getValue());
                    logger.info("Adding hybrid relic: " + r.getValue().name);
                }
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.helpers.RelicLibrary", method="populateRelicPool")
    public static class PopulateRelicPool {
        @SpireInsertPatch(rloc=0, localvars={"redRelics","greenRelics"})
        public static void Insert(ArrayList<String> pool, AbstractRelic.RelicTier tier, AbstractPlayer.PlayerClass chosenClass, Object _redRelics, Object _greenRelics) {
            if (chosenClass == PlayerClassEnum.HYBRID) {
                logger.info("Adding hybrid relics");
                @SuppressWarnings("unchecked")
                HashMap<String, AbstractRelic> redRelics = (HashMap<String, AbstractRelic>)_redRelics;
                @SuppressWarnings("unchecked")
                HashMap<String, AbstractRelic> greenRelics = (HashMap<String, AbstractRelic>)_greenRelics;
                for (Map.Entry<String, AbstractRelic> r : redRelics.entrySet()) {
                    if (r.getValue().tier != tier || UnlockTracker.isRelicLocked(r.getKey()) && !Settings.isDailyRun) continue;
                    pool.add(r.getKey());
                    logger.info("Adding red relic to hybrid pool: " + r.getKey());
                }
                for (Map.Entry<String, AbstractRelic> r : greenRelics.entrySet()) {
                    if (r.getValue().tier != tier || UnlockTracker.isRelicLocked(r.getKey()) && !Settings.isDailyRun) continue;
                    pool.add(r.getKey());
                    logger.info("Adding green relic to hybrid pool: " + r.getKey());
                }
            }
        }
    }
    
}
