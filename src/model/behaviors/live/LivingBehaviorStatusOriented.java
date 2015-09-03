package model.behaviors.live;

import data.building.BuildingFactory;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.exceptions.GoodStockTooSmallException;
import data.exceptions.MoneyTooSmallException;
import data.person.Person;
import data.person.SocialStatus;

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
