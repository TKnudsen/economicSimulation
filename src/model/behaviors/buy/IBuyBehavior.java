package model.behaviors.buy;

import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;

public interface IBuyBehavior {

	public void execute() throws CapacityUnderflowException, CapacityOverflowException;
}
