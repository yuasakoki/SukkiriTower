package test;

import java.util.Random;

public class Hero {
	AppGameProperties a = new AppGameProperties();
	String name;
	int maxHp;
	int hp;
	int maxMp;
	int mp;
	int power;
	int defense;
	int special_power;
	int exp;
	int level;

	int levelUp = 100;
	int item = 1;

	public Hero() {
		name = a.name;
		maxHp = a.hp;
		hp = maxHp;
		maxMp = a.mp;
		mp = maxMp;
		power = a.power;
		defense = a.defense;
		special_power = a.special_power;
		exp = 0;
		level = 1;
	}

	//HPなどの表示
	public void show() {
		if(this.hp <= 0) {
			this.hp = 0;
		}
		System.out.println("[Lv:" + this.level + " " + this.name +"]" );
		System.out.print("[HP: " + this.hp + " ");
		System.out.println("MP: " + this.mp +"]---------------------------------------------------------------");

	}

	//アタックメソッド　もらう引数に応じて攻撃か必殺技か
	public int attack(int select, int mHp, int monsterDefense, String name) {
		if(select == 1) {
			System.out.println(this.name + "の攻撃！");
			int damage = this.power - monsterDefense;
			if(damage < 0) {
				damage =0;
			}

			System.out.println(name + "に" + damage +"のダメージ!");

			mHp = mHp - (this.power - monsterDefense);

			if(mHp < 0) {
				mHp = 0;
			}

		return mHp;

//		もらったセレクトが２の場合 必殺技
		}else{
			this.mp--;

			if(this.mp >= 0) {
				System.out.println(this.name + "の必殺技！");
				System.out.println(name + "に" + this.special_power +"のダメージ!");
				mHp = mHp - this.special_power;
				return mHp;

			}else {

				if(this.mp <= 0) {
					this.mp = 0;
				}
				System.out.println("mpが足りない。");
				return mHp;
			}
		}
	}


	//経験値メソッド　
	public void exp(int i) {
//		経験値の取得
		this.exp = new Random().nextInt(150) + (i * 100);
		System.out.println(this.exp +"の経験値を得た！！！");
		System.out.println("");

//		レベルアップの処理
		for(; this.levelUp < this.exp ; this.levelUp += 300) {
			this.level++;
//			ＨＰは1レベルごとに１５アップ
			System.out.println(this.name +"はレベル" + this.level + "になった！!");
			System.out.println("HPが全回復した！");
			System.out.println("HPが" + 15 + "上がった！");
			this.maxHp += 15;
			this.hp = this.maxHp;

//			ＭＰは1レベルごとに１アップ
			System.out.println("MPが全回復した！");
			System.out.println("MPが" + 1 + "上がった！");
			this.maxMp += 1;
			this.mp = this.maxMp;


//			レベルごとに攻撃力、守備力、必殺技を乱数でアップ
			int powerR = new Random().nextInt(7) + 5;
			int defenseR = new Random().nextInt(5) + 5;
			int special_powerR =  new Random().nextInt(5) + 5;

			System.out.println("攻撃力が" + powerR + "上がった！");
			this.power += powerR;
			System.out.println("防御力が" + defenseR + "上がった！");
			this.defense += defenseR;
			System.out.println("必殺技が" + special_powerR + "上がった！");
			this.special_power += special_powerR;
			System.out.println("");

		}
	}

//	回復薬を拾うメソッド
	public void itemR() {
		int r = new Random().nextInt(3);
		if(r == 1) {
			System.out.println("");
			System.out.println("★ラッキー!！ 回復薬を拾った！");
			item++;
		}

	}


//	回復薬を使うメソッド
	public void item() {
		if(item > 0 ) {
			System.out.println(this.name + "は回復薬を飲んだ。");
			System.out.println("HPを全回復した！！");
			this.hp = this.maxHp;
			item--;
		}else {
			System.out.println("探したが見つからなかった。");
		}
	}


}









