import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class koneksi {

	void koneksiDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_konter","root","");
			
			JOptionPane.showMessageDialog(null, "Koneksi Berhasil!!!","Report Koneksi", JOptionPane.INFORMATION_MESSAGE);
			
			con.close();
		}
		catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
	}

	public static void main(String[] syauli) {
		koneksi konek = new koneksi();
		konek.koneksiDatabase();
		System.exit(0);

	}

}
