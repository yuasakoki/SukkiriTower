package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CSV{
	private final String URL = "jdbc:postgresql://localhost:5432/monster";
	private final String USER = "postgres";
	private final String PASSWORD = "test";


	public void outputCSVData() {

		List<String> csvData = new ArrayList<String>();

		FileWriter fw = null;
		BufferedWriter bw = null;

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
	         rs = st.executeQuery("SELECT * FROM monster");

	         /* 5) 結果からCSV形式データを作成 */
	         csvData = makeCSVData(rs);


	         try {

	        	 fw = new FileWriter("C:\\Users\\user\\Desktop\\monster.csv");
	        	 bw = new BufferedWriter(fw);

	        	 // タイトル行書出し
//	 			bw.write("商品ID,商品名,商品分類,販売単価,仕入単価");
//	        	 bw.newLine();

	        	 // データ行書出し
	        	 for(int i = 0; i < csvData.size(); i++) {
	        		 bw.write(csvData.get(i));

	        		 // 最終行でなければ改行を行う
	        		 if(csvData.size() - 1 != i) {
	        			 bw.newLine();
	        		 }
	        	 }
	        	 bw.flush();

//	        	 System.out.println("出力処理が完了しました。");
	         } catch(Exception e) {
	        	 System.out.println("CSV出力処理中にエラーが発生しました。");
	        	 e.printStackTrace();
	         } finally {
	        	 if(fw != null) {
	        		 try {
	        			 fw.close();
	        		 } catch (Exception e) {}
	        	 }

	        	 if(bw != null) {
	        		 try {
	        			 bw.close();
	        		 } catch (Exception e) {}
	        	 }
	         }


	         /* 6) PostgreSQLとの接続を切断 */
	         rs.close();
	         st.close();
	         con.close();


		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
	}




	 public List<String> makeCSVData(ResultSet rs) throws Exception {
		List<String> csvData = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		while(rs.next()) {
			sb = new StringBuilder();

		    sb.append(rs.getString("monster_no"));
		    sb.append(",");
		    sb.append(rs.getString("name"));
		    sb.append(",");
		    sb.append(rs.getString("hp"));
		    sb.append(",");
		    sb.append(rs.getString("mp"));
		    sb.append(",");
		    sb.append(rs.getString("power"));
		    sb.append(",");
		    sb.append(rs.getString("defense"));
		    sb.append(",");
		    sb.append(rs.getString("special_power"));

		    csvData.add(sb.toString());
		}

		return csvData;
	 }






	 public void inportCSVData(String filePath) {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			Connection con;
			PreparedStatement st;

			/* 2) JDBCドライバの定義 */
			Class.forName("org.postgresql.Driver");

			/* 3) PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			/* 4) SQL文の準備 */
			String sql = "";
			sql = "DELETE FROM monster";

			st = con.prepareStatement(sql);
			
			/* 5) SQL文の実行 */
			st.executeUpdate();
			
			/* 6) PostgreSQLとの接続を切断 */
			st.close();
			con.close();

			

			String lineData = br.readLine();	// 2行目からがデータ行（取込対象）

			// 取込処理
			while (lineData != null) {
				System.out.println("【取込データ】" + lineData);
				String[] monster = lineData.split(",");
				insertMonster(Integer.parseInt(monster[0]),
						monster[1],
						Integer.parseInt(monster[2]),
						Integer.parseInt(monster[3]),
						Integer.parseInt(monster[4]),
						Integer.parseInt(monster[5]),
						Integer.parseInt(monster[6]));

				lineData = br.readLine();
			}

			System.out.println("取込処理が完了しました。");
			System.out.println("");
		} catch(Exception e) {
			System.out.println("取込処理中にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (Exception e) {}
			}

			if(br != null) {
				try {
					br.close();
				} catch (Exception e) {}
			}
		}
	}
	 public void insertMonster(int monster_no,String name,int hp,int mp,int power,int defense,int special_power) {

			try {
			/* 1) PostgreSQLへの接続情報 */
		Connection con;
		PreparedStatement st;

		/* 2) JDBCドライバの定義 */
		Class.forName("org.postgresql.Driver");

		/* 3) PostgreSQLへの接続 */
		con = DriverManager.getConnection(URL, USER, PASSWORD);

		/* 4) SQL文の準備 */
		String sql = "";
		sql = "INSERT INTO monster(monster_no, name, hp, mp, power, defense, special_power) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?);";

		st = con.prepareStatement(sql);
		st.setInt(1, monster_no);
		st.setString(2, name);
		st.setInt(3, hp);
		st.setInt(4, mp);
		st.setInt(5, power);
		st.setInt(6, defense);
		st.setInt(7, special_power);

		/* 5) SQL文の実行 */
		st.executeUpdate();

		/* 6) PostgreSQLとの接続を切断 */
		st.close();
		con.close();

		} catch(Exception e) {
		System.out.println("DBアクセス時にエラーが発生しました。");
		e.printStackTrace();
		}
	 }

}
//"C:\\Users\\user\\Desktop\\monster.csv"