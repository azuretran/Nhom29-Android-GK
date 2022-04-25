package nhom29.gk_quanlithietbi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom29.gk_quanlithietbi.database.DbHelper;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ThietBiDAO {
    private SQLiteDatabase db;

    public ThietBiDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThietBi obj) {
        ContentValues values = new ContentValues();
        values.put("tenTB", obj.getTenTB());
        values.put("xuatXu", obj.getXuatXu());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("ThietBi", null, values);
    }

    public long update(ThietBi obj) {
        ContentValues values = new ContentValues();
        values.put("tenTB", obj.getTenTB());
        values.put("xuatXu", obj.getXuatXu());
        values.put("maLoai", obj.getMaLoai());
        return db.update("ThietBi", values, "maTB=?", new String[]{String.valueOf(obj.getMaTB())});
    }

    public int delete(String id) {
        return db.delete("ThietBi", "maTB=?", new String[]{id});
    }

    //get data nhieu tham so
    @SuppressLint("Range")
    public List<ThietBi> getData(String sql, String... selectionArgs) {
        List<ThietBi> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThietBi obj = new ThietBi();
            obj.setMaTB(Integer.parseInt(c.getString(c.getColumnIndex("maTB"))));
            obj.setTenTB(c.getString(c.getColumnIndex("tenTB")));
            obj.setXuatXu(c.getString(c.getColumnIndex("xuatXu")));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }

    //get tat ca data
    public List<ThietBi> getAll() {
        String sql = "SELECT * FROM ThietBi";
        return getData(sql);
    }

    //get data theo id
    public ThietBi getID(String id) {
        String sql = "SELECT * FROM ThietBi WHERE maTB=?";
        List<ThietBi> list = getData(sql, id);
        return list.get(0);
    }
}
