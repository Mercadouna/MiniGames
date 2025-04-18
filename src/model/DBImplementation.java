package model;

import java.sql.Connection;
import java.util.random.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;

public  class DBImplementation implements PlayerDAO{
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
	private final String SQL = "SELECT US_NAME, PASS FROM PLAYER WHERE US_NAME = ? AND PASS = ?";
	private final String comparePL = "SELECT US_NAME FROM PLAYER WHERE US_NAME = ? ";
	private final String eliminarpr = "call DeleteUser(?)" ;
	private final String CHECKPL = "SELECT US_NAME FROM PLAYER WHERE US_NAME = ?";
	private final String addpl = "call InsertPlayer(?, ?)" ;
	private final String TakeID = "SELECT id from PLAYER WHERE US_NAME =  ?";
	private final String sqlInsert = "INSERT INTO usuario VALUES ( ?,?)";
	private final String sqlConsulta = "SELECT * FROM usuario";
	private final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	private final String MODPOINTS = "UPDATE PLAYER SET POINTS = ? WHERE US_NAME = ?";
	private final String OBTAINPOINTS = "SELECT POINTS FORM PLAYER WHERE US_NAME = ?";
	private final String DELPL = "DELETE PLAYER FROM PLAYER WHERE US_NAME = ?";
	private final String GETPLAYS = "SELECT * FROM PLAYS WHERE Us_Id = ?";
	private final String SAVEPLAYS = "INSERT INTO PLAYS(Us_ID, G_Name, DATI, SCORE) VALUES (?, ?, CURDATE(), ?)";
	private final String GET_POINTS = "SELECT POINTS FROM PLAYER WHERE Us_Id = ?";
	private final String CHECK_BOUGHT = "SELECT 1 FROM BUY WHERE Us_Id = ? AND T_NAME = ?";
	private final String UPDATE_POINTS = "UPDATE PLAYER SET POINTS = POINTS - ? WHERE Us_Id = ?";
	private final String INSERT_BUY = "INSERT INTO BUY (Us_Id, T_NAME) VALUES (?, ?)";
	private final String GET_ID_BY_NAME = "SELECT Us_Id FROM PLAYER WHERE US_NAME = ?"; 
	private final String ADD_POINTS_SQL = "UPDATE PLAYER SET POINTS = POINTS + ? WHERE Us_Id = ?";

