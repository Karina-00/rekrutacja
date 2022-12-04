package pl.psi.creatures;

import pl.psi.Point;

import java.beans.PropertyChangeEvent;

public class BonusDamageDependingOnDistanceCreatureDecorator extends AbstractCreature {
    private final Creature decorated;
    private int lastDistanceTraveled = 0;
    private BonusDamageCalculator bonusDamageCalculator;
    private final double BONUS_DAMAGE_PER_DISTANCE_UNIT = 0.05;

    public BonusDamageDependingOnDistanceCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void move(Point newPoint) {
        lastDistanceTraveled += 1;
        decorated.move(newPoint);
    }

    @Override
    public void attack(final Creature aDefender) {
        bonusDamageCalculator = new BonusDamageCalculator(BONUS_DAMAGE_PER_DISTANCE_UNIT * lastDistanceTraveled);
        decorated.setCalculator(bonusDamageCalculator);
        decorated.attack(aDefender);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
        lastDistanceTraveled = 0;
    }
}
