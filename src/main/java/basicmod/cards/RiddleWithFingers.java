package basicmod.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.MyOwnSwordmanMod;
import basicmod.actions.TriggerAcupressurePainAction;
import basicmod.character.ThiefSaint;
import basicmod.powers.AcupressureDeathPower;
import basicmod.powers.AcupressurePainPower;
import basicmod.powers.AcupressureWeakPower;
import basicmod.util.CardStats;

public class RiddleWithFingers extends BaseCard {
    public static final Logger logger = LogManager.getLogger(MyOwnSwordmanMod.modID); // Used to output to the console.
    public static final String ID = makeID(RiddleWithFingers.class.getSimpleName());
    private static final CardStats info = new CardStats(
            ThiefSaint.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY, 2);

    private static final int DAMAGE = 4;

    public RiddleWithFingers() {
        super(ID, info);
        setDamage(DAMAGE);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();

                return;
            }
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                    AbstractDungeon.cardRandomRng);
            if (!randomMonster.isDying) {
                int randomAcupressureIndex = AbstractDungeon.miscRng.random(0, 2);

                switch (randomAcupressureIndex) {
                    case 0:
                        addToBot(new ApplyPowerAction(randomMonster, p,
                                new AcupressureWeakPower(randomMonster, 1), 1,
                                true, AbstractGameAction.AttackEffect.NONE));
                        break;
                    case 1:
                        addToBot(new ApplyPowerAction(randomMonster, p,
                                new AcupressurePainPower(randomMonster, 1), 1,
                                true, AbstractGameAction.AttackEffect.NONE));
                        addToBot(new TriggerAcupressurePainAction(randomMonster));
                        break;
                    case 2:
                        addToBot(new ApplyPowerAction(randomMonster, p,
                                new AcupressureDeathPower(randomMonster, 1), 1,
                                true, AbstractGameAction.AttackEffect.NONE));
                        break;

                }
                logger.info("TThe deal damage part is run");
                addToBot(new DamageAction(randomMonster,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                       AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                addToBot((AbstractGameAction)new WaitAction(0.1F));
            }

        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new RiddleWithFingers();
    }
}
