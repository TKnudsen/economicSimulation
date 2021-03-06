package model.behaviors.transport;

import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;

public class TransportBehaviorInstant implements ITransportBehavior {

	@Override
	public boolean checkTransport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException {
		if (source.getStock(good) < amount)
			throw new CapacityUnderflowException(source.getStock(good) - amount);
		if (target.getRemainingCapacity() < amount)
			throw new CapacityOverflowException(target.getRemainingCapacity() - amount, target.getRemainingCapacity());
		return true;
	}

	@Override
	public void transport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException {
		if (source.getStock(good) < amount)
			throw new CapacityUnderflowException(source.getStock(good) - amount);
		if (target.getRemainingCapacity() < amount)
			throw new CapacityOverflowException(target.getRemainingCapacity() - amount, target.getRemainingCapacity());

		source.removeGood(good, amount);
		target.addGood(good, amount);
	}
}
