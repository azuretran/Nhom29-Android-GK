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
//import nhom29.gk_quanlithietbi.model.LoaiThietBi;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ThietBiSpinnerAdapter extends ArrayAdapter<ThietBi> {
    private Context context;
    private ArrayList<ThietBi> lists;
    TextView tvMaTB, tvTenTB;

    public ThietBiSpinnerAdapter(@NonNull Context context, ArrayList<ThietBi> lists) {
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
            v = inflater.inflate(R.layout.thiet_bi_item_spinner, null);
        }

        final ThietBi item = lists.get(position);
        if (item != null) {
            tvMaTB = v.findViewById(R.id.tvMaTBSp);
            tvMaTB.setText(item.getMaTB() + ". ");
            tvTenTB = v.findViewById(R.id.tvTenTBSp);
            tvTenTB.setText(item.getTenTB());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thiet_bi_item_spinner, null);
        }
        final ThietBi item = lists.get(position);

        if (item != null) {
            tvMaTB = v.findViewById(R.id.tvMaTBSp);
            tvMaTB.setText(item.getMaTB() + ". ");
            tvTenTB = v.findViewById(R.id.tvTenTBSp);
            tvTenTB.setText(item.getTenTB());
        }
        return v;
    }
}
