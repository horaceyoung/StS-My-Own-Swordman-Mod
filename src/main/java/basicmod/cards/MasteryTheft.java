package basicmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.actions.MasteryTheftAction;
import basicmod.character.ThiefSaint;
import basicmod.util.CardStats;

public class MasteryTheft extends BaseCard {
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 1;

    public static final String ID = makeID(MasteryTheft.class.getSimpleName());
    private static final CardStats info = new CardStats(
            ThiefSaint.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY, 0);

    public MasteryTheft() {
        super(ID, info);
        this.exhaust = true;
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new MasteryTheftAction((AbstractCreature) m,
                new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }

    public AbstractCard makeCopy() {
        return new MasteryTheft();
    }
}
