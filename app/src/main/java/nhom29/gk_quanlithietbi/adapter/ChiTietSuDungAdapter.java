package nhom29.gk_quanlithietbi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.dao.PhongHocDAO;
import nhom29.gk_quanlithietbi.dao.ThietBiDAO;
import nhom29.gk_quanlithietbi.fragment.ChiTietSuDungFragment;
import nhom29.gk_quanlithietbi.model.ChiTietSuDung;
import nhom29.gk_quanlithietbi.model.PhongHoc;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ChiTietSuDungAdapter extends ArrayAdapter<ChiTietSuDung> {
    private Context context;
    ChiTietSuDungFragment fragment;
    private ArrayList<ChiTietSuDung> lists;
    TextView tvMaChiTiet, tvLoaiPhong, tvTenTB, tvTraTB, tvSoLuong, tvNgay;
    ImageView imgDel;
    ThietBiDAO thietBiDAO;
    PhongHocDAO phongHocDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ChiTietSuDungAdapter(@NonNull Context context, ChiTietSuDungFragment fragment, ArrayList<ChiTietSuDung> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.chi_tiet_su_dung_item,null);
        }

        final  ChiTietSuDung item = lists.get(position);
        if (item!=null){
            tvMaChiTiet = v.findViewById(R.id.tvMaChiTiet);
            tvMaChiTiet.setText("Mã: "+item.getMaChiTiet());

            thietBiDAO = new ThietBiDAO(context);
            ThietBi thietBi = thietBiDAO.getID((String.valueOf(item.getMaTB())));
            tvTenTB = v.findViewById(R.id.tvTenTB);
            tvTenTB.setText("Tên Thiết Bị"+thietBi.getTenTB());

            phongHocDAO = new PhongHocDAO(context);
            PhongHoc phongHoc = phongHocDAO.getID(String.valueOf(item.getMaPhong()));
            tvLoaiPhong = v.findViewById(R.id.tvLoaiPhong);
            tvLoaiPhong.setText("Loại Phòng"+phongHoc.getLoaiPhong());

            tvSoLuong = v.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText("Số lượng"+item.getSoLuong());

            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày mượn: "+sdf.format(item.getNgay()));

            tvTraTB = v.findViewById(R.id.tvTraTB);
            if (item.getTraTB()==1){
                tvTraTB.setTextColor(Color.BLUE);
                tvTraTB.setText("Đã trả");
            }else{
                tvTraTB.setTextColor(Color.RED);
                tvTraTB.setText("Chưa trả");
            }

            imgDel.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goi phuong thuc xoa
            }
        });

        return v;
    }
}
