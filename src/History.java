import java.sql.*;

import main.entities.Jogos;
import main.utils.Utils;

public class History {

	Jogos jogo;

	private  double casaAtaque;
	private  double foraAtaque;
	private  double casaDefesa;
	private  double foraDefesa;

	public History(Jogos jogo) {
		super();
		this.jogo = jogo;
		System.out.println("CALCULANDO CASA-> "+jogo.getId().getCasa() );
		this.casaAtaque=initCasaAtaque();	
		this.casaDefesa=initCasaDefesa();
		System.out.println("CALCULANDO FORA-> "+jogo.getId().getFora() );
		this.foraAtaque=initForaAtaque();
		this.foraDefesa=initForaDefesa();
		}

	public   double initCasaAtaque() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql;
		int total = 0;
		String nome = this.jogo.getId().getCasa();
		String nomeF =  this.jogo.getId().getFora();
		// AAAAAAAAAA
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1,golos2,casa from jogos where ( casa=? or fora=? ) and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("casa").equals(nome)) {
						total += rs.getInt("golos1");
					} else {
						total += rs.getInt("golos2");
					}
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.15;
				} else {

					casaAtaque = casaAtaque + ((double)total / (double)i)* 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//BBBBBBBB
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1 from jogos where casa=?  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos1");
					
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.15;
				} else {

					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//CCCCCCCCCCCCCCCCCCC
		for (int i = 5; i <= 10; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1 from jogos where casa=? and fora=? and estado='TRMND'  order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos1");
					
				}
				rs.close();
				conn.close();
				if (i == 5 ) {
					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.10;
				} else {

					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//DDDDDDDD
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1,golos2,casa from jogos where (casa=? and fora=? ) or ( casa=? and fora=? )  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				stmt.setString(3, nomeF);
				stmt.setString(4, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("casa").equals(nome)) {
						total += rs.getInt("golos1");
					} else {
						total += rs.getInt("golos2");
					}
				}
				rs.close();
				conn.close();
				if (i == 5) {
					casaAtaque = casaAtaque + ((double)total / (double)i) *0.10;
				} else {

					casaAtaque = casaAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		System.out.println(casaAtaque);
		return casaAtaque;

	}
	public  double initCasaDefesa() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql;
		int total = 0;
		String nome = this.jogo.getId().getCasa();
		String nomeF =  this.jogo.getId().getFora();
		// AAAAAAAAAA
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select casa,golos1,golos2 from jogos where ( casa=? or fora=? ) and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("casa").equals(nome)) {
						total += rs.getInt("golos2");
					} else {
						total += rs.getInt("golos1");
					}
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.15;
				} else {

					casaDefesa = casaDefesa + ((double)total / (double)i)* 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//BBBBBBBB
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos2 from jogos where casa=?  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos2");
					
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.15;
				} else {

					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//CCCCCCCCCCCCCCCCCCC
		for (int i = 5; i <= 10; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos2 from jogos where casa=? and fora=? and estado='TRMND'  order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos2");
					
				}
				rs.close();
				conn.close();
				if (i == 5 ) {
					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.10;
				} else {

					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//DDDDDDDD
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1,golos2,casa from jogos where (casa=? and fora=? ) or ( casa=? and fora=? )  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				stmt.setString(3, nomeF);
				stmt.setString(4, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("casa").equals(nome)) {
						total += rs.getInt("golos2");
					} else {
						total += rs.getInt("golos1");
					}
				}
				rs.close();
				conn.close();
				if (i == 5) {
					casaDefesa = casaDefesa + ((double)total / (double)i) *0.10;
				} else {

					casaDefesa = casaDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}System.out.println(casaDefesa);
		return casaDefesa;

	}
	public  double initForaAtaque() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql;
		int total = 0;
		String nome =  this.jogo.getId().getCasa();
		String nomeF =  this.jogo.getId().getFora();
		// AAAAAAAAAA
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select fora,golos1,golos2 from jogos where ( casa=? or fora=? ) and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nomeF);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("fora").equals(nome)) {
						total += rs.getInt("golos2");
					} else {
						total += rs.getInt("golos1");
					}
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.15;
				} else {

					foraAtaque = foraAtaque + ((double)total / (double)i)* 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//BBBBBBBB
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos2 from jogos where fora=?  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nomeF);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos2");
					
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.15;
				} else {

					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//CCCCCCCCCCCCCCCCCCC
		for (int i = 5; i <= 10; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos2 from jogos where casa=? and fora=? and estado='TRMND'  order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos2");
					
				}
				rs.close();
				conn.close();
				if (i == 5 ) {
					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.10;
				} else {

					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//DDDDDDDD
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select fora,golos1,golos2 from jogos where (casa=? and fora=? ) or ( casa=? and fora=? )  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				stmt.setString(3, nomeF);
				stmt.setString(4, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("fora").equals(nomeF)) {
						total += rs.getInt("golos2");
					} else {
						total += rs.getInt("golos1");
					}
				}
				rs.close();
				conn.close();
				if (i == 5) {
					foraAtaque = foraAtaque + ((double)total / (double)i) *0.10;
				} else {

					foraAtaque = foraAtaque + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		System.out.println(foraAtaque);
		return foraAtaque;

	}
	public  double initForaDefesa() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql;
		int total = 0;
		String nome =  this.jogo.getId().getCasa();
		String nomeF =  this.jogo.getId().getFora();
		// AAAAAAAAAA
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select fora,golos1,golos2 from jogos where ( casa=? or fora=? ) and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nomeF);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("fora").equals(nomeF)) {
						total += rs.getInt("golos1");
					} else {
						total += rs.getInt("golos2");
					}
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.15;
				} else {

					foraDefesa = foraDefesa + ((double)total / (double)i)* 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//BBBBBBBB
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1 from jogos where fora=?  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nomeF);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos1");
					
				}
				rs.close();
				conn.close();
				if (i == 5 || i == 10) {
					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.15;
				} else {

					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		//CCCCCCCCCCCCCCCCCCC
		for (int i = 5; i <= 10; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select golos1 from jogos where casa=? and fora=? and estado='TRMND'  order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
						total += rs.getInt("golos1");
					
				}
				rs.close();
				conn.close();
				if (i == 5 ) {
					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.10;
				} else {

					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}
		//DDDDDDDD
		for (int i = 5; i <= 15; i = i + 5) {
			try {
				total = 0;
				Class.forName(Utils.JDBC_DRIVER);
				conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
				sql = "select fora,golos1,golos2 from jogos where (casa=? and fora=? ) or ( casa=? and fora=? )  and estado='TRMND' order by datareal desc limit " + i;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, nomeF);
				stmt.setString(3, nomeF);
				stmt.setString(4, nome);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("fora").equals(nomeF)) {
						total += rs.getInt("golos1");
					} else {
						total += rs.getInt("golos2");
					}
				}
				rs.close();
				conn.close();
				if (i == 5) {
					foraDefesa = foraDefesa + ((double)total / (double)i) *0.10;
				} else {

					foraDefesa = foraDefesa + ((double)total / (double)i) * 0.05;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}System.out.println(foraDefesa);
		return foraDefesa;

	}

	public Jogos getJogo() {
		return jogo;
	}

	public void setJogo(Jogos jogo) {
		this.jogo = jogo;
	}

	public double getCasaAtaque() {
		return casaAtaque;
	}

	public void setCasaAtaque(double casaAtaque) {
		this.casaAtaque = casaAtaque;
	}

	public double getForaAtaque() {
		return foraAtaque;
	}

	public void setForaAtaque(double foraAtaque) {
		this.foraAtaque = foraAtaque;
	}

	public double getCasaDefesa() {
		return casaDefesa;
	}

	public void setCasaDefesa(double casaDefesa) {
		this.casaDefesa = casaDefesa;
	}

	public double getForaDefesa() {
		return foraDefesa;
	}

	public void setForaDefesa(double foraDefesa) {
		this.foraDefesa = foraDefesa;
	}


	

}
