package basicmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import basicmod.powers.AcupressurePainPower;

public class TriggerAcupressurePainAction extends AbstractGameAction {

    public TriggerAcupressurePainAction(AbstractCreature target) {
        this.target = target;
    }

    @Override
    public void update() {
        if (this.target.hasPower(AcupressurePainPower.POWER_ID)) {
            addToBot(new LoseHPAction(this.target, this.source,
                    this.target.getPower(AcupressurePainPower.POWER_ID).amount));
        }
        this.isDone = true;
    }

}