	public DBImplementation() {
		this.configFile = ResourceBundle.getBundle("configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}



	@Override
	public boolean compareplayer(Player player) {
		boolean exist=false;
		this.openConnection();

		try {
			stmt = con.prepareStatement(SQL);	
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getPassword());
			ResultSet result = stmt.executeQuery();


			if (result.next()) {
				exist = true;
			}

			result.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return exist;
	}


	@Override
	public void deleteplayer(Player player) {
		this.openConnection();
		try {
			this.openConnection();
			stmt = con.prepareCall(DELPL); 
			stmt.setString(1, player.getName()); 
			stmt.executeUpdate(); 


			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al eliminar al jugador: " + e.getMessage());
			e.printStackTrace();
		}

	}



	public int RandomPoints() {

		int randomNum = (int)(Math.random() * 300); 

		return randomNum;

	}

	@Override
	public void modifypoints(Player player, String gname) { // gname ya no se usa aquí
	    int randpoint;
	    randpoint = RandomPoints();
	    // recordPlay( player, gname,randpoint); // <<<--- ELIMINAR ESTA LÍNEA

	    // El resto del método para actualizar puntos (también necesita arreglar conexión)
	    String sql = MODPOINTS; // "UPDATE PLAYER SET POINTS = ? WHERE US_NAME = ?"
	    int newPoints = player.getPoints() + randpoint; // Cuidado, esto usa puntos locales obsoletos

	     try (Connection tempCon = DriverManager.getConnection(urlBD, userBD, passwordBD);
	          PreparedStatement tempStmt = tempCon.prepareStatement(sql)) {

	         // Idealmente, la actualización debería ser atómica en BD:
	         // UPDATE PLAYER SET POINTS = POINTS + ? WHERE US_NAME = ?
	         // Y pasar randpoint en lugar de newPoints.
	         // Pero siguiendo tu SQL actual:
	         tempStmt.setInt(1, newPoints);
	         tempStmt.setString(2, player.getName());
	         tempStmt.executeUpdate();

	     } catch (SQLException e) {
	         System.err.println("Error SQL en modifypoints: " + e.getMessage());
	         e.printStackTrace(); // O manejar de otra forma
	     }
	     // Eliminar this.openConnection() y los close() manuales de aquí
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



	@Override
	public void addplayer(Player player) {
		this.openConnection();
		try {
			stmt = con.prepareCall(addpl);
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getPassword());
			stmt.executeQuery();
			stmt.close();
			con.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public boolean checkPL(Player player) {
		this.openConnection();
		boolean exist = false;
		try {
			stmt = con.prepareStatement(CHECKPL);
			stmt.setString(1, player.getName());
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				exist = true;
			}
			resultSet.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}



	public String ReturnID(Player player) {
		this.openConnection();
		String id = "";
		try {
			PreparedStatement stm = con.prepareStatement("SELECT Us_Id FROM PLAYER WHERE US_NAME = ? AND PASS = ?");
			stm.setString(1, player.getName());
			stm.setString(2, player.getPassword());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				id = rs.getString("Us_Id");
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}


	public int obtpoints(Player player) {
		this.openConnection();
		int points = 0;
		try {
			PreparedStatement stm = con.prepareStatement("SELECT POINTS FROM PLAYER WHERE US_NAME = ?");
			stm.setString(1, player.getName());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				points = rs.getInt("POINTS");
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
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



	@Override
	public ArrayList getPlays(Player player) {
		ArrayList<Plays> plays= new ArrayList<Plays>(); 
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETPLAYS);
			stmt.setString(1, ReturnID(player));
			ResultSet retrievedPlays = stmt.executeQuery();
			while (retrievedPlays.next()) {
				Plays p=new Plays(
						retrievedPlays.getString("G_NAME"),
						retrievedPlays.getDate("DATI").toLocalDate(),
						retrievedPlays.getInt("SCORE")
						);
				plays.add(p);
			}
			retrievedPlays.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}
		return plays;
	}




	// Dentro de DBImplementation.java

	@Override // Asegúrate que la interfaz PlayerDAO tiene esta firma
	public void recordPlay(Player player, String gname, int score) throws SQLException {
	    int playerId = -1;

	    // --- OBTENER ID USANDO GET_ID_BY_NAME (MÁS FIABLE QUE ReturnID) ---
	    // Aún así, lo ideal es tener el ID en el objeto Player desde el login.
	    String getIdSql = GET_ID_BY_NAME; // "SELECT Us_Id FROM PLAYER WHERE US_NAME = ?"
	    try (Connection idCon = DriverManager.getConnection(urlBD, userBD, passwordBD);
	         PreparedStatement idStmt = idCon.prepareStatement(getIdSql)) {

	        idStmt.setString(1, player.getName()); // Busca por nombre, no contraseña
	        try (ResultSet idRs = idStmt.executeQuery()) {
	            if (idRs.next()) {
	                playerId = idRs.getInt("Us_Id"); // Obtiene el INT directamente
	            } else {
	                // Lanzar excepción si no podemos encontrar el ID por nombre
	                throw new SQLException("recordPlay: No se pudo encontrar el ID del jugador para el nombre: " + player.getName());
	            }
	        } // idRs se cierra solo
	    } catch (SQLException e) {
	        // Log and re-throw or handle more gracefully
	        System.err.println("recordPlay: Error SQL al obtener ID para " + player.getName() + ": " + e.getMessage());
	        throw e; // Re-throw para notificar al controlador
	    } // idStmt y idCon se cierran automáticamente
	    // --- Fin obtención ID ---

	    if (playerId <= 0) { // Comprobación extra por si acaso
	        throw new SQLException("recordPlay: ID de jugador inválido obtenido: " + playerId);
	    }

	    // --- Ahora, ejecutar el INSERT con el ID correcto ---
	    String sql = SAVEPLAYS; // "INSERT INTO PLAYS(Us_ID, G_Name, DATI, SCORE) VALUES (?, ?, CURDATE(), ?)";
	    System.out.println("Guardando partida: Jugador ID=" + playerId + ", Juego=" + gname + ", Score=" + score); // Debug

	    // Usar Try-with-Resources para la conexión y el statement del INSERT
	    try (Connection tempCon = DriverManager.getConnection(urlBD, userBD, passwordBD);
	         PreparedStatement tempStmt = tempCon.prepareStatement(sql)) {

	        tempStmt.setInt(1, playerId);    // <<<--- Usar setInt con el ID correcto
	        tempStmt.setString(2, gname);
	        tempStmt.setInt(3, score);
	        tempStmt.executeUpdate();

	        System.out.println("Partida guardada exitosamente."); // Debug Éxito

	    } catch (SQLException e) {
	        System.err.println("Error SQL al guardar partida para player ID " + playerId + ": " + e.getMessage());
	        // Relanzar la excepción
	        throw e;
	    } // Recursos se cierran automáticamente
	}
	
	public boolean buyTrophy(Player player, String trophyName, int trophyPrice)
			throws InsufficientPointsException, SQLException {

		Connection localCon = null; // Usar una conexión local para la transacción
		PreparedStatement checkPointsStmt = null;
		PreparedStatement checkBoughtStmt = null;
		PreparedStatement updatePointsStmt = null;
		PreparedStatement insertBuyStmt = null;
		PreparedStatement getIdStmt = null; // Para obtener ID si no lo tenemos
		ResultSet rs = null;

		boolean purchaseSuccessful = false;
		int currentPoints = -1;
		int playerId = -1;

		try {
			localCon = DriverManager.getConnection(urlBD, userBD, passwordBD);
			localCon.setAutoCommit(false);
			getIdStmt = localCon.prepareStatement(GET_ID_BY_NAME);
			getIdStmt.setString(1, player.getName());
			rs = getIdStmt.executeQuery();
			if (rs.next()) {
				playerId = rs.getInt("Us_Id");
			} else {
				throw new SQLException("Player not found: " + player.getName());
			}
			rs.close();

			checkBoughtStmt = localCon.prepareStatement(CHECK_BOUGHT);
			checkBoughtStmt.setInt(1, playerId);
			checkBoughtStmt.setString(2, trophyName);
			rs = checkBoughtStmt.executeQuery();
			if (rs.next()) {
				System.out.println("Player " + playerId + " already owns trophy " + trophyName);
				localCon.rollback(); 
				return false; 
			}
			rs.close(); 


			checkPointsStmt = localCon.prepareStatement(GET_POINTS);
			checkPointsStmt.setInt(1, playerId);
			rs = checkPointsStmt.executeQuery();
			if (rs.next()) {
				currentPoints = rs.getInt("POINTS");
			} else {
				throw new SQLException("Could not retrieve points for player ID: " + playerId);
			}
			rs.close(); // Cerrar ResultSet


			// 4. Comprobar si tiene puntos suficientes
			if (currentPoints < trophyPrice) {
				// No tiene suficientes puntos, lanzar excepción personalizada
				throw new InsufficientPointsException("Puntos insuficientes para comprar " + trophyName +
						". Necesarios: " + trophyPrice + ", Tienes: " + currentPoints);
			}

			// 5. Actualizar puntos del jugador
			updatePointsStmt = localCon.prepareStatement(UPDATE_POINTS);
			updatePointsStmt.setInt(1, trophyPrice); // Puntos a descontar
			updatePointsStmt.setInt(2, playerId);
			int rowsAffectedPlayer = updatePointsStmt.executeUpdate();

			// 6. Insertar registro de compra
			insertBuyStmt = localCon.prepareStatement(INSERT_BUY);
			insertBuyStmt.setInt(1, playerId);
			insertBuyStmt.setString(2, trophyName);
			int rowsAffectedBuy = insertBuyStmt.executeUpdate();

			// 7. Si ambas operaciones tuvieron éxito, confirmar transacción
			if (rowsAffectedPlayer > 0 && rowsAffectedBuy > 0) {
				localCon.commit();
				purchaseSuccessful = true;
				System.out.println("Compra exitosa: Jugador " + playerId + ", Trofeo " + trophyName);
			} else {
				// Si algo falló (inesperado si no hubo excepciones), deshacer
				localCon.rollback();
				System.err.println("Error en la transacción de compra, rollback ejecutado.");
				throw new SQLException("Database update failed during trophy purchase, transaction rolled back.");
			}

		} catch (SQLException | InsufficientPointsException e) {
			// Si ocurre cualquier error SQL o de puntos insuficientes, deshacer transacción
			if (localCon != null) {
				try {
					System.err.println("Error durante la compra (" + e.getMessage() + "), ejecutando rollback...");
					localCon.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error ejecutando rollback: " + rollbackEx.getMessage());
				}
			}
			// Relanzar la excepción para que la capa superior la maneje
			throw e;

		} finally {
			// Asegurarse de cerrar todos los recursos y restaurar auto-commit
			try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorar */ }
			try { if (getIdStmt != null) getIdStmt.close(); } catch (SQLException e) { /* Ignorar */ }
			try { if (checkPointsStmt != null) checkPointsStmt.close(); } catch (SQLException e) { /* Ignorar */ }
			try { if (checkBoughtStmt != null) checkBoughtStmt.close(); } catch (SQLException e) { /* Ignorar */ }
			try { if (updatePointsStmt != null) updatePointsStmt.close(); } catch (SQLException e) { /* Ignorar */ }
			try { if (insertBuyStmt != null) insertBuyStmt.close(); } catch (SQLException e) { /* Ignorar */ }
			if (localCon != null) {
				try {
					localCon.setAutoCommit(true); // Restaurar modo auto-commit
					localCon.close(); // Cerrar conexión
				} catch (SQLException e) {
					System.err.println("Error al cerrar conexión/restaurar auto-commit: " + e.getMessage());
				}
			}
			// ¡OJO! Si usas un Pool de Conexiones, el cierre (localCon.close())
			// normalmente devuelve la conexión al pool en lugar de cerrarla físicamente.
			// La gestión de autoCommit también puede variar según el pool.
		}

		return purchaseSuccessful;
	}



	@Override
	public ArrayList<String> getBoughtTrophies(int userId) {
		this.openConnection();
		ArrayList<String> trophies = new ArrayList<>(); 
		try {
			PreparedStatement stm = con.prepareStatement("SELECT T_NAME FROM BUY WHERE Us_Id = ?");
			stm.setInt(1, userId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				trophies.add(rs.getString("T_NAME"));
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error al obtener los trofeos comprados: " + e.getMessage());
			e.printStackTrace();
		}
		return trophies;
	}





	@Override // Si está en PlayerDAO
	public void addPoints(Player player, int pointsToAdd) throws SQLException {
	    // --- Obtener playerId de forma segura (NECESITA MEJORA URGENTE en ReturnID/gestión de ID) ---
	    int playerId = -1;
	    try {
	        String idStr = this.ReturnID(player); // Sigue usando el método problemático
	        if (idStr != null && !idStr.isEmpty()) {
	            playerId = Integer.parseInt(idStr);
	        } else {
	            throw new SQLException("addPoints: No se pudo obtener el ID del jugador para " + player.getName());
	        }
	    } catch (NumberFormatException e) {
	        throw new SQLException("addPoints: Fallo al convertir el ID a número desde ReturnID para " + player.getName() + ". ReturnID devolvió: '" + this.ReturnID(player) + "'", e);
	    } catch (Exception e) { // Captura otros errores de ReturnID
	        throw new SQLException("addPoints: Error llamando a ReturnID para " + player.getName(), e);
	    }
	    // --- Fin obtención ID ---

	    if (playerId <= 0) { // Comprobación extra
	        throw new SQLException("addPoints: ID de jugador inválido obtenido: " + playerId);
	    }

	    String sql = ADD_POINTS_SQL;
	    System.out.println("Intentando añadir " + pointsToAdd + " puntos al jugador ID: " + playerId); // Debug

	    try (Connection tempCon = DriverManager.getConnection(urlBD, userBD, passwordBD);
	         PreparedStatement tempStmt = tempCon.prepareStatement(sql)) {

	        tempStmt.setInt(1, pointsToAdd); // Puntos a añadir
	        tempStmt.setInt(2, playerId);    // <<<--- CORRECCIÓN: Usar setInt para el ID

	        int rowsAffected = tempStmt.executeUpdate(); // Ejecutar el UPDATE

	        if (rowsAffected > 0) {
	            System.out.println("Puntos añadidos correctamente para jugador ID " + playerId); // Debug Éxito
	        } else {
	             // Esto podría pasar si el playerId no existe en la tabla PLAYER
	             System.err.println("Advertencia: No se actualizaron los puntos para el jugador ID " + playerId + ". ¿Existe el jugador en la BD?");
	             // Considera lanzar una excepción si esto no debería ocurrir
	             // throw new SQLException("No se encontró el jugador con ID " + playerId + " para actualizar puntos.");
	        }

	    } catch (SQLException e) {
	        System.err.println("Error SQL al añadir puntos para player ID " + playerId + ": " + e.getMessage());
	        throw e; // Relanzar la excepción para que el controlador (y quizás la vista) se enteren
	    } // Recursos se cierran automáticamente
	}
}	
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