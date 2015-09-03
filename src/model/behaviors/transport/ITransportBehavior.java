package model.behaviors.transport;

import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;

public interface ITransportBehavior {

	public boolean checkTransport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException;

	public void transport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException;
}
