package basicmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.MyOwnSwordmanMod.makeID;

public class AcupressureDeathPower extends BasePower {
    public static final String NAME = AcupressureDeathPower.class.getSimpleName();
    public static final String POWER_ID = makeID(NAME);
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public AcupressureDeathPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public String getUpdatedDescription() {
        return this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atEndOfRound() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 10) {
            addToTop((AbstractGameAction) new InstantKillAction(this.owner));
            this.amount -= 10;
            if (this.amount <= 0)
                addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
}
