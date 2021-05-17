package test;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		boolean game = true;
		while(game) {
		AppGameProperties a = new AppGameProperties();
		Write write = new Write();
		CSV c = new CSV();
		Monster monster = new Monster();
		int floor = monster.floor();
		Hero hero = new Hero();
		int monsterCount = 0;
		int i = 1 ;
		int endCount= 0;


			System.out.println("★☆スッキリタワー☆★");
			System.out.println("1:ゲームスタート");
			System.out.println("2:モンスターデータ取込");
			int select = new Scanner(System.in).nextInt();

			switch(select) {

			case 1:
				write.startWrite(hero.hp, hero.mp);

				for(; i <= floor ; i++){
					boolean loop = true;

					monster.monsters(i);
					if(monster.name == "") {
						i = floor;
						monsterCount = floor;
						loop = false;
					}else {
						System.out.println("【 " + i + " 階に到着した】");
						System.out.println(" 【"+monster.name+"】" + "があらわれた！！");
						hero.show();
					}

					while(loop) {
						System.out.print("コマンドを入力（1:攻撃 2:必殺技 3:逃げる 4:回復薬 "+ hero.item +"コ）>>");
						System.out.println("");
						int select2 = new Scanner(System.in).nextInt();

						switch(select2) {
							case 1://アタック
							case 2://スペシャルアタック
							monster.hp = hero.attack(select2, monster.hp, monster.defense, monster.name);
								break;


							//逃げる
							case 3:
								int r = new Random().nextInt(10);
								System.out.println(hero.name + "は逃げ出した！");
								if (r == 1 || r == 2) {
									System.out.println(hero.name + "はうまく逃げきれた！");
									loop = false;
									monsterCount++;
								}else {
									System.out.println(hero.name + "は逃げきれなかった。");
								}
								System.out.println("");
								break;

//							回復薬
							case 4:
								hero.item();
								break;
						}

//						モンスターのターン
						if(loop) {

							if(monster.hp <= 0) {
								System.out.println(monster.name + "をやっつけた!!!");
//								アイテムメソッド
								hero.itemR();
								System.out.println("");
//								経験値メソッド
								hero.exp(i);

								System.out.println("");
//								倒したモンスターのカウント
								monsterCount++;
								break;


							}else{
//								モンスターの行動確立
								int r = new Random().nextInt(100);

//								攻撃の確率
								if( r < a.attack) {

									System.out.println(monster.name + "の攻撃！");
									int mAttack =  (monster.power - hero.defense);
									if(mAttack <= 0) {
										mAttack = 0;
									}
									System.out.println(hero.name + "に" + mAttack + "のダメージ!");
									hero.hp = hero.hp - mAttack;

//								必殺技の確率
								}else if(r <a.attack + a.special_attack) {

									System.out.println(monster.name + "の必殺技！");
										System.out.println();
										if(monster.mp > 0) {
										int mS_Attack =  (monster.special_power);
										if(mS_Attack <= 0) {
											mS_Attack = 0;
										}

										System.out.println(hero.name + "に" + mS_Attack + "のダメージ!");
										hero.hp = hero.hp - mS_Attack;
										monster.mp--;
									}else {
										System.out.println("しかしなにも起こらなかった。");
									}
//								様子を見ている確率
								}else{
									System.out.println(monster.name + "は様子を見ている");
								}
								hero.show();
								System.out.println("");
							}
						}

//						GAME OVERの処理
						if(hero.hp <= 0) {
							System.out.println(hero.name + "は死んでしまった...");
							System.out.println("--GAME OVER--");
							loop = false;
							endCount = i;
							i = floor;
						}

					}

//					GAME CREARの処理
					if(monsterCount == floor){
						System.out.println("");
						System.out.println("おめでとう! クリアです!");
						System.out.println("---END---");
						endCount = i;
					}
				}

//				記録メソッド
//				終了時間の取得
				write.endWriter(i);
//				結果の記録
				write.clearWrite(hero.name, hero.hp, hero.mp, hero.power, hero.defense, hero.special_power,endCount);
				break;


			case 2:
				//取込処理
				System.out.println("取込むCSVファイルのパスを入力してください。C:\\Users\\user\\Desktop\\monster.csv");
				String add = new Scanner(System.in).nextLine();
//				アドレスを渡す
				c.inportCSVData(add);
				break;
			}

		}
	}
}