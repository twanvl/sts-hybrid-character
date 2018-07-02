package hybridcharacter;

import java.nio.charset.StandardCharsets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hybridcharacter.characters.HybridCharacter;
import hybridcharacter.patches.PlayerClassEnum;
import hybridcharacter.patches.CardColorEnum;
import hybridcharacter.relics.*;

@SpireInitializer
public class HybridCharacterMod implements
        PostInitializeSubscriber,
        EditRelicsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber {
    public static final String MODNAME = "Hybrid Character";
    public static final String AUTHOR = "twanvl";
    public static final String DESCRIPTION = "v0.1.4\nAdds an Ironclad/Silent hybrid character with a combined card pool.";

    // card trail effect
    private static final Color RED_GREEN = new Color(0.7f, 0.7f, 0.1f, 1.0f);
    //private static final Color RED_GREEN = new Color(0.3f, 0.3f, 0.15f, 1.0f);

	public static final Logger logger = LogManager.getLogger(HybridCharacterMod.class.getName());

    public HybridCharacterMod() {
        logger.info("Initialize hybrid character mod.");
        BaseMod.subscribeToPostInitialize(this);
        BaseMod.subscribeToEditStrings(this);
        BaseMod.subscribeToEditCharacters(this);
        BaseMod.subscribeToEditRelics(this);
        receiveEditColors();
    }

    public static void initialize() {
        new HybridCharacterMod();
    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture("images/HybridCharacterBadge.png");
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings.", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    public void receiveEditColors() {
        BaseMod.addColor(
            CardColorEnum.HYBRID_COLOR.toString(),
            RED_GREEN, RED_GREEN, RED_GREEN, RED_GREEN, RED_GREEN, RED_GREEN, RED_GREEN,
            "images/cardui/512/bg_attack_hybrid.png",
            "images/cardui/512/bg_skill_hybrid.png",
            "images/cardui/512/bg_power_hybrid.png",
            "images/cardui/512/card_hybrid_orb.png",
            "images/cardui/1024/bg_attack_hybrid.png",
            "images/cardui/1024/bg_skill_hybrid.png",
            "images/cardui/1024/bg_power_hybrid.png",
            "images/cardui/1024/card_hybrid_orb.png");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(
            HybridCharacter.class,
            "The Hybrid",//HybridCharacter.NAMES[1],
            "Hybrid class string",
            CardColorEnum.HYBRID_COLOR.toString(),
            "Hybrid",//HybridCharacter.NAMES[0],
            "images/charSelect/hybridButton.png",
            "images/charSelect/hybridPortrait.jpg",
            PlayerClassEnum.HYBRID.toString());
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new BurningSnakeRing(), CardColorEnum.HYBRID_COLOR.toString());
        BaseMod.addRelicToCustomPool(new BlackendSnakeRing(), CardColorEnum.HYBRID_COLOR.toString());
    }

    @Override
    public void receiveEditStrings() {
        // Note: it seems that naming the files localization/eng/relics.json crashes slay the spire on startup
        loadCustomStringsFile(RelicStrings.class, "localization/eng/hybrid-relics.json");
        loadCustomStringsFile(CharacterStrings.class, "localization/eng/hybrid-character.json");
    }
    public void loadCustomStringsFile(@SuppressWarnings("rawtypes") Class stringType, String file) {
        String strings = Gdx.files.internal(file).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(stringType, strings);
    }
}
