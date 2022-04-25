package nhom29.gk_quanlithietbi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.fragment.LoaiThietBiFragment;
import nhom29.gk_quanlithietbi.fragment.PhongHocFragment;
import nhom29.gk_quanlithietbi.model.LoaiThietBi;
import nhom29.gk_quanlithietbi.model.PhongHoc;

public class LoaiThietBiAdapter extends ArrayAdapter<LoaiThietBi> {
    private Context context;
    LoaiThietBiFragment fragment;
    private ArrayList<LoaiThietBi> lists;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiThietBiAdapter(Context context, LoaiThietBiFragment fragment, ArrayList<LoaiThietBi> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_thiet_bi_item, null);
        }

        final LoaiThietBi item = lists.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã loại thiết bị: " + item.getMaLoai());
            tvTenLoai = v.findViewById(R.id.tvTenLoai);
            tvTenLoai.setText("Tên loại thiết bị: " + item.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });

        return v;
    }
}
