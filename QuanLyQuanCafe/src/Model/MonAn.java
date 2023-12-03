package Model;

public class MonAn {
	private int MaMon;
	private String TenMon;
	private String LoaiMon;
	private String Gia;
	
	public MonAn() {
		
	}
	public MonAn(int maMon, String tenMon, String loaiMon, String gia) {
		super();
		MaMon = maMon;
		TenMon = tenMon;
		LoaiMon = loaiMon;
		Gia = gia;
	}
	public int getMaMon() {
		return MaMon;
	}
	public void setMaMon(int maMon) {
		MaMon = maMon;
	}
	public String getTenMon() {
		return TenMon;
	}
	public void setTenMon(String tenMon) {
		TenMon = tenMon;
	}
	public String getLoaiMon() {
		return LoaiMon;
	}
	public void setLoaiMon(String loaiMon) {
		LoaiMon = loaiMon;
	}
	public String getGia() {
		return Gia;
	}
	public void setGia(String gia) {
		Gia = gia;
	}
	@Override
	public String toString() {
		return "MonAn [MaMon=" + MaMon + ", TenMon=" + TenMon + ", LoaiMon=" + LoaiMon + ", Gia=" + Gia + "]";
	}
	
	
}
