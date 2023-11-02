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

public class TightFittingShoes extends BaseRelic {
    public static final String NAME = TightFittingShoes.class.getSimpleName();                                                               // as well as the ID.
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER; // The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; // The sound played when the relic is clicked.
    

    public TightFittingShoes() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if (damageAmount <= this.counter) {
            flash();
            return 0;
        }
        return damageAmount;
    }

    @Override
    public String getUpdatedDescription() {
        return this.description = String.format(DESCRIPTIONS[0], this.counter);
    }

    public void setCounter(int amount){
        this.counter = amount;
    }

    public void addCounter(int amount) {
        this.counter += amount;
    }
}
