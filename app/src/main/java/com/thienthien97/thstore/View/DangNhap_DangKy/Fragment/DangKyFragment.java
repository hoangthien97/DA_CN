package com.thienthien97.thstore.View.DangNhap_DangKy.Fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.thienthien97.thstore.Model.ObjectClass.NhanVien;
import com.thienthien97.thstore.Presenter.DangKy.PresenterLogicDangKy;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.DangNhap_DangKy.ViewDangKy;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangKyFragment extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener{

    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edtHoTen, edtMatKhau, edtNhapLaiMK, edtEmail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edtHoTen, input_edtMatKhau, input_edtNhapLaiMK, input_Email;
    Boolean ktthongtin = false;

    public DangKyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        btnDangKy = view.findViewById(R.id.dangky_btnDangKy);
        edtHoTen = view.findViewById(R.id.dangky_edtHoTenDK);
        edtMatKhau = view.findViewById(R.id.dangky_edtMatKhauDK);
        edtNhapLaiMK = view.findViewById(R.id.dangky_edtNhapLaiMatKhauDK);
        edtEmail = view.findViewById(R.id.dangky_edtEmailDK);
        sEmailDocQuyen = view.findViewById(R.id.dangky_sEmailDocQuyen);
        input_edtHoTen = (TextInputLayout) view.findViewById(R.id.dangky_inputEdtHoTen);
        input_edtMatKhau = (TextInputLayout) view.findViewById(R.id.dangky_inputEdtMatKhau);
        input_edtNhapLaiMK = (TextInputLayout) view.findViewById(R.id.dangky_inputEdtNhapLaiMatKhau);
        input_Email = (TextInputLayout) view.findViewById(R.id.dangky_inputEdtEmail);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edtHoTen.setOnFocusChangeListener(this);
        edtNhapLaiMK.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.dangky_btnDangKy:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen = "";
    private void btnDangKy(){
        String hoten = edtHoTen.getText().toString();
        String email = edtEmail.getText().toString();
        String matkhau = edtMatKhau.getText().toString();
        String nhaplaimk = edtNhapLaiMK.getText().toString();


        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if(ktthongtin){

            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKy.ThucHienDangKy(nhanVien);
        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.dangky_edtHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.trim().equals(null)){
                        input_edtHoTen.setErrorEnabled(true);
                        input_edtHoTen.setError("Bạn chưa nhập  mục này !");
                        ktthongtin = false;
                    }else{
                        input_edtHoTen.setErrorEnabled(false);
                        input_edtHoTen.setError("");
                        ktthongtin = true;
                    }
                }
                break;

            case R.id.dangky_edtEmailDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.trim().equals(null)){
                        input_Email.setErrorEnabled(true);
                        input_Email.setError("Bạn chưa nhập  mục này !");
                        ktthongtin = false;
                    }else{
                        Boolean ktEmail =  Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!ktEmail){
                            input_Email.setErrorEnabled(true);
                            input_Email.setError("Đây không phải định dạng Email !");
                            ktthongtin = false;
                        }else {
                            input_Email.setErrorEnabled(false);
                            input_Email.setError("");
                            ktthongtin = true;
                        }
                    }
                }
                break;

            case R.id.dangky_edtMatKhauDK:
                break;

            case R.id.dangky_edtNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edtMatKhau.getText().toString();

                    if(!chuoi.equals(matkhau)){
                        input_edtNhapLaiMK.setErrorEnabled(true);
                        input_edtNhapLaiMK.setError("Mật khẩu nhập lại không trùng khớp !");
                        ktthongtin = false;
                    }else{
                        input_edtNhapLaiMK.setErrorEnabled(false);
                        input_edtNhapLaiMK.setError("");
                        ktthongtin = true;
                    }
                }
                break;
        }
    }
}
