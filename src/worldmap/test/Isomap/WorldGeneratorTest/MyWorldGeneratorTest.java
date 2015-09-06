package worldmap.test.Isomap.WorldGeneratorTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.Timer;

import datastores.CachingDataStore;
import datastores.DataStore;
import datastores.DataStoreFactory;

public class MyWorldGeneratorTest {

	public static void main(String[] args) throws Exception {
		DataStore webDataStore = DataStoreFactory.getWebDao(new URL("http://msteiger.bplaced.net/bfw/data/core/images/terrain"));
		DataStore localDataStore = DataStoreFactory.createLocalDao(Paths.get("data/wesnoth/images/terrain"));

		DataStore cachingDataStore = new CachingDataStore(webDataStore, localDataStore);

		final MyExampleComponent comp = new MyExampleComponent(cachingDataStore);

		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
