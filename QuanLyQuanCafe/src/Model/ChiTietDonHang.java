package Model;

public class ChiTietDonHang {
	private int ID;
	private int MaDonHang ;
	private int MaMon ;
	private int SoLuong;
	private int Gia;
	
	public ChiTietDonHang() {
		
	}
	public ChiTietDonHang(int iD, int maDonHang, int maMon, int soLuong, int gia) {
		super();
		ID = iD;
		MaDonHang = maDonHang;
		MaMon = maMon;
		SoLuong = soLuong;
		Gia = gia;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getMaDonHang() {
		return MaDonHang;
	}
	public void setMaDonHang(int maDonHang) {
		MaDonHang = maDonHang;
	}
	public int getMaMon() {
		return MaMon;
	}
	public void setMaMon(int maMon) {
		MaMon = maMon;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public int getGia() {
		return Gia;
	}
	public void setGia(int gia) {
		Gia = gia;
	}
	@Override
	public String toString() {
		return "ChiTietDonHang [ID=" + ID + ", MaDonHang=" + MaDonHang + ", MaMon=" + MaMon + ", SoLuong=" + SoLuong
				+ ", Gia=" + Gia + "]";
	}
	
	
}	
