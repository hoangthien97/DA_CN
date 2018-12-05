package com.thienthien97.thstore.View.DangNhap_DangKy.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thienthien97.thstore.Model.DangNhap_DangKy.ModelDangNhap;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    Button btnFacebook, btnGoogle, btnDangnhap;
    CallbackManager callbackManager;
    GoogleApiClient googleApiClient;
    public static int SIGN_IN_GG_PLUS = 111;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edtTenDangNhap, edtMatKhau;

    public DangNhapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        modelDangNhap = new ModelDangNhap();
        googleApiClient = modelDangNhap.LayGgAPIClient(getContext(),this);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnFacebook = view.findViewById(R.id.btnDangNhapFaceBook);
        btnGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangnhap = view.findViewById(R.id.dangnhap_btnDangnhap);
        edtTenDangNhap = view.findViewById(R.id.dangnhap_edtEmail);
        edtMatKhau = view.findViewById(R.id.dangnhap_edtMatkhau);

        btnFacebook.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnDangnhap.setOnClickListener(this);

        return view;
    }

    public void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnDangNhapFaceBook:
                LoginManager.getInstance().logInWithReadPermissions(DangNhapFragment.this,  Arrays.asList("public_profile","email","user_status"));
                break;

            case R.id.btnDangNhapGoogle:
                Intent intentGG = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intentGG, SIGN_IN_GG_PLUS);
                showProgressDialog();
                break;

            case R.id.dangnhap_btnDangnhap:
                String tendangnhap = edtTenDangNhap.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(), tendangnhap, matkhau);

                if(kiemtra){
                    Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(requestCode  == SIGN_IN_GG_PLUS){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                progressDialog.cancel();
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
