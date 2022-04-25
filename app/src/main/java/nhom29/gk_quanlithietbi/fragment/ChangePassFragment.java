package nhom29.gk_quanlithietbi.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nhom29.gk_quanlithietbi.R;
import nhom29.gk_quanlithietbi.dao.TaiKhoanDAO;
import nhom29.gk_quanlithietbi.model.TaiKhoan;

public class ChangePassFragment extends Fragment {
    TextInputEditText edPass0ld, edPass, edRePass;
    Button btnSave, btnCancel;
    TaiKhoanDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);

        dao = new TaiKhoanDAO(getActivity());
        edPass0ld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPass0ld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate() > 0) {
                    TaiKhoan taiKhoan = dao.getID(user);
                    taiKhoan.setMatKhau(edPass.getText().toString());
                    dao.updatePass(taiKhoan);
                    if (dao.updatePass(taiKhoan) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass0ld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public int validate() {
        int check = 1;
        if (edPass0ld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            // doc user, pass trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!passOld.equals(edPass0ld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}