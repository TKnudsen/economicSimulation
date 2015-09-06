/*
 * Copyright (C) 2012-2013 Martin Steiger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package worldmap.test.Isomap;

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

import datastores.DataStore;

import terrain.HexTerrainModel;
import terrain.TerrainType;
import terrain.Tile;
import tiles.HexTileSet;
import view.TileRendererDefault;
import view.TileRendererCursor;
import view.TileRendererGrid;
import view.Viewport;

import common.GridData;
import common.RepaintingObserver;
import common.SelectionModel;

/**
 * A simple component that loads the terrain and the tileset,
 * creates a viewport and renderer and attaches mouse selection listener
 * @author Martin Steiger
 */
public class ExampleComponent extends JComponent
{
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
	 * @throws IOException if some data cannot be read
	 */
	public ExampleComponent(DataStore dao) throws IOException
	{
		InputStream terrainDataStream = new FileInputStream("data/example.txt");
		TerrainLoader terrainLoader = new TerrainLoader();
		GridData<TerrainType> terrainData = terrainLoader.load(terrainDataStream);

//		TileSetBuilder tileSetBuilder = new TileSetBuilder();
//		tileset = tileSetBuilder.readFromStream(new FileInputStream("data/treasurefleet.tsd"));

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
	public void animate()
	{
//		tileRendererDefault.nextFrame();

		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
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
