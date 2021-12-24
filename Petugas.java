import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

public class Petugas extends JFrame{
//==Label-dan-Textfield-PETUGAS==================================-=-=-=-=
	JLabel Ltambah 	 = new JLabel("Tambah");
	JLabel Lpetugas	 = new JLabel("PETUGAS");
	
	JLabel Lid 		 = new JLabel("ID");
	JTextField txId	 = new JTextField();
	
	JLabel Lnama 	 = new JLabel("Nama");
	JTextField txNama= new JTextField();
	
	JLabel LUsername = new JLabel("Username");
	JTextField txUsername = new JTextField();
	
	JLabel LPassword = new JLabel("Passwors");
	JTextField txPassword = new JTextField();
	
	JLabel Ljk 		 = new JLabel("Jenis Kelamin");
	JRadioButton Rdpria = new JRadioButton("Pria");
	JRadioButton Rdwanita = new JRadioButton("Wanita");
	ButtonGroup Ggender = new  ButtonGroup();
	
	JLabel LNoHP	 = new JLabel("NO HP");
	JTextField txNoHP = new JTextField();
	
	JLabel LAlamat	 = new JLabel("Alamat");
	JTextField txAlamat = new JTextField();
	
//==Tombol-aksi-PETUGAS=================================-=-=
	JButton btnSimpan = new JButton("SIMPAN");
	JButton btnUbah	  = new JButton("UBAH");
	JButton btnReset  = new JButton("RESET");
	JButton btnHapus  = new JButton("HAPUS");

//==Koneksi-PETUGAS==================================-=-=-=-=
	Connection koneksi = null;
	
//	TABEL PETUGAS ====================================-=-=-=-
	String[] header = {"ID","NAMA","USERNAME","PASSWORD","JENIS KELAMIN","HP","ALAMAT"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable(model);
	JScrollPane pane = new JScrollPane(tabel);
	Dimension dimensi = new Dimension(15,5);
	
//==       JPANEL-PETUGAS==================================-=-=-=-=
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	
	Petugas(){
		setTitle("FORM PETUGAS");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 594);
	}
	
