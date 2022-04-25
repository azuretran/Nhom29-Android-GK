package nhom29.gk_quanlithietbi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nhom29.gk_quanlithietbi.database.DbHelper;
import nhom29.gk_quanlithietbi.model.PhongHoc;

public class PhongHocDAO {
    private SQLiteDatabase db;

    public PhongHocDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhongHoc obj) {
        ContentValues values = new ContentValues();
        values.put("loaiPhong", obj.getLoaiPhong());
        values.put("tang", obj.getTang());
        return db.insert("PhongHoc", null, values);
    }

    public int update(PhongHoc obj) {
        ContentValues values = new ContentValues();
        values.put("loaiPhong", obj.getLoaiPhong());
        values.put("tang", obj.getTang());
        return db.update("PhongHoc", values, "maPhong=?", new String[]{String.valueOf(obj.getMaPhong())});
    }

    public int delete(String id) {
        return db.delete("PhongHoc", "maPhong=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<PhongHoc> getData(String sql, String... selectionArgs) {
        List<PhongHoc> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhongHoc obj = new PhongHoc();
            obj.setMaPhong(Integer.parseInt(c.getString(c.getColumnIndex("maPhong"))));
            obj.setLoaiPhong(c.getString(c.getColumnIndex("loaiPhong")));
            obj.setTang(c.getString(c.getColumnIndex("tang")));
            Log.i("//========", obj.toString());
            list.add(obj);

        }
        return list;
    }

    //get tat ca data
    public List<PhongHoc> getALL() {
        String sql = "SELECT * FROM PhongHoc";
        return getData(sql);
    }

    //get data theo id
    public PhongHoc getID(String id) {
        String sql = "SELECT * FROM PhongHoc WHERE maPhong=?";
        List<PhongHoc> list = getData(sql, id);
        return list.get(0);
    }
}
