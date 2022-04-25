package nhom29.gk_quanlithietbi.model;

public class LoaiThietBi {
    private int maLoai;
    private String tenLoai;

    public LoaiThietBi() {
    }

    public LoaiThietBi(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }


}
