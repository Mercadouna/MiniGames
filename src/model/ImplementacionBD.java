package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ImplementacionBD implements PlayerDAO{
			// Atributos
		private Connection con;
		private PreparedStatement stmt;
			//private Connection con;
			//private PreparedStatement stmt;

			// Los siguientes atributos se utilizan para recoger los valores del fich de
			// configuraci n
			private ResourceBundle configFile;
			private String driverBD;
			private String urlBD;
			private String userBD;
			private String passwordBD;
			final String SQL = "SELECT US_NOM, PASS FROM usuario WHERE US_NOM = ? AND PASS = ?";
			final String sql = "SELECT * FROM usuario WHERE usuario = ? AND contraseña = ?";
			final String sqlInsert = "INSERT INTO usuario VALUES ( ?,?)";
			final String sqlConsulta = "SELECT * FROM usuario";
			final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
			final String SQLMODIFICAR = "UPDATE usuario SET contraseña=? WHERE nombre=?";


	public ImplementacionBD() {
				this.configFile = ResourceBundle.getBundle("modelo.configClase");
				this.driverBD = this.configFile.getString("Driver");
				this.urlBD = this.configFile.getString("Conn");
				this.userBD = this.configFile.getString("DBUser");
				this.passwordBD = this.configFile.getString("DBPass");
			}

	

	@Override
	public boolean comprobarplayer(Player player) {
		boolean existe=false;
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(sql);
			 stmt.setString(1, player.getNombre());
	         stmt.setString(2, player.getPassword());
	         ResultSet resultado = stmt.executeQuery();
	         
	         
	         if (resultado.next()) {
	                existe = true;
	            }
	         
	         resultado.close();
	            stmt.close();
	            con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return existe;
	}


	@Override
	public boolean eliminarplayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean modificarpuntos(Player player) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean eliminarhist(Player player) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean visualizarhist(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private void openConnection() {
		try {
			con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public boolean comprobarUsuario(Usuario usuario){
		// Abrimos la conexion
		boolean existe=false;
		this.openConnection();

		
		try {
			stmt = con.prepareStatement(SQL);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContraseña());
            ResultSet resultado = stmt.executeQuery();

            //Si hay un resultado, el usuario existe
            if (resultado.next()) {
                existe = true;
            }

            
            resultado.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return existe;
	} */
	
 /*       @Override
		public boolean insertarUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			boolean ok=false;
			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

				stmt = con.prepareStatement(sqlInsert);
				stmt.setString(1, usuario.getNombre());
				stmt.setString(2, usuario.getContraseña());
				if (stmt.executeUpdate()>0) {
					ok=true;
				}
				
	            stmt.close();
	            con.close();
			  } catch (SQLException e) {
	             System.out.println("Error al verificar credenciales: " + e.getMessage());
	        }
			return ok;
			
		
    } */
	
    /*    public Map<String, Usuario> consultaUsuarios() {
			// TODO Auto-generated method stub
			
			ResultSet rs = null;
			Usuario usuario;
			Map<String, Usuario> equipos = new TreeMap<>();

			// Abrimos la conexi n
			this.openConnection();

			try {
				stmt = con.prepareStatement(sqlConsulta);

				rs = stmt.executeQuery();

				// Leemos de uno en uno
				while (rs.next()) {
					usuario = new Usuario();
					usuario.setNombre(rs.getString("nombre"));
					usuario.setContraseña(rs.getString("contraseña"));
					equipos.put(usuario.getNombre(), usuario);
				}
				rs.close();
	            stmt.close();
	            con.close();
			} catch (SQLException e) {
				System.out.println("Error de SQL");
				e.printStackTrace();
			}
			return equipos;

} */
  /*      public boolean borrarUsuario(String usuario) {
			// TODO Auto-generated method stub
			boolean ok=false;
			
				this.openConnection();
				try {
					// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
	
					stmt = con.prepareStatement(SQLBORRAR);
					stmt.setString(1, usuario);
					if (stmt.executeUpdate()>0) {
						ok=true;
					}
					
		            stmt.close();
		            con.close();
				  } catch (SQLException e) {
		             System.out.println("Error al verificar credenciales: " + e.getMessage());
		        }
			
				return ok;
			
			
		} */

  /*      public boolean actualizarUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			boolean ok=false;
			
				this.openConnection();
				try {
					// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
	
					stmt = con.prepareStatement(SQLMODIFICAR);
					stmt.setString(2, usuario.getNombre());
					stmt.setString(1, usuario.getContraseña());
					if (stmt.executeUpdate()>0) {
						ok=true;
					}
					
		            stmt.close();
		            con.close();
				  } catch (SQLException e) {
		             System.out.println("Error al verificar credenciales: " + e.getMessage());
		        }
			
				return ok;
			
			
		} */

}
