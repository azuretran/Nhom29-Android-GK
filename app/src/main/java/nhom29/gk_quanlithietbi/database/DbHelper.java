package nhom29.gk_quanlithietbi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QL_ThietBi";
    private static final int DB_VERSION = 1;

    static final String INSERT_LOAI_THIET_BI = "insert into LoaiThietBi (tenLoai) values" +
            "('May tinh')";

    static final String INSERT_THIET_BI = "insert into ThietBi (tenTB, xuatXu, maLoai) values" +
            "('Test1', 'VietNam', '1')";
    static final String INSERT_TAI_KHOAN =
            "insert into TaiKhoan (maTT, hoTen, matKhau) values" +
                    "('admin', 'Duy Admin','123') ";

    static final String CREATE_TABLE_TAI_KHOAN =
            "create table TaiKhoan (" +
                    "maTT TEXT PRIMARY KEY," +
                    "hoTen TEXT NOT NULL," +
                    "matKhau TEXT NOT NULL)";

    static final String CREATE_TABLE_PHONG_HOC =
            "create table PhongHoc (" +
                    "maPhong INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "loaiPhong TEXT NOT NULL," +
                    "tang TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_THIET_BI =
            "create table LoaiThietBi (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tenLoai TEXT NOT NULL)";

    static final String CREATE_TABLE_THIET_BI =
            "create table ThietBi (" +
                    "maTB INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tenTB TEXT NOT NULL," +
                    "xuatXu TEXT NOT NULL," +
                    "maLoai INTEGER REFERENCES LoaiSach(maloai))";

    static final String CREATE_TABLE_CHI_TIET_SU_DUNG =
            "create table ChiTietSuDung (" +
                    "maChiTiet INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "maTT TEXT REFERENCES TaiKhoan (maTT)," +
                    "maPhong INTEGER REFERENCES PhongHoc(maPhong)," +
                    "maTB INTEGER REFERENCES ThietBi(maTB)," +
                    "soLuong INTEGER NOT NULL ," +
                    "ngay DATE NOT NULL," +
                    "traTB INTEGER NOT NULL )";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Tai Khoan
        db.execSQL(CREATE_TABLE_TAI_KHOAN);
        //Tao bang Phong Hoc
        db.execSQL(CREATE_TABLE_PHONG_HOC);
        //Tao bang Loai Thiet Bi
        db.execSQL(CREATE_TABLE_LOAI_THIET_BI);
        //Tao bang Thiet bi
        db.execSQL(CREATE_TABLE_THIET_BI);
        //Tao bang chi tiet su dung
        db.execSQL(CREATE_TABLE_CHI_TIET_SU_DUNG);

        db.execSQL(INSERT_LOAI_THIET_BI);
        db.execSQL(INSERT_THIET_BI);
        //insert tai khoan admin mac dinh
        db.execSQL(INSERT_TAI_KHOAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableTaiKhoan = "drop table if exists TaiKhoan";
        db.execSQL(dropTableTaiKhoan);

        String dropTablePhongHoc = "drop table if exists PhongHoc";
        db.execSQL(dropTablePhongHoc);

        String dropTableLoaiThietBi = "drop table if exists LoaiThietBi";
        db.execSQL(dropTableLoaiThietBi);

        String dropTableThietBi = "drop table if exists ThietBi";
        db.execSQL(dropTableThietBi);

        String dropTableChiTietSuDung = "drop table if exists ChiTietSuDung";
        db.execSQL(dropTableChiTietSuDung);

        onCreate(db);
    }
}
