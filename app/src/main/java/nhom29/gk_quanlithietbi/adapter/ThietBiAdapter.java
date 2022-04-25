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

import java.util.List;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.dao.LoaiThietBiDAO;
import nhom29.gk_quanlithietbi.fragment.ThietBiFragment;
import nhom29.gk_quanlithietbi.model.LoaiThietBi;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ThietBiAdapter extends ArrayAdapter<ThietBi> {
    Context context;
    ThietBiFragment fragment;
    List<ThietBi> list;

    TextView tvMaTB, tvTenTB, tvXuatXu, tvLoai;
    ImageView imgDel;

    public ThietBiAdapter(@NonNull Context context, ThietBiFragment fragment, List<ThietBi> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thiet_bi_item, null);
        }

        final ThietBi item = list.get(position);
        if (item != null) {
            LoaiThietBiDAO loaiThietBiDAO = new LoaiThietBiDAO(context);
            LoaiThietBi loaiThietBi = loaiThietBiDAO.getID(String.valueOf(item.getMaLoai()));

            tvMaTB = v.findViewById(R.id.tvMaTB);
            tvMaTB.setText("Mã Thiết Bị: " + item.getMaTB());
            tvTenTB = v.findViewById(R.id.tvTenTB);
            tvTenTB.setText("Tên Thiết Bị: " + item.getTenTB());
            tvXuatXu = v.findViewById(R.id.tvXuatXu);
            tvXuatXu.setText("Xuất Xứ: " + item.getXuatXu());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Thiết Bị: " + loaiThietBi.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //xoa
                fragment.xoa(String.valueOf(item.getMaTB()));
            }
        });
        return v;
    }
}
