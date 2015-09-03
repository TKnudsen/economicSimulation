package data.goods;

public class GoodTools {
	public static double getReferencePrice(Good good) {
		switch (good) {
		case Fish:
			return 20;
		case Potato:
			return 20;
		case Corn:
			return 20;
		case Grain:
			return 10;
		case Flour:
			return 15;
		case Wood:
			return 10;
		case Lumber:
			return 25;
		case Tools:
			return 100;
		default:
			throw new IllegalArgumentException("Good not found.");
		}
	}

	public static GoodClass getGoodClass(Good good) {
		switch (good) {
		case Fish:
			return GoodClass.Food;
		case Potato:
			return GoodClass.Food;
		case Corn:
			return GoodClass.Food;
		case Grain:
			return GoodClass.RawMaterial;
		case Flour:
			return GoodClass.RawMaterial;
		case Wood:
			return GoodClass.RawMaterial;
		case Lumber:
			return GoodClass.WoodCraft;
		case Tools:
			return GoodClass.MetalCraft;
		default:
			throw new IllegalArgumentException("Good not found.");
		}
	}
}
