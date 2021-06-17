package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import gestio.Materiel;
import gestio.Utilisateur;
import tools.Connect;
import gestio.Demande;

public class DemandesModel extends Connect {

	public ArrayList<Demande> demandes;

	public DemandesModel(String name_of_db) {
		super(name_of_db);
		// TODO Auto-generated constructor stub
		this.demandes = getAllDemands(0);
	}

	public Demande getDemandsByUser(Utilisateur user) {
		MaterielModel materielModel = new MaterielModel("Gestio");
		Demande demande = new Demande();
		demande.setUser(user);

		HashMap<Materiel, ArrayList<Integer>> materiels = new HashMap<Materiel, ArrayList<Integer>>();
		String sql2 = "SELECT * \r\n" + "FROM demande d\r\n"
				+ "	INNER JOIN utilisateur u ON ( d.id_utilisateur = u.id_utilisateur  )  \r\n"
				+ "	INNER JOIN materiel m ON ( d.id_materiel = m.id_materiel  )  \r\n" + "WHERE d.id_utilisateur = "
				+ user.getId();

		try (Connection conn = DriverManager.getConnection(this.url);
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2)) {
			demande.setId(rs2.getInt("id_demande"));
			while (rs2.next()) {

				int id_materiel = rs2.getInt("id_materiel");
				int quantity_bon = rs2.getInt("quantity_bon");
				int quantity_moyen = rs2.getInt("quantity_moyen");
				int quantity_mauvais = rs2.getInt("quantity_mauvais");
				ArrayList<Integer> quantities_materiel = new ArrayList<Integer>();
				quantities_materiel.add(quantity_bon);
				quantities_materiel.add(quantity_moyen);
				quantities_materiel.add(quantity_mauvais);

				Materiel materiel = materielModel.getMaterielById(id_materiel);
				materiels.put(materiel, quantities_materiel);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		demande.setMateriels(materiels);

		return demande;
	}

	public ArrayList<Demande> getAllDemands(int typeDemande) {
		UtilisateursModel userModel = new UtilisateursModel("Gestio");
		MaterielModel materielModel = new MaterielModel("Gestio");

		ArrayList<Demande> demandes = new ArrayList<Demande>();

		// RECUPERER TOUS LES IDS
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String sql = "SELECT distinct d.id_utilisateur  \r\n" + "FROM demande d";
		try (Connection conn = DriverManager.getConnection(this.url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				ids.add(rs.getInt("id_utilisateur"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// CREER LES DEMANDES
		for (Integer i : ids) {
			Demande demande = new Demande();
			demande.setUser(userModel.getUtilisateurById(i));

			HashMap<Materiel, ArrayList<Integer>> materiels = new HashMap<Materiel, ArrayList<Integer>>();
			String sql2 = "SELECT * \r\n" + "FROM demande d\r\n"
					+ "	INNER JOIN utilisateur u ON ( d.id_utilisateur = u.id_utilisateur  )  \r\n"
					+ "	INNER JOIN materiel m ON ( d.id_materiel = m.id_materiel  )  \r\n" + "WHERE d.id_utilisateur = "
					+ i;

			try (Connection conn = DriverManager.getConnection(this.url);
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(sql2)) {
				demande.setId(rs2.getInt("id_demande"));
				while (rs2.next()) {

					int id_materiel = rs2.getInt("id_materiel");
					int quantity_bon = rs2.getInt("quantity_bon");
					int quantity_moyen = rs2.getInt("quantity_moyen");
					int quantity_mauvais = rs2.getInt("quantity_mauvais");
					ArrayList<Integer> quantities_materiel = new ArrayList<Integer>();
					quantities_materiel.add(quantity_bon);
					quantities_materiel.add(quantity_moyen);
					quantities_materiel.add(quantity_mauvais);

					Materiel materiel = materielModel.getMaterielById(id_materiel);
					materiels.put(materiel, quantities_materiel);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			demande.setMateriels(materiels);
			demandes.add(demande);
		}

		return demandes;

	}

	public void addDemand(Utilisateur user, Materiel materiel, int quantity) {
		// DISTRIBUTION DES QUANTITE
		MaterielModel matModel = new MaterielModel("Gestio");

		int quantity_bon = materiel.getNbBon();
		int quantity_moyen = materiel.getNbMoyen();
		int quantity_mauvais = materiel.getNbMauvais();

		while (quantity != 0) {
			if (quantity_bon != 0) {
				quantity_bon--;
				quantity--;
			} else if (quantity_moyen != 0) {

				quantity_moyen--;
				quantity--;
			} else {
				quantity_mauvais--;
				quantity--;
			}
		}

		quantity_bon = materiel.getNbBon() - quantity_bon; // 6 - 0 -> Veut dire qu'on a prit tout les bons..
		quantity_moyen = materiel.getNbMoyen() - quantity_moyen;
		quantity_mauvais = materiel.getNbMauvais() - quantity_mauvais;

		String sql = "INSERT INTO demande (id_materiel,id_utilisateur,quantity_bon,quantity_moyen,quantity_mauvais) "
				+ "VALUES('" + materiel.getId() + "','" + user.getId() + "','" + quantity_bon + "','" + quantity_moyen
				+ "','" + quantity_mauvais + "')";
		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Maj des nouvelles quantitées
		materiel.setNbBon(materiel.getNbBon() - quantity_bon);
		materiel.setNbMoyen(materiel.getNbMoyen() - quantity_moyen);
		materiel.setNbMauvais(materiel.getNbMauvais() - quantity_mauvais);
		matModel.editMateriel(materiel);
	}

	public void validerDemande(Demande dem) {
		MaterielModel matModel = new MaterielModel("Gestio");
		String sql = "DELETE FROM demande WHERE id_utilisateur = '" + dem.getUser().getId() + "'";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		for (Entry<Materiel, ArrayList<Integer>> set : dem.getMateriels().entrySet()) {
			Materiel mat = set.getKey();
			mat.setNbBon(mat.getNbBon() + set.getValue().get(0));
			mat.setNbMoyen(mat.getNbMoyen() + set.getValue().get(1));
			mat.setNbMauvais(mat.getNbMauvais() + set.getValue().get(2));
			matModel.editMateriel(mat);
		}

	}

	public void refuserDemande(Demande dem) {
	}

}
