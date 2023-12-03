package Model;

public class DonHang {
	private int MaDonHang;
	private String ThoiGian;
	private String TrangThai;
	
	public DonHang() {
		
	}
	public DonHang(int maDonHang, String thoiGian, String trangThai) {
		super();
		MaDonHang = maDonHang;
		ThoiGian = thoiGian;
		TrangThai = trangThai;
	}
	public int getMaDonHang() {
		return MaDonHang;
	}
	public void setMaDonHang(int maDonHang) {
		MaDonHang = maDonHang;
	}
	public String getThoiGian() {
		return ThoiGian;
	}
	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	@Override
	public String toString() {
		return "DonHang [MaDonHang=" + MaDonHang + ", ThoiGian=" + ThoiGian + ", TrangThai=" + TrangThai + "]";
	}
	
	
	
}
