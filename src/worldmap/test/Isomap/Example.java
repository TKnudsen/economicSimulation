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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.Timer;

import datastores.CachingDataStore;
import datastores.DataStore;
import datastores.DataStoreFactory;

/**
 * TODO Type description
 * @author Martin Steiger
 */
public class Example
{
	// final static Logger log = LoggerFactory.getLogger(Example.class);

	public static void main(String[] args) throws Exception
	{
		DataStore webDataStore = DataStoreFactory.getWebDao(new URL("http://msteiger.bplaced.net/bfw/data/core/images/terrain"));
		DataStore localDataStore = DataStoreFactory.createLocalDao(Paths.get("data/wesnoth/images/terrain"));
		
		DataStore cachingDataStore = new CachingDataStore(webDataStore, localDataStore);
		
		final ExampleComponent comp = new ExampleComponent(cachingDataStore);

		Timer timer = new Timer(100, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				comp.animate();
			}
		});
		
		timer.start();
		
		JFrame frame = new JFrame();
		frame.setTitle("Simple example");
		frame.setSize(950, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(comp);
		frame.setVisible(true);
	}
}
