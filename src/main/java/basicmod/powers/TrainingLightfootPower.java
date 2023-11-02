package basicmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basicmod.MyOwnSwordmanMod;
import basicmod.relics.TightFittingShoes;

import static basicmod.MyOwnSwordmanMod.makeID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrainingLightfootPower extends BasePower {
    public static final String NAME = TrainingLightfootPower.class.getSimpleName();
    public static final String POWER_ID = makeID(NAME);
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public static final Logger logger = LogManager.getLogger(MyOwnSwordmanMod.modID); // Used to output to the console.

    public TrainingLightfootPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        getUpdatedDescription();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }

        return damageAmount;
    }

    @Override
    public void onVictory() {
        logger.info("Thi is run");
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.relicId.equals("MyOwnSwordman:TightFittingShoes")) {
                ((TightFittingShoes) AbstractDungeon.player.getRelic("MyOwnSwordman:TightFittingShoes")).addCounter(this.amount);
            }
        }
    }
}
