package pl.psi.creatures;

import java.util.Random;

public class BonusDamageCalculator extends AbstractCalculateDamageStrategy {
    double damageFactor;

    protected BonusDamageCalculator(double percent) {
        super(new Random());
        damageFactor = 1 + percent;
    }

    @Override
    public int calculateDamage(final Creature aAttacker, final Creature aDefender) {
        AbstractCalculateDamageStrategy defaultCalculator = new DefaultDamageCalculator(new Random());
        return (int) (defaultCalculator.calculateDamage(aAttacker, aDefender) * damageFactor);
    }
}
