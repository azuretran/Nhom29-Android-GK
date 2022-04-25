package nhom29.gk_quanlithietbi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom29.gk_quanlithietbi.database.DbHelper;
import nhom29.gk_quanlithietbi.model.LoaiThietBi;

public class LoaiThietBiDAO {
    private SQLiteDatabase db;

    public LoaiThietBiDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiThietBi obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiThietBi", null, values);
    }

    public int update(LoaiThietBi obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiThietBi", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public int delete(String id) {
        return db.delete("LoaiThietBi", "maLoai=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiThietBi> getData(String sql, String... selectionArgs) {
        List<LoaiThietBi> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
//        new ArrayList<LoaiThietBi>();
        while (c.moveToNext()) {
            LoaiThietBi obj = new LoaiThietBi();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiThietBi> getALL() {
        String sql = "SELECT * FROM LoaiThietBi";
        return getData(sql);
    }

    public LoaiThietBi getID(String id) {
        String sql = "SELECT * FROM LoaiThietBi WHERE maLoai=?";
        List<LoaiThietBi> list = getData(sql, id);
        return list.get(0);
    }
}
