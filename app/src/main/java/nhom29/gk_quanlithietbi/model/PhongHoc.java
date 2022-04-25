package nhom29.gk_quanlithietbi.model;

public class PhongHoc {
    private int maPhong;
    private String loaiPhong;
    private String tang;

    public PhongHoc() {
    }

    public PhongHoc(int maPhong, String loaiPhong, String tang) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.tang = tang;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    @Override
    public String toString() {
        return "PhongHoc{" +
                "maPhong=" + maPhong +
                ", loaiPhong='" + loaiPhong + '\'' +
                ", tang='" + tang + '\'' +
                '}';
    }
}
