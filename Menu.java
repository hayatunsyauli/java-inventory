import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
		
	JButton btnPetugas = new JButton("PETUGAS");
	JButton btnDataBarang = new JButton("STOK BARANG");
	JButton btnBrgMasuk = new JButton();
	JButton btnBrgKeluar = new JButton();
	JButton btnTentang = new JButton("TENTANG");
	JButton btnLaporan = new JButton("LAPORAN");
	
	JMenuBar menuBar = new JMenuBar();
	JMenu mnTransaksi = new JMenu("Transaksi");
	JMenu mnInfo 	= new JMenu("Info");
	JMenuItem 	brangMasuk = new JMenuItem("Transaksi Barang Masuk");
	JMenuItem 	brangKeluar = new JMenuItem("Transaksi Barang Keluar");
	JMenuItem  	tentang 	= new JMenuItem("Tentang");
	
	Menu(){
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 662);
	}
	
	void KomponenVisual() {
		getContentPane().setLayout(null);
		
		getContentPane().add(panel);
		panel.setBackground(new Color(204, 153, 255));
		panel.setBounds(0, 0, 1418, 84);
		panel.setLayout(null);
		
		panel.add(menuBar);
		menuBar.setBounds(0, 0, 97, 21);
		panel.add(menuBar);
		
		menuBar.add(mnTransaksi);
		mnTransaksi.add(brangMasuk);
		mnTransaksi.add(brangKeluar);
		
		menuBar.add(mnInfo);
		mnInfo.add(tentang);
		
		
		
		getContentPane().add(panel2);
		panel2.setBackground(new Color(51, 0, 153));
		panel2.setBounds(0, 83, 931, 540);
		panel2.setLayout(null);
		
		panel2.add(btnPetugas);
		btnPetugas.setForeground(new Color(255, 255, 255));
		btnPetugas.setBackground(new Color(153, 51, 204));
		btnPetugas.setBounds(261, 139, 172, 106);
		
		panel2.add(btnDataBarang);
		btnDataBarang.setForeground(new Color(255, 255, 255));
		btnDataBarang.setBackground(new Color(153, 51, 204));
		btnDataBarang.setBounds(443, 139, 172, 106);
		
		panel2.add(btnTentang);
		btnTentang.setForeground(new Color(255, 255, 255));
		btnTentang.setBackground(new Color(153, 51, 204));
		btnTentang.setBounds(443, 256, 172, 106);
		
		panel2.add(btnLaporan);
		btnLaporan.setForeground(new Color(255, 255, 255));
		btnLaporan.setBackground(new Color(153, 51, 204));
		btnLaporan.setBounds(261, 256, 172, 106);
		
		setVisible(true);
	}
	
	void AksiReaksi() {
		
		btnPetugas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Petugas ptg = new Petugas();
				ptg.KomponenVisual();
				ptg.AksiReaksi();
				
			}
		});
		
		btnDataBarang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataBarang DBrg = new DataBarang();
				DBrg.KomponenVisual();
				DBrg.AksiReaksi();
			}
		});
		
	}


	public static void main(String[] args) {
		Menu MU = new Menu();
		MU.KomponenVisual();
		MU.AksiReaksi();		
	}

}
