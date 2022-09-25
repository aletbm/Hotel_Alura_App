package hotel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
	private String url = "jdbc:mysql://localhost/";
	private String cfg = "?useTimeZone=true&serverTimeZone=UTC";
	private String nameDB = "hotel_alura";
	private String huespedes = "Huespedes";
	private String reservas = "Reservas";
	private String login = "Login";
	
	public DatabaseManager() {
	}
	
	public DatabaseManager(String nameDB, String huespedes, String reservas) {
		this.nameDB = nameDB;
		this.huespedes = huespedes;
		this.reservas = reservas;
	}
	
	public DatabaseManager(String nameDB, String huespedes, String reservas, String login) {
		this.nameDB = nameDB;
		this.huespedes = huespedes;
		this.reservas = reservas;
		this.login = login;
	}
	
	public boolean createDB() {
		try {
			Connection con = DriverManager.getConnection(this.url + this.cfg, "root", "");
			Statement stat = con.createStatement();
			stat.execute("CREATE DATABASE IF NOT EXISTS hotel_alura");
			con.close();

			con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			stat = con.createStatement();

			stat.execute("CREATE TABLE IF NOT EXISTS "+ this.reservas +"(" 
					+ "Id INT AUTO_INCREMENT NOT NULL,"
					+ "FechaEntrada DATE NOT NULL," 
					+ "FechaSalida DATE NOT NULL," 
					+ "Valor INT NOT NULL,"
					+ "FormaPago VARCHAR(50) NOT NULL," 
					+ "PRIMARY KEY (Id))Engine=InnoDB;");

			stat.execute("CREATE TABLE IF NOT EXISTS "+ this.huespedes +"(" 
					+ "Id INT AUTO_INCREMENT,"
					+ "Nombre VARCHAR(50) NOT NULL," 
					+ "Apellido VARCHAR(50) NOT NULL,"
					+ "Fecha_de_Nacimiento DATE NOT NULL," 
					+ "Nacionalidad VARCHAR(50) NOT NULL,"
					+ "Telefono VARCHAR(15) NOT NULL," 
					+ "Id_Reserva INT,"
					+ "FOREIGN KEY (Id_Reserva) REFERENCES Reservas(Id)," + "PRIMARY KEY (Id))Engine=InnoDB;");
			
			stat.execute("CREATE TABLE IF NOT EXISTS "+ this.login +"(" 
					+ "Id INT AUTO_INCREMENT,"
					+ "Nombre VARCHAR(50) NOT NULL," 
					+ "Apellido VARCHAR(50) NOT NULL," 
					+ "Username VARCHAR(50) NOT NULL," 
					+ "Password VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY (Id))Engine=InnoDB;");
			
			con.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public List<Map<String, String>> getHuespedes() {
		return getHuespedes(null);
	}

	@SuppressWarnings("finally")
	public List<Map<String, String>> getHuespedes(String apellido) {
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT * FROM Huespedes ";
			if(apellido != null) {
				query = query + "WHERE apellido LIKE '" + apellido + "%'";
			}
			
			boolean result = stat.execute(query); 
			if(result) {
				ResultSet resultSet = stat.getResultSet(); 
				while(resultSet.next()) { 
					Map<String, String> fila = new HashMap<>(); 
					fila.put("ID", String.valueOf(resultSet.getInt("ID"))); 
					fila.put("NOMBRE", resultSet.getString("NOMBRE"));
					fila.put("APELLIDO", resultSet.getString("APELLIDO")); 
					fila.put("FECHA_DE_NACIMIENTO", resultSet.getDate("FECHA_DE_NACIMIENTO").toString()); 
					fila.put("NACIONALIDAD", resultSet.getString("NACIONALIDAD")); 
					fila.put("TELEFONO", resultSet.getString("TELEFONO")); 
					fila.put("ID_RESERVA", resultSet.getString("ID_RESERVA")); 
					resultado.add(fila); 
				}
			}
			con.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			return resultado; 
		}
	}
	
	@SuppressWarnings("finally")
	public List<Map<String, String>> getHuespedesXnroReserva(String ids_reserva) {
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT * FROM Huespedes WHERE id_reserva IN " + ids_reserva + "";
			
			boolean result = stat.execute(query); 
			if(result) {
				ResultSet resultSet = stat.getResultSet(); 
				while(resultSet.next()) { 
					Map<String, String> fila = new HashMap<>(); 
					fila.put("ID", String.valueOf(resultSet.getInt("ID"))); 
					fila.put("NOMBRE", resultSet.getString("NOMBRE"));
					fila.put("APELLIDO", resultSet.getString("APELLIDO")); 
					fila.put("FECHA_DE_NACIMIENTO", resultSet.getDate("FECHA_DE_NACIMIENTO").toString()); 
					fila.put("NACIONALIDAD", resultSet.getString("NACIONALIDAD")); 
					fila.put("TELEFONO", resultSet.getString("TELEFONO")); 
					fila.put("ID_RESERVA", resultSet.getString("ID_RESERVA")); 
					resultado.add(fila); 
				}
			}
			con.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			return resultado; 
		}
	}
	
	public List<Map<String, String>> getReservas() {
		return getReservas(null);
	}
	
	@SuppressWarnings("finally")
	public List<Map<String, String>> getReservas(String id) {
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT * FROM Reservas";
			if(id != null) {
				query = query + " WHERE id = " + id;
			}
			
			boolean result = stat.execute(query); 
			if(result) {
				ResultSet resultSet = stat.getResultSet(); 
				while(resultSet.next()) { 
					Map<String, String> fila = new HashMap<>(); 
					fila.put("ID", String.valueOf(resultSet.getInt("ID"))); 
					fila.put("FECHAENTRADA", resultSet.getDate("FECHAENTRADA").toString());
					fila.put("FECHASALIDA", resultSet.getDate("FECHASALIDA").toString()); 
					fila.put("VALOR", String.valueOf(resultSet.getInt("VALOR"))); 
					fila.put("FORMAPAGO",resultSet.getString("FORMAPAGO")); 
					resultado.add(fila); 
				}
			}
			con.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			return resultado; 
		}
	}
	
	@SuppressWarnings("finally")
	public List<Map<String, String>> getReservasList(String ids_reserva) {
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT * FROM Reservas WHERE id IN " + ids_reserva;
			
			boolean result = stat.execute(query); 
			if(result) {
				ResultSet resultSet = stat.getResultSet(); 
				while(resultSet.next()) { 
					Map<String, String> fila = new HashMap<>(); 
					fila.put("ID", String.valueOf(resultSet.getInt("ID"))); 
					fila.put("FECHAENTRADA", resultSet.getDate("FECHAENTRADA").toString());
					fila.put("FECHASALIDA", resultSet.getDate("FECHASALIDA").toString()); 
					fila.put("VALOR", String.valueOf(resultSet.getInt("VALOR"))); 
					fila.put("FORMAPAGO",resultSet.getString("FORMAPAGO")); 
					resultado.add(fila); 
				}
			}
			con.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			return resultado; 
		}
	}
	
	@SuppressWarnings("finally")
	public List<Map<String, String>> getLoginUserData(String username) {
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT * FROM Login WHERE username = '" + username + "'";
			
			boolean result = stat.execute(query); 
			if(result) {
				ResultSet resultSet = stat.getResultSet(); 
				while(resultSet.next()) { 
					Map<String, String> fila = new HashMap<>(); 
					fila.put("ID", String.valueOf(resultSet.getInt("ID"))); 
					fila.put("NOMBRE",resultSet.getString("NOMBRE")); 
					fila.put("APELLIDO",resultSet.getString("APELLIDO")); 
					fila.put("USERNAME",resultSet.getString("USERNAME")); 
					fila.put("PASSWORD",resultSet.getString("PASSWORD")); 
					resultado.add(fila); 
				}
			}
			con.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			return resultado; 
		}
	}
	
	public boolean insertUser(String nombre, String apellido, String username, String password) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT COUNT(*) FROM login WHERE username = '" + username + "'";
			stat.execute(query);
			ResultSet resultSet = stat.getResultSet();
			resultSet.next();
			
			if(resultSet.getInt(1) == 0) {
				query = "INSERT INTO login (Nombre, Apellido, Username, Password) VALUES ('" + nombre + "', '" + apellido + "', '" + username + "', '" + password + "')";
			
				stat.execute(query);
			
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertReserva(String id, String dateIn, String dateOut, String precio, String formaPago) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "INSERT INTO reservas (Id, FechaEntrada, FechaSalida, Valor, FormaPago) VALUES ('"+ id +"','" + dateIn + "', '" + dateOut + "', '" + precio + "', '" + formaPago + "')";
			
			stat.execute(query);

			return true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Integer getCantidadReservas() {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "SELECT id FROM reservas ORDER BY Id DESC LIMIT 1";
			stat.execute(query);
			ResultSet resultSet = stat.getResultSet();
			resultSet.next();
			return resultSet.getInt("ID");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public boolean insertHuesped(String name, String lname, String dateNac, String nacionalidad, String telefono, int nroReserva) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "INSERT INTO huespedes (Nombre, Apellido, Fecha_de_Nacimiento, Nacionalidad, Telefono, Id_Reserva) VALUES ('" + name + "', '" + lname + "', '" + dateNac + "', '" + nacionalidad + "', '" + telefono + "', '" + nroReserva +"')";
			
			stat.execute(query);

			return true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean updateReserva(String id, String dateIn, String dateOut, String precio, String formaPago) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "UPDATE reservas SET FechaEntrada = '"+ dateIn +"', FechaSalida = '"+ dateOut +"', Valor = '"+ precio +"', FormaPago  = '"+ formaPago +"' WHERE id = " + id;
			
			stat.execute(query);

			return true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean updateHuesped(String id, String name, String lname, String dateNac, String nacionalidad, String telefono, String nroReserva) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "UPDATE huespedes SET Nombre = '"+ name +"', Apellido = '"+ lname +"', Fecha_de_Nacimiento = '"+ dateNac +"', Nacionalidad = '"+ nacionalidad +"', Telefono = '"+ telefono +"', Id_Reserva = '"+ nroReserva +"' WHERE id = "+ id;
			
			boolean result = stat.execute(query);

			return result;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteReserva(String id) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "DELETE FROM reservas WHERE id = " + id;
			
			boolean result = stat.execute(query);

			return result;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteHuesped(String id) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "DELETE FROM huespedes WHERE id = "+ id;
			
			boolean result = stat.execute(query);

			return result;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteHuespedXnroReserva(String id_reserva) {
		try {
			Connection con = DriverManager.getConnection(this.url + this.nameDB + this.cfg, "root", "");
			Statement stat = con.createStatement();
			
			String query = "DELETE FROM huespedes WHERE id_reserva = "+ id_reserva;
			
			boolean result = stat.execute(query);

			return result;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}


