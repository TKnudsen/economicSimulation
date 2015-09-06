package worldmap;

import terrain.TerrainType;
import worldmap.terasology.procedural.BrownianNoise2D;
import worldmap.terasology.procedural.Noise2D;
import worldmap.terasology.procedural.SimplexNoise;

import common.GridData;

public class WorldGenerator {

	Noise2D terrainNoise = null;

	public GridData<TerrainType> generateTerain(int mapWidth, int mapHeight) {

		init((long) (Math.random() * 1000000L));

		GridData<TerrainType> terrain = new GridData<TerrainType>(mapWidth, mapHeight, TerrainType.UNDEFINED);

		float maxHeight = Float.MIN_VALUE;
		for (int y = 0; y < mapHeight; y++)
			for (int x = 0; x < mapWidth; x++)
				maxHeight = Math.max(maxHeight, getHeightAt(y, x));

		for (int y = 0; y < mapHeight; y++) {

			for (int x = 0; x < mapWidth; x++) {

				float height = getHeightAt(y, x);
				// System.out.println(height+" ");
				TerrainType type = getTerrainType(height, maxHeight, maxHeight * 0.33f, y, mapHeight);

				terrain.setData(x, y, type);
			}
		}

		return terrain;
	}

	private void init(long seed) {
		terrainNoise = new BrownianNoise2D(new SimplexNoise(seed), 6);
	}

	float getHeightAt(int x, int z) {
		return 7 + (terrainNoise.noise(x / 1000f, z / 1000f) * 8f);
	}

	private TerrainType getTerrainType(float height, float maxHeight, float seaLevel, int z, int maxZ) {
		float zz = z / (float) maxZ;

		if (zz > 0.5)
			zz = 1 - zz;
		zz *= 2; // polar = 0, equator = 1;

		// humidity is max at 0°, almost zero at 27° and then relaxes until 90°
		float humidity = (float) (7.595 * zz * zz * zz - 10.35 * zz * zz + 3.407 * zz + 0.259);

		float temperature = zz;

		if (height <= seaLevel) {
			return TerrainType.WATER;
		} else if (temperature <= 0.10f) {
			return TerrainType.ICE;
		} else if (maxHeight * 0.94 < height) {
			return TerrainType.MOUNTAIN;
		} else if (maxHeight * 0.88 < height) {
			return TerrainType.HILL;
		} else if (temperature >= 0.85f && humidity < 0.15f) {
			return TerrainType.DESERT;
		} else if (temperature >= 0.6f && humidity > 0.6f) {
			return TerrainType.FOREST; // Tropical
		} else if (height <= seaLevel + 1) {
			return TerrainType.SAND;
		} else if (height <= seaLevel + 2) {
			return TerrainType.GRASS;
		} else if (humidity >= 0.1f && humidity <= 0.6f && temperature >= 0.5f) {
			return TerrainType.PLAINS;// Plains
		} else if (temperature <= 0.3f && humidity > 0.5f) {
			return TerrainType.FOREST;// Snow
		} else if (humidity >= 0.2f && humidity <= 0.6f && temperature < 0.5f) {
			return TerrainType.HILL;
		} else {
			return TerrainType.UNDEFINED;
		}
	}
}
