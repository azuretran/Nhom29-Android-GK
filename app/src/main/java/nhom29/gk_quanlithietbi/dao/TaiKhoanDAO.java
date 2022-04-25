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
import nhom29.gk_quanlithietbi.model.TaiKhoan;

public class TaiKhoanDAO {
    private SQLiteDatabase db;

    public TaiKhoanDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(TaiKhoan obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("TaiKhoan", null, values);
    }

    public int updatePass(TaiKhoan obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("TaiKhoan", values, "maTT=?", new String[]{obj.getMaTT()});
    }

    public int delete(String id) {
        return db.delete("TaiKhoan", "maTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<TaiKhoan> getData(String sql, String... selectionArgs) {
        List<TaiKhoan> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            TaiKhoan obj = new TaiKhoan();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            Log.i("//======", obj.toString());
            list.add(obj);
        }
        return list;
    }

    public List<TaiKhoan> getAll() {
        String sql = "SELECT * FROM TaiKhoan";
        return getData(sql);
    }

    //get data theo id
    public TaiKhoan getID(String id) {
        String sql = "SELECT * FROM TaiKhoan WHERE maTT= ?";
        List<TaiKhoan> list = getData(sql, id);
        return list.get(0);
    }

    //check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE maTT=? AND matKhau=?";
        List<TaiKhoan> list = getData(sql, id, password);
        if (list.size() == 0)
            return -1;

        return 1;
    }
}