package basicmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AccuracyPower;

import basicmod.MyOwnSwordmanMod;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static basicmod.MyOwnSwordmanMod.makeID;

public class AcupressurePainPower extends BasePower {
    public static final Logger logger = LogManager.getLogger(MyOwnSwordmanMod.modID); // Used to output to the console.
    public static final String NAME = AcupressurePainPower.class.getSimpleName();
    public static final String POWER_ID = makeID(NAME);
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;

    private static final boolean TURN_BASED = true;
    private boolean justApplied = false;

    public AcupressurePainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        getUpdatedDescription();
    }

    public String getUpdatedDescription() {
        return this.description = DESCRIPTIONS[0];
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }
}
