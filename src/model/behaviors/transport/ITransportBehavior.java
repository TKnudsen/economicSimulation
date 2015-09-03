package model.behaviors.transport;

import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Good;
import data.goods.GoodStock;

public interface ITransportBehavior {

	public boolean checkTransport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException;

	public void transport(Good good, double amount, GoodStock source, GoodStock target) throws CapacityUnderflowException, CapacityOverflowException;
}
