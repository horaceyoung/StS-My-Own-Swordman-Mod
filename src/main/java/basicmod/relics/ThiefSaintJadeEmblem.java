package basicmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.monsters.exordium.Looter;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SpeechTextEffect;

import basicmod.character.ThiefSaint;
import static basicmod.MyOwnSwordmanMod.makeID;

public class ThiefSaintJadeEmblem extends BaseRelic {
    private static final String NAME = "ThiefSaintJadeEmblem"; // The name will be used for determining the image file
                                                               // as well as the ID.
    public static final String ID = makeID(NAME); // This adds the mod's prefix to the relic ID, resulting in
                                                  // modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; // The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; // The sound played when the relic is clicked.

    private static final int STRENGTH = 10; // For convenience of changing it later and clearly knowing what the number
                                            // means instead of just having a 10 sitting around in the code.

    public ThiefSaintJadeEmblem() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (mo instanceof Looter) {
                addToBot((AbstractGameAction) new EscapeAction(mo));
                AbstractDungeon.actionManager.addToBottom(
                        (AbstractGameAction) new SetMoveAction(mo, (byte) 99, AbstractMonster.Intent.ESCAPE));
            }
        }
    }

    @Override
    public void onEquip() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (mo instanceof Looter) {
                addToBot((AbstractGameAction) new EscapeAction(mo));
                AbstractDungeon.actionManager.addToBottom(
                        (AbstractGameAction) new SetMoveAction(mo, (byte) 99, AbstractMonster.Intent.ESCAPE));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