	void KomponenVisual() {
		getContentPane().setLayout(null);
		
//==Label-dan-Textfield-  (KomponenV) PANEL -PETUGAS==================================-=-=-=-=
		getContentPane().add(panel);
		panel.setBackground(new Color(204, 153, 255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 280, 598);
		
		panel.add(Lpetugas);
		Lpetugas.setHorizontalAlignment(SwingConstants.CENTER);
		Lpetugas.setFont(new Font("Calibri", Font.PLAIN, 24));
		Lpetugas.setBounds(53, 11, 147, 35);
		
		panel.add(Lid);
		Lid.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lid.setBounds(24, 82, 46, 14);
		panel.add(txId);
		txId.setFont(new Font("Calibri", Font.PLAIN, 16));
		txId.setBounds(24, 101, 135, 34);
		
		panel.add(Lnama);
		Lnama.setFont(new Font("Calibri", Font.PLAIN, 14));
		Lnama.setBounds(24, 146, 46, 14);
		panel.add(txNama);
		txNama.setFont(new Font("Calibri", Font.PLAIN, 16));
		txNama.setBounds(24, 165, 214, 34);
		
		panel.add(LUsername);
		LUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		LUsername.setBounds(24, 212, 69, 14);
		panel.add(txUsername);
		txUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		txUsername.setBounds(24, 231, 214, 34);
		
		panel.add(LPassword);
		LPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		LPassword.setBounds(24, 276, 69, 14);
		panel.add(txPassword);
		txPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		txPassword.setBounds(24, 295, 214, 34);
		
		panel.add(Ljk);
		Ljk.setFont(new Font("Calibri", Font.PLAIN, 14));
		Ljk.setBounds(24, 336, 88, 14);
		panel.add(Rdpria);
		Rdpria.setFont(new Font("Calibri", Font.PLAIN, 16));
		Rdpria.setBackground(new Color(204, 153, 255));
		Rdpria.setBounds(24, 350, 56, 32);
		panel.add(Rdwanita);
		Rdwanita.setFont(new Font("Calibri", Font.PLAIN, 16));
		Rdwanita.setBackground(new Color(204, 153, 255));
		Rdwanita.setBounds(129, 349, 77, 34);
		
		panel.add(LNoHP);
		LNoHP.setFont(new Font("Calibri", Font.PLAIN, 14));
		LNoHP.setBounds(24, 390, 46, 14);
		panel.add(txNoHP);
		txNoHP.setFont(new Font("Calibri", Font.PLAIN, 16));
		txNoHP.setBounds(24, 408, 214, 34);
		
		panel.add(LAlamat);
		LAlamat.setFont(new Font("Calibri", Font.PLAIN, 14));
		LAlamat.setBounds(24, 452, 46, 14);
		panel.add(txAlamat);
		txAlamat.setFont(new Font("Calibri", Font.PLAIN, 16));
		txAlamat.setBounds(24, 471, 214, 34);
	
//==tampil-Tabel  (KomponenV) PANEL  2  -PETUGAS==================================-=-=-=-=
		getContentPane().add(panel2);
		panel2.setLayout(null);
		panel2.setBackground(new Color(255,255,255));
		panel2.setBounds(281, 0, 751, 598);
		
		panel2.add(pane);
		pane.setBounds(0, 114, 751, 295);
		pane.setOpaque(false);//untuk transparan tbel
		pane.getViewport().setOpaque(false);//untuk transparan tbel
		
		tabel.setShowGrid(true);
		tabel.setShowVerticalLines(true);
		tabel.setIntercellSpacing(new Dimension(dimensi));
		tabel.setGridColor(Color.green);
		
//		========TOMBOL AKSI ========================
		panel2.add(btnSimpan);
		btnSimpan.setBackground(new Color(204, 153, 204));
		btnSimpan.setBounds(355, 435, 89, 34);
		
		panel2.add(btnHapus);
		btnHapus.setBackground(new Color(204, 153, 204));
		btnHapus.setBounds(553, 435, 89, 34);
		
		panel2.add(btnUbah);
		btnUbah.setBackground(new Color(204, 153, 204));
		btnUbah.setBounds(454, 435, 89, 34);
		
		panel2.add(btnReset);
		btnReset.setBackground(new Color(204, 153, 204));
		btnReset.setBounds(652, 435, 89, 34);
		
		setVisible(true);
		koneksiDatabase();
		tampilTabel();
	
	} //end komponen Visuel
	
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
	
//	=========================== AKSI REAKSI ------------------------
	void AksiReaksi() {
		//=========================== SAVE ------------------------
		btnSimpan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				String sumber = fc.getSelectedFile().getPath();
				try {
					String sql = "insert into pegawai values(?,?,?,?,?,?,?)";
					PreparedStatement pr = koneksi.prepareStatement(sql);
					
					pr.setString(1, txId.getText());
					pr.setString(2, txNama.getText());
					pr.setString(3, txUsername.getText());
					pr.setString(4, txPassword.getText());
					
					if (Rdpria.isSelected()==true) {
						pr.setString(5, Rdpria.getText());						
					}else if (Rdwanita.isSelected()==true) {
						pr.setString(5, Rdwanita.getText());
					}
					pr.setString(6, txNoHP.getText());
					pr.setString(7, txAlamat.getText());
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
			}
		});
		
