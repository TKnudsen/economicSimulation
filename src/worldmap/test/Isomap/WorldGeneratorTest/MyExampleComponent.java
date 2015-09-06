package worldmap.test.Isomap.WorldGeneratorTest;

import input.TilemapMouseAdapter;
import input.ViewportMouseAdapter;
import io.TerrainLoader;
import io.TileSetBuilderWesnoth;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.geom.AffineTransform;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JComponent;

import terrain.HexTerrainModel;
import terrain.TerrainType;
import terrain.Tile;
import tiles.HexTileSet;
import view.TileRendererCursor;
import view.TileRendererDefault;
import view.TileRendererGrid;
import view.Viewport;
import worldmap.WorldGenerator;
import common.GridData;
import common.RepaintingObserver;
import common.SelectionModel;
import datastores.DataStore;

/**
 * A simple component that loads the terrain and the tileset, creates a viewport
 * and renderer and attaches mouse selection listener
 * 
 * @author Martin Steiger
 */
public class MyExampleComponent extends JComponent {
	private static final long serialVersionUID = 6701940511292511047L;

	private TileRendererDefault tileRendererDefault;
	private TileRendererGrid tileRendererGrid;
	private TileRendererCursor tileRendererCursor;

	private HexTerrainModel terrainModel;
	private HexTileSet tileset;

	private Viewport view = new Viewport();
	private SelectionModel<Tile> selectionModel = new SelectionModel<Tile>();

	/**
	 * Setup everything
	 * 
	 * @throws IOException
	 *             if some data cannot be read
	 */
	public MyExampleComponent(DataStore dao) throws IOException {
		
		WorldGenerator worldGenerator = new WorldGenerator();
		GridData<TerrainType> terrainData = worldGenerator.generateTerain(80, 60);

		TileSetBuilderWesnoth tb = new TileSetBuilderWesnoth(dao);
		tileset = tb.read();

		terrainModel = new HexTerrainModel(terrainData, tileset);

		MouseAdapter ma = new ViewportMouseAdapter(view);
		view.addObserver(new RepaintingObserver(this));

		addMouseListener(ma);
		addMouseMotionListener(ma);
		addMouseWheelListener(ma);

		selectionModel.addObserver(new RepaintingObserver(this));

		tileRendererDefault = new TileRendererDefault(terrainModel, tileset);
		tileRendererGrid = new TileRendererGrid(terrainModel, tileset);
		tileRendererCursor = new TileRendererCursor(terrainModel, tileset);

		TilemapMouseAdapter tma = new TilemapMouseAdapter(view, terrainModel, selectionModel);
		addMouseMotionListener(tma);
	}

	/**
	 * Update tile animation and repaint
	 */
	public void animate() {
		// tileRendererDefault.nextFrame();

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		Rectangle visibleRect = getVisibleRect();

		// convert screen to world coordinates
		int worldX0 = view.screenXToWorldX(visibleRect.x);
		int worldY0 = view.screenYToWorldY(visibleRect.y);
		int worldX1 = view.screenXToWorldX(visibleRect.x + visibleRect.width);
		int worldY1 = view.screenYToWorldY(visibleRect.y + visibleRect.height);

		List<Tile> visibleTiles = terrainModel.getTilesInRect(worldX0, worldY0, worldX1, worldY1);

		AffineTransform oldAt = g2d.getTransform();

		AffineTransform at = new AffineTransform();
		at.translate(view.worldXToScreenX(0), view.worldYToScreenY(0));
		at.scale(view.getZoom(), view.getZoom());
		g2d.setTransform(at);

		tileRendererDefault.drawTiles(g2d, visibleTiles);
		tileRendererGrid.drawTiles(g2d, visibleTiles);
		tileRendererCursor.drawTiles(g2d, selectionModel.getSelection());

		g2d.setTransform(oldAt);
	}
}
