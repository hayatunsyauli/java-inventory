import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.toedter.calendar.JDateChooser; // library tgl
import java.text.SimpleDateFormat; // untuk tgl
import java.util.Date;


public class DataBarang extends JFrame{
// label Txfield Data Barang
	JLabel Lkd_brg		 = new JLabel("Kode Barang");
	JTextField txKdBrg	 = new JTextField();
	
	JLabel Lnm_brg		 = new JLabel("Nama Barang");
	JTextField txNmBrg   = new JTextField();
	
	JLabel Lstok	 	 = new JLabel("Stok Barang");
	JTextField txStok	 = new JTextField();
	
	JLabel Lket			 = new JLabel("Keterang");
	JTextArea txKet	 	 = new JTextArea();
	
	JLabel Lharga 		 = new JLabel("Harga");
	JTextField txHarga   = new JTextField();
	
	JLabel Ltgl	 		  = new JLabel("Tanggal");
//	JDate
	JDateChooser txtanggal = new JDateChooser();
	
//	Button
	JButton btnSimpan = new JButton("SIMPAN");
	JButton btnUbah	  = new JButton("UBAH");
	JButton btnReset  = new JButton("RESET");
	JButton btnHapus  = new JButton("HAPUS");
	
//	koneksi
	Connection koneksi = null;
	
//	tabel barang
	String[] header = {"TANGGAL","KODE BARANG","NAMA BARANG","STOK BARANG","HARGA /PCS","KETERANGAN"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable(model);
	JScrollPane pane = new JScrollPane(tabel);
	Dimension dimensi = new Dimension(5,0);
	
//	jPANEL
	JPanel panel1 	= new JPanel();
	JPanel panel2	= new JPanel();
	JPanel panel3 	= new JPanel();
	
	DataBarang() {
		setTitle("FORM DATA BARANG");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 624);
	}
	
	void KomponenVisual() {
		getContentPane().setLayout(null);
		
//		panel3
		getContentPane().add(panel3);
		panel3.setBackground(new Color(51, 0, 153));
		panel3.setLayout(null);
		panel3.setBounds(0, 0, 1056, 69);
		
//		PANEL 1
		getContentPane().add(panel1);
		panel1.setBackground(new Color(204, 153, 255));
		panel1.setLayout(null);
		panel1.setBounds(0, 70, 280, 528);
		
		panel1.add(Ltgl);
		Ltgl.setFont(new Font("Calibri", Font.PLAIN, 14));
		Ltgl.setBounds(24, 25, 88, 14);
		panel1.add(txtanggal);
		txtanggal.setDateFormatString("yyyy-MM-dd");
		txtanggal.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtanggal.setBounds(24, 44, 214, 34);
	
		panel1.add(Lkd_brg);
		Lkd_brg.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lkd_brg.setBounds(24, 89, 88, 14);
		panel1.add(txKdBrg);
		txKdBrg.setFont(new Font("Calibri", Font.PLAIN, 16));
		txKdBrg.setBounds(24, 108, 135, 34);
		
		panel1.add(Lnm_brg);
		Lnm_brg.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lnm_brg.setBounds(24, 153, 123, 14);
		panel1.add(txNmBrg);
		txNmBrg.setFont(new Font("Calibri", Font.PLAIN, 16));
		txNmBrg.setBounds(24, 172, 214, 34);
		
		panel1.add(Lstok);
		Lstok.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lstok.setBounds(24, 219, 69, 14);
		panel1.add(txStok);
		txStok.setFont(new Font("Calibri", Font.PLAIN, 16));
		txStok.setBounds(24, 238, 214, 34);
		
		panel1.add(Lket);
		Lket.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lket.setBounds(30, 347, 69, 14);
		panel1.add(txKet);
		txKet.setFont(new Font("Calibri", Font.PLAIN, 16));
		txKet.setBounds(30, 366, 208, 129);
		
		panel1.add(Lharga);
		Lharga.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lharga.setBounds(24, 283, 69, 14);
		panel1.add(txHarga);
		txHarga.setFont(new Font("Calibri", Font.PLAIN, 16));
		txHarga.setBounds(24, 302, 214, 34);
		
//		PANEL 2 tabel
		getContentPane().add(panel2);
		panel2.setLayout(null);
		panel2.setBackground(new Color(255,255,255));
		panel2.setBounds(281, 70, 751, 528);
		
		panel2.add(pane);
		pane.setBounds(0, 114, 751, 295);
		pane.setOpaque(false);//untuk transparan tbel
		pane.getViewport().setOpaque(false);//untuk transparan tbel
		
		tabel.setShowGrid(true);
		tabel.setShowVerticalLines(true);
		tabel.setIntercellSpacing(new Dimension(dimensi));
		tabel.setGridColor(Color.blue);
		
//		button
		panel2.add(btnSimpan);
		btnSimpan.setBackground(new Color(204, 153, 204));
		btnSimpan.setBounds(355, 435, 89, 34);
		
		panel2.add(btnHapus);
		btnHapus.setBackground(new Color(204, 153, 204));
		btnHapus.setBounds(553, 435, 89, 34);
		
		panel2.add(btnUbah);
		btnUbah.setBackground(new Color(204, 153, 204));
		btnUbah.setBounds(454, 435, 89, 34);
//		
		panel2.add(btnReset);
		btnReset.setBackground(new Color(204, 153, 204));
		btnReset.setBounds(652, 435, 89, 34);
		
		setVisible(true);
		koneksiDatabase();
		tampilTabel();
		
	} //end vhisyuel komponen
	
//	=========================== KONEKSI ------------------------
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
	
//	Aksi reaksi
	void AksiReaksi() {
		
//		simpan
		btnSimpan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String kalender = new SimpleDateFormat("dd-MM-yyyy").format(txtanggal.getDate());
				try {
					String sql = "insert into tb_barang2 values(?,?,?,?,?,?)";
					PreparedStatement pr = koneksi.prepareStatement(sql);
					
					pr.setString(1, kalender);
					pr.setString(2, txKdBrg.getText());
					pr.setString(3, txNmBrg.getText());
					pr.setString(4, txStok.getText());
					pr.setString(5, txHarga.getText());
					pr.setString(6, txKet.getText());
					pr.executeUpdate();
					
					
					JOptionPane.showMessageDialog(null, "Berhasil Disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
					
					tampilTabel(); //ambil data di void tampilTabel() 
					bersihData();
			
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Gagal Disimpan" 
							+ e,"Pesan", JOptionPane.INFORMATION_MESSAGE);
								System.out.println(e);
				}
				
				btnSimpan.setEnabled(true);
				txStok.setEnabled(true);
			}
		});
		
		//=========================== DELETE ------------------------
		btnHapus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = txKdBrg.getText();
				int tanya = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menghapus Data Dengan Id "+id+"?", "Konfirmasi",
						JOptionPane.YES_NO_OPTION);
				
				try {
					String sql = "delete from tb_barang2 where kd_barang=?";
					PreparedStatement pr = koneksi.prepareStatement(sql);
					
					pr.setString(1, id);
					
					pr.executeUpdate();
					pr.close();
//					koneksi.close();
					JOptionPane.showMessageDialog(null, "Data telah dihapus");
					
					tampilTabel();
					bersihData();
					
					btnSimpan.setEnabled(true);
					txStok.setEnabled(true);
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(null, "Error :" +ex,"Error",
							JOptionPane.ERROR_MESSAGE);
			}
			}});

		