		//=========================== DELETE ------------------------
		btnHapus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = txId.getText();
				int tanya = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menghapus Data Dengan Id "+id+"?", "Konfirmasi",
						JOptionPane.YES_NO_OPTION);
				
				try {
					String sql = "delete from pegawai where id_pegawai=?";
					PreparedStatement pr = koneksi.prepareStatement(sql);
					
					pr.setString(1, id);
					
					pr.executeUpdate();
					pr.close();
//					koneksi.close();
					JOptionPane.showMessageDialog(null, "Data telah dihapus");
					
					tampilTabel();
					bersihData();
					btnSimpan.setEnabled(true);
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(null, "Error :" +ex,"Error",
							JOptionPane.ERROR_MESSAGE);
			}
			}});

		//=========================== UBAH ------------------------
		btnUbah.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource()==btnUbah) {
					try {
						String sql = "update pegawai set nama =?, username =?, password =?, "
								+ "jenis_kelamin =?, no_hp =?, alamat =? WHERE id_pegawai = ?";
						
						PreparedStatement pr = koneksi.prepareStatement(sql);
						
						pr.setString(1,txNama.getText());
						pr.setString(2, txUsername.getText());
						pr.setString(3, txPassword.getText());
						
						if (Rdpria.isSelected()==true) {
							pr.setString(4, Rdpria.getText());						
						}else if (Rdwanita.isSelected()==true) {
							pr.setString(4, Rdwanita.getText());
						}
						pr.setString(5, txNoHP.getText());
						pr.setString(6, txAlamat.getText());
						pr.setString(7,txId.getText());
						
						pr.executeUpdate(); 
						pr.close();
						tampilTabel(); //ambil data di void tampilTabel() 
						bersihData(); //ambil data di void bersihData()

						JOptionPane.showMessageDialog(null,"Data Sudah TerUpdate",
								"Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
						
						btnSimpan.setEnabled(true);
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Data gagal di update"+e,
								"Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
				}
					}
				
			}
		});
		
//		reset
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txId.setText("");
				txNama.setText("");
				txUsername.setText("");
				txPassword.setText("");
				Rdpria.setSelected(false);
				Rdwanita.setSelected(false);
				txNoHP.setText("");
				txAlamat.setText("");
				
				btnSimpan.setEnabled(true);
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
				if(pilih >= 100) {
					return;
				}
				
				String id_petugas = (String) model.getValueAt(pilih, 0);
				txId.setText(id_petugas);
				String nama = (String) model.getValueAt(pilih, 1);
				txNama.setText(nama);
				String username = (String) model.getValueAt(pilih, 2);
				txUsername.setText(username);
				String password = (String) model.getValueAt(pilih, 3);
				txPassword.setText(password);
				
				String sex = model.getValueAt(pilih, 4).toString();
				if (sex.equals("Pria")) {
					Rdpria.setSelected(true);
					
				} else {
					Rdwanita.setSelected(true);
				}

				String txhp = (String) model.getValueAt(pilih, 5);
				txNoHP.setText(txhp);
				String alamat = (String) model.getValueAt(pilih, 6);
				txAlamat.setText(alamat);
				
				btnSimpan.setEnabled(false);
				
			}
		});
		
		
	} //tutup reaksi
	
	
//	-------=-=-=-=-==- BERSIH DATA -=-=-=-
	void bersihData() {
		txId.setText("");
		txNama.setText("");
		txUsername.setText("");
		txPassword.setText("");
		Rdpria.setSelected(false);
		Rdwanita.setSelected(false);
		txNoHP.setText("");
		txAlamat.setText("");
		
	}
	
//	============================-=-=-=-=-= TAMPIL TABEL ===-=-=
	void tampilTabel() {
		hapusTabel(); //ambil data di void hapus tabel()
		try {
			Statement state = koneksi.createStatement(); 
			String sql = "select * from pegawai "; 
			ResultSet rs = state.executeQuery(sql);
	
			while(rs.next()) {
				Object obj[] = new Object[7];
				obj[0] = rs.getString(1); //untuk id 
				obj[1] = rs.getString(2); //nama 
				obj[2] = rs.getString(3); //user 
				obj[3] = rs.getString(4); //pass 
				obj[4] = rs.getString(5); //jk 
				obj[5] = rs.getString(6); //hp 
				obj[6] = rs.getString(7); //alamat

			model.addRow(obj);
		}}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
//	-------=-=-=-=-==- HAPUS TABEL -=-=-=-
	
	void hapusTabel() {
		int row = model.getRowCount(); 
		for(int i = 0;i< row;i++) {
			model.removeRow(0);
	}}
	
	
	public static void main(String[] syauli) {
		Petugas ptg = new Petugas();
		ptg.KomponenVisual();
		ptg.AksiReaksi();
	}
}






















