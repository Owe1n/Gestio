package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gestio.Materiel;
import tools.Connect;

public class MaterielModel extends Connect {

	public ArrayList<Materiel> materiels;

	public MaterielModel(String name_of_db) {
		super(name_of_db);
		this.materiels = getAllMateriel();
	}

	public ArrayList<Materiel> getAllMateriel() {
		String sql = "SELECT * FROM materiel";
		ArrayList<Materiel> materielTemp = new ArrayList<Materiel>();
		try (Connection conn = DriverManager.getConnection(this.url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {

				Materiel materiel = new Materiel(rs.getInt("id_materiel"), rs.getString("name"), rs.getString("imgSrc"),
						rs.getInt("nbBon"), rs.getInt("nbMoyen"), rs.getInt("nbMauvais"));

				materielTemp.add(materiel);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return materielTemp;
	}

	public Materiel getMaterielById(int id) {

		String sql = "SELECT * FROM materiel WHERE id_materiel = " + id + "";
		Materiel materiel = new Materiel();
		try (Connection conn = DriverManager.getConnection(this.url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			materiel.setId(rs.getInt("id_materiel"));
			materiel.setName(rs.getString("name"));
			materiel.setImagePath(rs.getString("imgSrc"));
			materiel.setNbBon(rs.getInt("nbBon"));
			materiel.setNbMoyen(rs.getInt("nbMoyen"));
			materiel.setNbMauvais(rs.getInt("nbMauvais"));

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return materiel;

	}

	public void addMateriel(Materiel mat) {

		String sql = "INSERT INTO materiel (name,imgSrc,nbBon,nbMoyen,nbMauvais) VALUES('" + mat.getName() + "','"
				+ mat.getImagePath() + "','" + mat.getNbBon() + "','" + mat.getNbMoyen() + "','" + mat.getNbMauvais()
				+ "')";
		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteMateriel(Materiel mat) {
		String sql = "DELETE FROM materiel WHERE id_materiel = '" + mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editMateriel(Materiel mat) {
		String sql = "UPDATE materiel SET name= '" + mat.getName() + "', " + "imgSrc ='" + mat.getImagePath()
				+ "', nbBon='" + mat.getNbBon() + "'," + "nbMoyen = '" + mat.getNbMoyen() + "',nbMauvais = '"
				+ mat.getNbMauvais() + "' WHERE id_materiel = " + "'" + mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void addBonMateriel(Materiel mat) {
		String sql = "UPDATE materiel SET nbBon= '" + (mat.getNbBon() + 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void addMoyenMateriel(Materiel mat) {
		String sql = "UPDATE materiel SET nbMoyen= '" + (mat.getNbMoyen() + 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void addMauvaisMateriel(Materiel mat) {
		String sql = "UPDATE materiel SET nbMauvais= '" + (mat.getNbMauvais() + 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void removeBonMateriel(Materiel mat) {
		if (mat.getNbBon() == 0) {
			mat.setNbBon(1);
		}
		String sql = "UPDATE materiel SET nbBon= '" + (mat.getNbBon() - 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void removeMoyenMateriel(Materiel mat) {
		if (mat.getNbMoyen() == 0) {
			mat.setNbMoyen(1);
		}
		String sql = "UPDATE materiel SET nbMoyen= '" + (mat.getNbMoyen() - 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void removeMauvaisMateriel(Materiel mat) {
		if (mat.getNbMauvais() == 0) {
			mat.setNbMauvais(1);
		}
		String sql = "UPDATE materiel SET nbMauvais= '" + (mat.getNbMauvais() - 1) + "' WHERE id_materiel = " + "'"
				+ mat.getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
