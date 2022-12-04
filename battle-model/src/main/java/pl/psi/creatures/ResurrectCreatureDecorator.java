package pl.psi.creatures;

import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResurrectCreatureDecorator extends AbstractCreature {

    private final Creature decorated;
    private boolean resurrected = false;

    public ResurrectCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    public void resurrect(Creature creature) {
        if (!resurrected) {
            creature.resurrect();
            resurrected = true;
        }
    }

    public boolean canResurrect() {
        return !resurrected;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (TurnQueue.NEW_TURN.equals(evt.getPropertyName())) {
            List<Creature> creatureList = (List<Creature>) evt.getNewValue();

            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(creature -> !creature.isAlive())
                    .findAny()
                    .ifPresent(creature -> resurrect(creature));
        }
    }
}
