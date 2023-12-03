package Model;

public class Ban {
	private int MaBan;
	private String TenBan;
	private String TrangThai;
	
	public Ban() {
		
	}
	public Ban(int maBan, String tenBan, String trangThai) {
		super();
		MaBan = maBan;
		TenBan = tenBan;
		TrangThai = trangThai;
	}
	public int getMaBan() {
		return MaBan;
	}
	public void setMaBan(int maBan) {
		MaBan = maBan;
	}
	public String getTenBan() {
		return TenBan;
	}
	public void setTenBan(String tenBan) {
		TenBan = tenBan;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	@Override
	public String toString() {
		return "Ban [MaBan=" + MaBan + ", TenBan=" + TenBan + ", TrangThai=" + TrangThai + "]";
	}
	
	
}
