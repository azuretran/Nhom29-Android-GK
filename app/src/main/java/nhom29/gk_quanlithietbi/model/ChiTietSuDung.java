package nhom29.gk_quanlithietbi.model;

import java.util.Date;

public class ChiTietSuDung {
    private int maChiTiet;
    private String maTT;
    private int maPhong;
    private int maTB;
    private Date ngay;
    private int soLuong;
    private int traTB;

    public ChiTietSuDung() {
    }

    public ChiTietSuDung(int maChiTiet, String maTT, int maPhong, int maTB, Date ngay, int soLuong, int traTB) {
        this.maChiTiet = maChiTiet;
        this.maTT = maTT;
        this.maPhong = maPhong;
        this.maTB = maTB;
        this.ngay = ngay;
        this.soLuong = soLuong;
        this.traTB = traTB;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTraTB() {
        return traTB;
    }

    public void setTraTB(int traTB) {
        this.traTB = traTB;
    }
}
