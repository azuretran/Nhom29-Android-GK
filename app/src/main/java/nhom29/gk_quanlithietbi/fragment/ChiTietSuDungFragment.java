package nhom29.gk_quanlithietbi.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.adapter.ChiTietSuDungAdapter;
import nhom29.gk_quanlithietbi.adapter.PhongHocSpinnerAdapter;
import nhom29.gk_quanlithietbi.adapter.ThietBiSpinnerAdapter;
import nhom29.gk_quanlithietbi.dao.ChiTietSuDungDAO;
import nhom29.gk_quanlithietbi.dao.PhongHocDAO;
import nhom29.gk_quanlithietbi.dao.ThietBiDAO;
import nhom29.gk_quanlithietbi.model.ChiTietSuDung;
import nhom29.gk_quanlithietbi.model.PhongHoc;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ChiTietSuDungFragment extends Fragment {
    ListView lvChiTiet;
    ArrayList<ChiTietSuDung> list;
    static ChiTietSuDungDAO dao;
    ChiTietSuDungAdapter adapter;
    ChiTietSuDung item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaChiTiet;
    Spinner spPhong, spTB;
    TextView tvNgay, tvSoLuong;
    CheckBox chkTraTB;
    Button btnSave, btnCancel;

    PhongHocSpinnerAdapter phongHocSpinnerAdapter;
    ArrayList<PhongHoc> listPhongHoc;
    PhongHocDAO phongHocDAO;
    PhongHoc phongHoc;
    int maPhong;

    ThietBiSpinnerAdapter thietBiSpinnerAdapter;
    ArrayList<ThietBi> listThietBi;
    ThietBiDAO thietBiDAO;
    ThietBi thietBi;
    int maTB, soLuong;
    int positionPhong, positionThietBi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chi_tiet_su_dung, container, false);

        lvChiTiet = v.findViewById(R.id.lvChiTiet);
        fab = v.findViewById(R.id.fab);
        dao = new ChiTietSuDungDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });

        dao = new ChiTietSuDungDAO(getActivity());
        capNhatLv();
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<ChiTietSuDung>) dao.getALL();
        adapter = new ChiTietSuDungAdapter(getActivity(), this, list);
        lvChiTiet.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.chi_tiet_su_dung_dialog);

        edMaChiTiet = dialog.findViewById(R.id.edMaChiTiet);
        spPhong = dialog.findViewById(R.id.spMaPhong);
        spTB = dialog.findViewById(R.id.spMaTB);

        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvSoLuong = dialog.findViewById((R.id.tvSoLuong));
        chkTraTB = dialog.findViewById(R.id.chkTraTB);
        btnCancel = dialog.findViewById(R.id.btnCancelCT);
        btnSave = dialog.findViewById(R.id.btnSaveCT);

        phongHocDAO = new PhongHocDAO(context);
        listPhongHoc = new ArrayList<PhongHoc>();
        listPhongHoc = (ArrayList<PhongHoc>) phongHocDAO.getALL();
        phongHocSpinnerAdapter = new PhongHocSpinnerAdapter(context, listPhongHoc);
        spPhong.setAdapter(phongHocSpinnerAdapter);
        spPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                maPhong = listPhongHoc.get(position).getMaPhong();
                Toast.makeText(context, "Chọn" + listPhongHoc.get(position).getLoaiPhong(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        thietBiDAO = new ThietBiDAO(context);
        listThietBi = new ArrayList<ThietBi>();
        listThietBi = (ArrayList<ThietBi>) thietBiDAO.getAll();
        thietBiSpinnerAdapter = new ThietBiSpinnerAdapter(context, listThietBi);
        spTB.setAdapter(thietBiSpinnerAdapter);
        spTB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                maTB = listThietBi.get(position).getMaTB();
//                soLuong = listThietBi.get(position).get
                Toast.makeText(context, "Chọn" + listThietBi.get(position).getTenTB(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialog.show();
    }
}