package pl.psi.creatures;

public class BonusDamageForSkeletonsCreatureDecorator extends AbstractCreature {
    private final Creature decorated;
    private final double BONUS_DAMAGE_FOR_SKELETONS = 0.5;
    private BonusDamageCalculator bonusDamageCalculator = new BonusDamageCalculator(BONUS_DAMAGE_FOR_SKELETONS);
    private final DamageCalculatorIf defaultDamageCalculator;

    public BonusDamageForSkeletonsCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
        defaultDamageCalculator = decorated.getCalculator();
    }

    @Override
    public void attack(final Creature aDefender) {
        if (aDefender.getName() == CreatureStatistic.SKELETON.getName()) {
            attackSkeleton(aDefender);
        } else {
            decorated.attack(aDefender);
        }
    }

    private void attackSkeleton(final Creature aDefender) {
        decorated.setCalculator(bonusDamageCalculator);
        decorated.attack(aDefender);
        decorated.setCalculator(defaultDamageCalculator);
    }
}
