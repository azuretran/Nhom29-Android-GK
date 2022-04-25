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
import nhom29.gk_quanlithietbi.fragment.PhongHocFragment;
import nhom29.gk_quanlithietbi.model.PhongHoc;

public class PhongHocAdapter extends ArrayAdapter<PhongHoc> {
    private Context context;
    PhongHocFragment fragment;
    private ArrayList<PhongHoc> lists;
    TextView tvMaPhong, tvLoaiPhong, tvTang;
    ImageView imgDel;

    public PhongHocAdapter(@NonNull Context context, PhongHocFragment fragment, ArrayList<PhongHoc> lists) {
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
            v = inflater.inflate(R.layout.phong_hoc_item, null);
        }

        final PhongHoc item = lists.get(position);
        if (item != null) {
            tvMaPhong = v.findViewById(R.id.tvMaPhong);
            tvMaPhong.setText("Mã phòng học: " + item.getMaPhong());
            tvLoaiPhong = v.findViewById(R.id.tvLoaiPhong);
            tvLoaiPhong.setText("Loại phòng: " + item.getLoaiPhong());
            tvTang = v.findViewById(R.id.tvTang);
            tvTang.setText("Tầng: " + item.getTang());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.getMaPhong()));
            }
        });

        return v;
    }
}
