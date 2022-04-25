package nhom29.gk_quanlithietbi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nhom29.gk_quanlithietbi.database.DbHelper;
import nhom29.gk_quanlithietbi.model.ChiTietSuDung;

public class ChiTietSuDungDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ChiTietSuDungDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Long insert(ChiTietSuDung obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maPhong", obj.getMaPhong());
        values.put("maTB", obj.getMaTB());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("soLuong", obj.getSoLuong());
        values.put("traTB", obj.getTraTB());
        return db.insert("ChiTietSuDung", null, values);
    }

    public int update(ChiTietSuDung obj) {
        ContentValues values = new ContentValues();
        values.put("maChiTiet", obj.getMaChiTiet());
        values.put("maTT", obj.getMaTT());
        values.put("maPhong", obj.getMaPhong());
        values.put("maTB", obj.getMaTB());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("soLuong", obj.getSoLuong());
        values.put("traTB", obj.getTraTB());
        return db.update("ChiTietSuDung", values, "maChiTiet=?", new String[]{String.valueOf(obj.getMaChiTiet())});
    }

    public int delete(String id) {
        return db.delete("ChiTietSuDung", "maChiTiet=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ChiTietSuDung> getData(String sql, String... selectionArgs) {
        List<ChiTietSuDung> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ChiTietSuDung obj = new ChiTietSuDung();
            obj.setMaChiTiet(Integer.parseInt(c.getString(c.getColumnIndex("maChiTiet"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setMaPhong(Integer.parseInt(c.getString(c.getColumnIndex("maPhong"))));
            obj.setMaTB(Integer.parseInt(c.getString(c.getColumnIndex("maTB"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setTraTB(Integer.parseInt(c.getString(c.getColumnIndex("traTB"))));
            list.add(obj);
        }
        return list;
    }

    public List<ChiTietSuDung> getALL() {
        String sql = "SELECT * FROM ChiTietSuDung";
        return getData(sql);
    }

    //get data theo id
    public ChiTietSuDung getID(String id) {
        String sql = "SELECT * FROM ChiTietSuDung WHERE maChiTiet=?";
        List<ChiTietSuDung> list = getData(sql, id);

        return list.get(0);
    }

}
