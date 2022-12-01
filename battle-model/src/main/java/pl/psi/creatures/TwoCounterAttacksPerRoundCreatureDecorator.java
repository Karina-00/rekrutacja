package pl.psi.creatures;

import java.beans.PropertyChangeEvent;

public class TwoCounterAttacksPerRoundCreatureDecorator extends AbstractCreature {

    private Creature decorated;
    private final int maxCounterAttacks = 2;
    private int counterAttackCounter;

    public TwoCounterAttacksPerRoundCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
        counterAttackCounter = maxCounterAttacks;
    }

    @Override
    public void setCanCounterAttack(final boolean value) {
        if (value) {
            counterAttackCounter = maxCounterAttacks;
        } else {
            counterAttackCounter -= 1;
        }
    }

    @Override
    public boolean getCanCounterAttack() {
        System.out.println(counterAttackCounter);
        return counterAttackCounter > 0;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
        setCanCounterAttack(true);
    }
}
