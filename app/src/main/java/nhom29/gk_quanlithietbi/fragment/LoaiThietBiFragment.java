package nhom29.gk_quanlithietbi.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.adapter.LoaiThietBiAdapter;
import nhom29.gk_quanlithietbi.adapter.PhongHocAdapter;
import nhom29.gk_quanlithietbi.dao.LoaiThietBiDAO;
import nhom29.gk_quanlithietbi.model.LoaiThietBi;
import nhom29.gk_quanlithietbi.model.PhongHoc;

public class LoaiThietBiFragment extends Fragment {
    ListView lvLoaiThietBi;
    ArrayList<LoaiThietBi> list;
    static LoaiThietBiDAO dao;
    LoaiThietBiAdapter adapter;
    LoaiThietBi item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_thiet_bi, container, false);
        lvLoaiThietBi = v.findViewById(R.id.lvLoaiThietBi);
        fab = v.findViewById(R.id.fab);

        dao = new LoaiThietBiDAO(getActivity());

        capNhatLV();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvLoaiThietBi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);

                return false;
            }
        });

        return v;
    }

    void capNhatLV() {
        list = (ArrayList<LoaiThietBi>) dao.getALL();
        adapter = new LoaiThietBiAdapter(getActivity(), this, list);
        lvLoaiThietBi.setAdapter(adapter);
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
                dao.delete(Id);

                capNhatLV();
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
        dialog.setContentView(R.layout.loai_thiet_bi_dialog);
        edMaLoai = dialog.findViewById(R.id.edMaLoai);
        edTenLoai = dialog.findViewById(R.id.edTenLoai);
        btnCancel = dialog.findViewById(R.id.btnCancelLTB);
        btnSave = dialog.findViewById(R.id.btnSaveLTB);

        edMaLoai.setEnabled(false);
        if (type != 0) {
            edMaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());
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
                item = new LoaiThietBi();
                item.setTenLoai(edTenLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        //type = 0 (insert)
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type =1 (update)
                        item.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT);
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenLoai.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}