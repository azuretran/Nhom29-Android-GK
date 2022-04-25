package nhom29.gk_quanlithietbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import nhom29.gk_quanlithietbi.dao.ChiTietSuDungDAO;
import nhom29.gk_quanlithietbi.dao.TaiKhoanDAO;
import nhom29.gk_quanlithietbi.fragment.ChangePassFragment;
import nhom29.gk_quanlithietbi.fragment.ChiTietSuDungFragment;
import nhom29.gk_quanlithietbi.fragment.LoaiThietBiFragment;
import nhom29.gk_quanlithietbi.fragment.PhongHocFragment;
import nhom29.gk_quanlithietbi.fragment.ThietBiFragment;
import nhom29.gk_quanlithietbi.model.TaiKhoan;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    ChiTietSuDungDAO dao;
    TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

//        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        ChiTietSuDungFragment chiTietSuDungFragment = new ChiTietSuDungFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, chiTietSuDungFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        taiKhoanDAO = new TaiKhoanDAO(this);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(user);
        String username = taiKhoan.getHoTen();
        edUser.setText("Welcome " + username + "!");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()) {
                    case R.id.nav_ChiTietSuDung:
                        setTitle("Chi Tiết Sử Dụng");
                        ChiTietSuDungFragment chiTietSuDungFragment = new ChiTietSuDungFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, chiTietSuDungFragment)
                                .commit();
                        break;
                    case R.id.nav_LoaiThietBi:
                        setTitle("Quản lý Loại Thiết Bị");
                        LoaiThietBiFragment loaiThietBiFragment = new LoaiThietBiFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, loaiThietBiFragment)
                                .commit();
                        break;
                    case R.id.nav_ThietBi:
                        setTitle("Quản lý Thiết Bị");
                        ThietBiFragment thietBiFragment = new ThietBiFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, thietBiFragment)
                                .commit();
                        break;
                    case R.id.nav_PhongHoc:
                        setTitle("Quản lý Phòng Học");
                        PhongHocFragment phongHocFragment = new PhongHocFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, phongHocFragment)
                                .commit();
                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thêm người dùng");
                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }

                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
}