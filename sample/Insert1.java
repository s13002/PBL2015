import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;


public class Insert1 {
	private String _user = "pbl";				// ユーザ名
	private String _pass = "pbl";				// パスワード
	private String _host = "localhost"; // サーバ名
	private String _sid = "pbl";				// データベース名
	private static int count = 0; // 件数をカウント	
	
	public static void main(String[] args){
		Scanner stdIn = new Scanner(System.in);

		Insert1 sample = new Insert1();
		try {

			sample.select();
			System.out.println();

			sample.insert();

			/*
			System.out.print("社員番号：");
			int empno = stdIn.nextInt();
			System.out.println();

			sample.update(empno);

			System.out.print("削除しますか(yes/no)");
			String delete_kakunin = stdIn.next();
			System.out.println();
			if(delete_kakunin.equals("yes")){
				if(count != 0){
					System.out.println("部下がいる場合は削除できません");
				}else{
					sample.delete(empno);
				}
			}
			*/

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void select() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		  Class.forName("org.gjt.mm.mysql.Driver"); // ドライバの登録
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + _host + "/" + _sid, _user, _pass); // 接続
			
			String sql = "select e.empno, e.ename, e.job, m.ename, dname, loc ";
			sql += "from employees e left outer join employees m on e.mgr = m.empno ";
			sql += "join departments d  on e.deptno = d.deptno ";
			sql += "order by e.empno";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			
			System.out.printf("%2s %-3s %2s %2s %-4s %s\n", "番号", "名前", "職種", "上司", "部門", "場所");
			System.out.println("------------------------------");

			while(rs.next()){
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				String mgr = rs.getString(4);
				String dname = rs.getString(5);
				String loc = rs.getString(6);

				System.out.printf("%2s %-3s %2s %2s %-4s %s\n", empno, ename, job, mgr, dname, loc);
			}
		}catch(ClassNotFoundException e){
			throw e;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}finally{
			if(conn != null){
				conn.close();
			}
			if(ps != null){
				ps.close();
				ps = null;
			}
			if(rs != null){
				rs.close();
				rs = null;
			}
		}
	}

	private void insert() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // ドライバ登録
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + _host + "/" + _sid + "?characterEncoding=utf8"
						, _user, _pass); // 接続
			
			String sql = "select max(empno) from employees";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			String empno_str = "";
			while(rs.next()){
				empno_str = rs.getString(1);
			}
			int empno = Integer.parseInt(empno_str);

			empno++;
			int count = 1;

			System.out.print("社員名：");
			String ename = reader.readLine();
			if(ename.length()==0){
				ename = null;
			}
			System.out.print("ローマ字：");
			String yomi = reader.readLine();
			if(yomi.length()==0){
				yomi = null;
			}
			System.out.print("職種：");
			String job = reader.readLine();
			if(job.length()==0){
				job = null;
			}
			System.out.print("上司の社員番号：");
			String mgr = reader.readLine();
			if(mgr.length()==0){
				mgr = null;
			}
			System.out.print("入社日：");
			String hiredate = reader.readLine();
			if(hiredate.length()==0){
				hiredate = null;
			}
			System.out.print("給与：");
			String sal = reader.readLine();
			if(sal.length()==0){
				sal = null;
			}
			System.out.print("歩合：");
			String comm = reader.readLine();
			if(comm.length()==0){
				comm = null;
			}
			System.out.print("部門番号：");
			String deptno = reader.readLine();
			if(deptno.length()==0){
				deptno = null;
			}

			sql = "insert into employees(empno, ename, yomi, job, mgr ,";
			sql += "hiredate, sal, comm, deptno) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, empno);
			ps.setString(2, ename);
			ps.setString(3, yomi);
			ps.setString(4, job);
			ps.setString(5, mgr);
			ps.setString(6, hiredate);
			ps.setString(7, sal);
			ps.setString(8, comm);
			ps.setString(9, deptno);

			int num = ps.executeUpdate();



		}catch(ClassNotFoundException e){
			throw e;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}finally{
			if(conn != null){
				conn.close();
			}
			if(ps != null){
				ps.close();
				ps = null;
			}
			if(rs != null){
				rs.close();
				rs = null;
			}
		}
	}
}
