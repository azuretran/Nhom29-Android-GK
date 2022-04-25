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
import nhom29.gk_quanlithietbi.model.LoaiThietBi;

public class LoaiThietBiSpinnerAdapter extends ArrayAdapter<LoaiThietBi> {
    Context context;
    ArrayList<LoaiThietBi> lists;
    TextView tvMaLoaiThietBi, tvTenLoaiThietBi;

    public LoaiThietBiSpinnerAdapter(@NonNull Context context, ArrayList<LoaiThietBi> lists) {
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
            v = inflater.inflate(R.layout.loai_thiet_bi_item_spinner, null);
        }

        final LoaiThietBi item = lists.get(position);
        if (item != null) {
            tvMaLoaiThietBi = v.findViewById(R.id.tvMaLoaiThietBiSp);
            tvMaLoaiThietBi.setText(item.getMaLoai() + ". ");
            tvTenLoaiThietBi = v.findViewById(R.id.tvTenLoaiThietBiSp);
            tvTenLoaiThietBi.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_thiet_bi_item_spinner, null);
        }
        final LoaiThietBi item = lists.get(position);

        if (item != null) {
            tvMaLoaiThietBi = v.findViewById(R.id.tvMaLoaiThietBiSp);
            tvMaLoaiThietBi.setText(item.getMaLoai() + ". ");
            tvTenLoaiThietBi = v.findViewById(R.id.tvTenLoaiThietBiSp);
            tvTenLoaiThietBi.setText(item.getTenLoai());
        }
        return v;
    }
}
