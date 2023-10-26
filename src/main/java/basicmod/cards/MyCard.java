package basicmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basicmod.character.ThiefSaint;
import basicmod.powers.AcupressureWeakPower;
import basicmod.util.CardStats;

public class MyCard extends BaseCard {
    public static final String ID = makeID(MyCard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            ThiefSaint.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY, 0);

    private static final int DAMAGE = 50;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 100;
    private static final int UPG_BLOCK = 2;

    public MyCard() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p,
                    (AbstractPower) new AcupressureWeakPower((AbstractCreature) mo, -this.magicNumber), -this.magicNumber,
                    true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
