import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransaksiBarangKeluar1 extends JFrame{
//	label txfield Barang Masuk
	JLabel LNoTrans = new JLabel("No. Transaksi");
	JTextField txNoTsk = new JTextField();
	
	JLabel Ltgl		 = new JLabel("Tanggal");
	JDateChooser txTgl = new JDateChooser();
	
	JLabel LNmPtg		= new JLabel("Nama Petugas");
	JTextField txNmPtg 	= new JTextField();
	String[] ptg = {""};
	JComboBox cbptg	= new JComboBox(ptg);  //combo KODE PETUGAS
	
	JLabel LNmMit		= new JLabel("Nama Mitra");
	JTextField txNmSup  = new JTextField();
	String[] Sup = {""};
	JComboBox cbSup	= new JComboBox(Sup); //combo KODE SUPPLIER
	
	JLabel LNmBrg	 	= new JLabel("Nama Barang");
	JTextField txNmBrg 	= new JTextField();
	String[] Brg = {""};
	JComboBox cbBrg	= new JComboBox(Brg); //combo KODE Nama brg
	
	JLabel Lkat		 = new JLabel("Kategori");
	JTextField txKat = new JTextField();
	
	JLabel LStkMasuk 	= new JLabel("Stok Keluar");
	JTextField txStkMsk = new JTextField();
	
//	Button
	JButton btnSimpan = new JButton("SIMPAN");
	JButton btnHapus  = new JButton("HAPUS");
	JButton btnReset  = new JButton("RESET");
//	panel
	JPanel pHeader = new JPanel();
	JPanel pTable  = new JPanel();
	
//	koneksi
	Connection koneksi = null;
	
//	table
	String[] header = {"","","",""};
	
	DefaultTableModel model = new DefaultTableModel();
	JTable tabel = new JTable(model);
	JScrollPane pane = new JScrollPane(tabel);
	Dimension dimensi = new Dimension(5,0);

	TransaksiBarangKeluar1(){
		setTitle("TRANSAKSI BARANG KELUAR");
		setBounds(100, 100, 1392, 706);
		getContentPane().setLayout(null);
	}
	
	void KomponenVisual() {
//		panel header
		getContentPane().add(pHeader);
		pHeader.setLayout(null);
		pHeader.setBackground(new Color(204, 153, 255));
		pHeader.setBounds(0, 0, 1376, 144);
		
		pHeader.add(LNoTrans);
		LNoTrans.setFont(new Font("Calibri", Font.PLAIN, 14));
		LNoTrans.setBounds(25, 22, 93, 14);
		pHeader.add(txNoTsk);
		txNoTsk.setBounds(138, 11, 155, 25);
		
		pHeader.add(Ltgl);
		Ltgl.setFont(new Font("Calibri", Font.PLAIN, 14));
		Ltgl.setBounds(25, 58, 118, 14);
		pHeader.add(txTgl);
		txTgl.setBounds(138, 47, 155, 25);
		
		pHeader.add(LNmPtg);
		LNmPtg.setFont(new Font("Calibri", Font.PLAIN, 14));
		LNmPtg.setBounds(360, 22, 118, 14);
		pHeader.add(cbptg);
		cbptg.setBounds(475, 11, 35, 25);
		pHeader.add(txNmPtg);
		txNmPtg.setBounds(512, 11, 118, 25);
		
		pHeader.add(LNmMit);
		LNmMit.setFont(new Font("Calibri", Font.PLAIN, 14));
		LNmMit.setBounds(360, 58, 118, 14);
		pHeader.add(cbSup);
		cbSup.setBounds(475, 47, 35, 25);
		pHeader.add(txNmSup);
		txNmSup.setBounds(512, 47, 118, 25);

		pHeader.add(LNmBrg);
		LNmBrg.setFont(new Font("Calibri", Font.PLAIN, 14));
		LNmBrg.setBounds(692, 22, 118, 14);
		pHeader.add(cbBrg);
		cbBrg.setBounds(801, 11, 35, 25);
		pHeader.add(txNmBrg);
		txNmBrg.setBounds(838, 11, 117, 25);
		
		pHeader.add(Lkat);
		Lkat.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lkat.setBounds(692, 58, 118, 14);
		pHeader.add(txKat);
		txKat.setBounds(801, 47, 154, 25);
		
		pHeader.add(LStkMasuk);
		LStkMasuk.setFont(new Font("Calibri", Font.PLAIN, 14));
		LStkMasuk.setBounds(1017, 22, 93, 14);
		pHeader.add(txStkMsk);
		txStkMsk.setBounds(1120, 11, 155, 25);
		
		//		buttom
		pHeader.add(btnSimpan);
		btnSimpan.setBounds(1013, 95, 89, 34);
		
		pHeader.add(btnHapus);
		btnHapus.setBounds(1112, 95, 89, 34);
		
		pHeader.add(btnReset);
		btnReset.setBounds(1211, 95, 89, 34);
		
		
//		panel tabel
		getContentPane().add(pTable);
		pTable.setLayout(null);
		pTable.setBackground(new Color(255, 255, 255));
		pTable.setBounds(0, 144, 1376, 536);
		
		pTable.add(pane);
		pane.setBounds(0, 37, 1376, 454);
		
		pane.setViewportView(tabel);
		tabel.setShowGrid(true);
		tabel.setShowVerticalLines(true);
		tabel.setIntercellSpacing(new Dimension(dimensi));
		tabel.setGridColor(Color.green);
		
		
		setVisible(true);
	}
	
//	koneksi
	void koneksiDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf) {
			System.out.println("Driver Tidak ditemukan : " + cnf);
		}
		try {
			koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_konter","root","");
			System.out.println("Koneksi Sukses");
		}
		catch(SQLException se) {
			System.out.println("Koneksi gagal : " + se);
		}
	}
	
	void AksiReaksi() {
		
	}//end aksyi reaksyiii
	
//	BERSIH DATA 
	void bersihData() {
////		txtanggal.update("");
//		txKdBrg.setText("");
//		txNmBrg.setText("");
//		txStok.setText("");
//		txHarga.setText("");
//		txKet.setText("");
	}	
	
//	TAMPIL TABEL
	void tampilTabel() {
		hapusTabel(); //ambil data di void hapus tabel()
		try {
			Statement state = koneksi.createStatement(); 
			String sql = "select * from tb_barang2 "; 
			ResultSet rs = state.executeQuery(sql);
	
			while(rs.next()) {
				Object obj[] = new Object[6];
				obj[0] = rs.getString(1); //tanggal 
				obj[1] = rs.getString(2); //kode  
				obj[2] = rs.getString(3); //nama 
				obj[3] = rs.getString(4); //stok 
				obj[4] = rs.getString(5);
				obj[5] = rs.getString(6); //ket 

			model.addRow(obj);
		}}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	
//   hapus tabel	
	void hapusTabel() {
		int row = model.getRowCount(); 
		for(int i = 0;i< row;i++) {
			model.removeRow(0);
	}}
	
	public static void main(String[] Yoona) {
		TransaksiBarangKeluar1 TBK = new TransaksiBarangKeluar1();
		TBK.KomponenVisual();
		TBK.AksiReaksi();
	}
	
}
