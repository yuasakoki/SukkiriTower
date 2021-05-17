package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Write {
	Calendar start = null;
	Calendar end = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 (E) HH時mm分ss秒");
	String game = "";
	int startHp = 0;
	int startMp = 0;


	public void startWrite(int Hp, int Mp) {
		start = Calendar.getInstance();
		this.startHp = Hp;
		this.startMp = Mp;
	}
	
	
	
	public void endWriter(int i) {
		end = Calendar.getInstance();


		if(i == 24) {
			this.game = "GAME CLEAR!!";

		}else {
			this.game = "GAME OVER";

		}
	}
	
	
	public void clearWrite(String name,
							int hp,
							int mp,
							int power,
							int defense,
							int special_power,
							int floor){

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("C:\\result\\today.txt");
			bw = new BufferedWriter(fw);


			bw.write("[ゲーム結果]");
			bw.newLine();
			bw.write("ゲーム開始：" + sdf.format(start.getTime()) );
			bw.newLine();
			bw.write("ゲーム終了：" + sdf.format(end.getTime()));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("結果："+ game);
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("プレイヤー情報：");
			bw.newLine();
			bw.write("（名前）"+ name);
			bw.newLine();
			bw.write("（HP）"+ startHp + "→ " + hp);
			bw.newLine();
			bw.write("（MP）"+ startMp + "→ " + mp);
			bw.newLine();
			bw.write("（攻撃力）" + power);
			bw.newLine();
			bw.write("（防御力）" + defense);
			bw.newLine();
			bw.write("（必殺技威力）" + special_power);
			bw.newLine();
			bw.write("進んだ階数：" + floor+ "階");

			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
				fw.close();
			}catch(IOException e2) {
				e2.printStackTrace();
			}

			}
	}


}
