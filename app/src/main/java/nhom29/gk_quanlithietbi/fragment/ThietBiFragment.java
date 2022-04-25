package nhom29.gk_quanlithietbi.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.adapter.LoaiThietBiSpinnerAdapter;
import nhom29.gk_quanlithietbi.adapter.ThietBiAdapter;
import nhom29.gk_quanlithietbi.dao.LoaiThietBiDAO;
import nhom29.gk_quanlithietbi.dao.ThietBiDAO;
import nhom29.gk_quanlithietbi.model.LoaiThietBi;
import nhom29.gk_quanlithietbi.model.ThietBi;

public class ThietBiFragment extends Fragment {
    ListView lvThietBi;
    ThietBiDAO thietBiDAO;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaThietBi, edTenThietBi, edXuatXu;
    Spinner spinner;
    Button btnSave, btnCancel;

    ThietBiAdapter adapter;
    ThietBi item;
    List<ThietBi> list;

    LoaiThietBiSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiThietBi> listLoaiThietBi;
    LoaiThietBiDAO loaiThietBiDAO;
    LoaiThietBi loaiThietBi;
    int maLoaiThietBi, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thiet_bi, container, false);
        lvThietBi = v.findViewById(R.id.lvThietBi);
        thietBiDAO = new ThietBiDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvThietBi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (List<ThietBi>) thietBiDAO.getAll();
        adapter = new ThietBiAdapter(getActivity(), this, list);

        lvThietBi.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        //Su dung Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                thietBiDAO.delete(Id);

                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thiet_bi_dialog);

        edMaThietBi = dialog.findViewById(R.id.edMaThietBi);
        edTenThietBi = dialog.findViewById(R.id.edTenThietBi);
        edXuatXu = dialog.findViewById(R.id.edXuatXu);
        spinner = dialog.findViewById(R.id.spLoaiThietBi);
        btnCancel = dialog.findViewById(R.id.btnCancelTB);
        btnSave = dialog.findViewById(R.id.btnSaveTB);

        listLoaiThietBi = new ArrayList<LoaiThietBi>();
        loaiThietBiDAO = new LoaiThietBiDAO(context);
        listLoaiThietBi = (ArrayList<LoaiThietBi>) loaiThietBiDAO.getALL();

        spinnerAdapter = new LoaiThietBiSpinnerAdapter(context, listLoaiThietBi);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                maLoaiThietBi = listLoaiThietBi.get(position).getMaLoai();
                Toast.makeText(context, "Chọn" + listLoaiThietBi.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //kiem tra type insert 0 hay Update 1
        edMaThietBi.setEnabled(false);
        if (type != 0) {
            edMaThietBi.setText(String.valueOf(item.getMaTB()));
            edTenThietBi.setText(item.getTenTB());
            edXuatXu.setText(String.valueOf(item.getXuatXu()));
            for (int i = 0; i < listLoaiThietBi.size(); i++)
                if (item.getMaLoai() == (listLoaiThietBi.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posSach" + position);
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThietBi();
                item.setTenTB(edTenThietBi.getText().toString());
                item.setXuatXu(edXuatXu.getText().toString());
                item.setMaLoai(maLoaiThietBi);

                if (validate() > 0) {
                    if (type == 0) {
                        if (thietBiDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaTB(Integer.parseInt(edMaThietBi.getText().toString()));
                        if (thietBiDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Sửa Thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenThietBi.getText().length() == 0 || edXuatXu.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}