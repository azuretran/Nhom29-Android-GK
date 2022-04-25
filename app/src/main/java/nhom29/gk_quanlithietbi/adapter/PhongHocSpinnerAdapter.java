package nhom29.gk_quanlithietbi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.model.PhongHoc;
//import nhom29.gk_quanlithietbi.model.LoaiThietBi;

public class PhongHocSpinnerAdapter extends ArrayAdapter<PhongHoc> {
   private Context context;
   private ArrayList<PhongHoc> lists;
    TextView tvMaPhong, tvLoaiPhong;

    public PhongHocSpinnerAdapter(@NonNull Context context, ArrayList<PhongHoc> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phong_hoc_item_spinner, null);
        }

        final PhongHoc item = lists.get(position);
        if (item != null) {
            tvMaPhong = v.findViewById(R.id.tvMaPhongSp);
            tvMaPhong.setText(item.getMaPhong() + ". ");
            tvLoaiPhong = v.findViewById(R.id.tvLoaiPhongSp);
            tvLoaiPhong.setText(item.getLoaiPhong());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phong_hoc_item_spinner, null);
        }
        final PhongHoc item = lists.get(position);

        if (item != null) {
            tvMaPhong = v.findViewById(R.id.tvMaPhongSp);
            tvMaPhong.setText(item.getMaPhong() + ". ");
            tvLoaiPhong = v.findViewById(R.id.tvLoaiPhongSp);
            tvLoaiPhong.setText(item.getLoaiPhong());
        }
        return v;
    }
}
