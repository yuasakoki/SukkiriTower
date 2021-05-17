package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppGameProperties {

	private static final String FILE_PATH = "c:\\config\\game.properties";

	String name = "";
	int hp = 0;
	int mp = 0;
	int power = 0;
	int defense = 0;
	int special_power = 0;
	int attack = 0;
	int special_attack = 0;
	int wait = 0;

	public AppGameProperties() {

		FileReader fr = null;

		try {
			fr = new FileReader(FILE_PATH);
			Properties p = new Properties();

			p.load(fr);
			name = p.getProperty("hero_name");
			hp = Integer.parseInt(p.getProperty("hero_hp"));
			mp = Integer.parseInt(p.getProperty("hero_mp"));
			power = Integer.parseInt(p.getProperty("hero_power"));
			defense = Integer.parseInt(p.getProperty("hero_defense"));
			special_power = Integer.parseInt(p.getProperty("hero_special_power"));

			attack = Integer.parseInt(p.getProperty("action_attack"));
			special_attack = Integer.parseInt(p.getProperty("action_special_attack"));
			wait = Integer.parseInt(p.getProperty("action_wait"));

		} catch (Exception e) {
			System.out.println("プロパティ情報の取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {}
			}
		}
	}
}
