package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Monster {
	int monster_no = 0;
	String name = "";
	int hp = 0;
	int mp = 0;
	int power = 0;
	int defense = 0;
	int special_power = 0;
	String monsterName = null;
	int floor = 0;

	private final String URL = "jdbc:postgresql://localhost:5432/monster";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

    public void monsters(int m) {
    	try {
	         /* 1) PostgreSQLへの接続情報 */
	         Connection con;
	         Statement st;
	         ResultSet rs;

	         /* 2) JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* 3) PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         st = con.createStatement();



	         /* 4) SQL文の実行 */
	         rs = st.executeQuery("SELECT monster_no, name, hp, mp, power, defense, special_power "
	         		+ "FROM monster WHERE monster_no = '"+ m + "' ");


	         /* 5) monsterの上書き */
	         monsterData(rs);


	         rs.close();
	         st.close();
	         con.close();
		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
   }

    public void monsterData(ResultSet rs) throws Exception {
    	while(rs.next()) {
    		monster_no = rs.getInt("monster_no");
    		name = rs.getString("name");
            hp =rs.getInt("hp");
            mp =rs.getInt("mp");
            power =rs.getInt("power");
            defense =rs.getInt("defense");
            special_power =rs.getInt("special_power");
       }
    }




//    モンスターの数によってフロア数を変える
    public int floor() {
    	try {

	         /* 1) PostgreSQLへの接続情報 */
	         Connection con;
	         Statement st;
	         ResultSet rs;

	         /* 2) JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* 3) PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         st = con.createStatement();



	         /* 4) SQL文の実行 */
	         rs = st.executeQuery("SELECT count(*)"
	         		+ "FROM monster");


	         /* 5) monsterの上書き */
//	         monsterData(rs);

	         while(rs.next()) {
	        	 floor = Integer.parseInt(rs.getString("count"));
	         }

	         rs.close();
	         st.close();
	         con.close();
		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

    	return floor;
    }











}
