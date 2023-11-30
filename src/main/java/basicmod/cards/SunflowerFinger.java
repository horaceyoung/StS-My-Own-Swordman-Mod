package basicmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.actions.TriggerAcupressurePainAction;
import basicmod.character.ThiefSaint;
import basicmod.powers.AcupressureDeathPower;
import basicmod.powers.AcupressurePainPower;
import basicmod.powers.AcupressureWeakPower;
import basicmod.util.CardStats;

public class SunflowerFinger extends BaseCard {
    public static final String ID = makeID(SunflowerFinger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            ThiefSaint.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY, 0);

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private int ACUPRESSURE_STACK = 1;

    public SunflowerFinger() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (!m.isDying) {
            int randomAcupressureIndex = AbstractDungeon.miscRng.random(0, 2);

                switch (randomAcupressureIndex) {
                    case 0:
                        addToBot(new ApplyPowerAction(m, p,
                                new AcupressureWeakPower(m, ACUPRESSURE_STACK), ACUPRESSURE_STACK,
                                true, AbstractGameAction.AttackEffect.NONE));
                        break;
                    case 1:
                        addToBot(new ApplyPowerAction(m, p,
                                new AcupressurePainPower(m, ACUPRESSURE_STACK), ACUPRESSURE_STACK,
                                true, AbstractGameAction.AttackEffect.NONE));
                        addToBot(new TriggerAcupressurePainAction(m));
                        break;
                    case 2:
                        addToBot(new ApplyPowerAction(m, p,
                                new AcupressureDeathPower(m, ACUPRESSURE_STACK), ACUPRESSURE_STACK,
                                true, AbstractGameAction.AttackEffect.NONE));
                        break;
                
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            this.ACUPRESSURE_STACK = 2;
        }
    }

    public AbstractCard makeCopy() {
        return new SunflowerFinger();
    }

}