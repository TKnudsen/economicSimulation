package model.behaviors.live;

import dataStructures.building.BuildingFactory;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.exceptions.GoodStockTooSmallException;
import dataStructures.exceptions.MoneyTooSmallException;
import dataStructures.person.Person;
import dataStructures.person.SocialStatus;

public class LivingBehaviorStatusOriented implements ILivingBehavior {

	City city;

	Person person;

	public LivingBehaviorStatusOriented(City city, Person person) {
		this.city = city;
		this.person = person;
	}

	@Override
	public void execute() {

		if (person.getResidualBuilding() == null) {
			try {
				BuildingFactory.createResidualBuilding(SocialStatus.Pioneer, city, person, person.getInventory());
			} catch (GoodStockTooSmallException | MoneyTooSmallException | CapacityUnderflowException | CapacityOverflowException e) {
				e.printStackTrace();
			}
			return;
		} else if (person.getResidualBuilding() != null && !person.getResidualBuilding().getSocialStatus().equals(person.getSocialStatus())) {
			try {
				BuildingFactory.upgradeResidualBuilding(person.getResidualBuilding());
			} catch (GoodStockTooSmallException | MoneyTooSmallException | CapacityUnderflowException | CapacityOverflowException e) {
				e.printStackTrace();
			}
			return;
		}
	}
}