//		reset
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txKdBrg.setText("");
				txNmBrg.setText("");
				txStok.setText("");
				txHarga.setText("");
				txKet.setText("");
				
				btnSimpan.setEnabled(true);
				txStok.setEnabled(true);
			}
			

		});
		
//		ubah
		btnUbah.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource()==btnUbah) {
					try {
						String sql = "update tb_barang2 set kd_barang =?, nm_barang =?, harga =?, ket =? WHERE kd_barang = ?";
						
						PreparedStatement pr = koneksi.prepareStatement(sql);
						
						pr.setString(1,txKdBrg.getText());
						pr.setString(2, txNmBrg.getText());
						pr.setString(3, txHarga.getText());
						pr.setString(4, txKet.getText());
//						pr.setString(6, txAlamat.getText());
						pr.setString(5,txKdBrg.getText());
						
						pr.executeUpdate(); 
						pr.close();
						tampilTabel(); //ambil data di void tampilTabel() 
						bersihData(); //ambil data di void bersihData()

						JOptionPane.showMessageDialog(null,"Data Sudah TerUpdate",
								"Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
						
						btnSimpan.setEnabled(true);
						
						txStok.setEnabled(true);
						
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Data gagal di update"+e,
								"Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
				}
					}
				
			}
		});
		
//		klik baris tabel Mouse Selected Row
		tabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int pilih = tabel.getSelectedRow();
				TableModel modell = tabel.getModel();
			
				txStok.setEnabled(false);
				
				if(pilih >= 100) {
					return;
				}
				
//				Date date = SimpleDateFormat()
//				JDateChooser.setDate(date);
//				String tanggal = (String) model.getValueAt(pilih, 0);
//				txtanggal.setText(tanggal);
				String kdBarang = (String) model.getValueAt(pilih, 1);
				txKdBrg.setText(kdBarang);
				String nama = (String) model.getValueAt(pilih, 2);
				txNmBrg.setText(nama);
				String jumlah = (String) model.getValueAt(pilih, 3);
				txStok.setText(jumlah);
				String harga = (String) model.getValueAt(pilih, 4);
				txHarga.setText(harga);
				String ket = (String) model.getValueAt(pilih, 5);
				txKet.setText(ket);
				
				btnSimpan.setEnabled(false);
				
			}
		});	
		
	} //end aksi

//	-------=-=-=-=-==- BERSIH DATA -=-=-=-
	void bersihData() {
//		txtanggal.update("");
		txKdBrg.setText("");
		txNmBrg.setText("");
		txStok.setText("");
		txHarga.setText("");
		txKet.setText("");
	}	
	
//	============================-=-=-=-=-= TAMPIL TABEL ===-=-=
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
	
	
	
	public static void main(String[] syauleee) {
		DataBarang DBrg = new DataBarang();
		DBrg.KomponenVisual();
		DBrg.AksiReaksi();
	}
	
}
