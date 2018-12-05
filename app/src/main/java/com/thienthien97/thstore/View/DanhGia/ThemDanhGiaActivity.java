package com.thienthien97.thstore.View.DanhGia;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.thienthien97.thstore.R;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia {

    TextInputLayout input_edtTieuDe, input_edtNoiDung;
    EditText edtTieuDe, edtNoiDung;
    RatingBar rbDanhGia;
    Button btnDanhgia;
    int masp = 0;
    int sosao = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);

        Anhxa();

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        rbDanhGia.setOnRatingBarChangeListener(this);

        btnDanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//                if (ActivityCompat.checkSelfPermission(ThemDanhGiaActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    return;
//                }
//
//                String madg = telephonyManager.getDeviceId();
                String tenthietbi = Build.MODEL;
                String tieude = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();

                if(tieude.trim().length() > 0){
                    input_edtTieuDe.setErrorEnabled(false);
                    input_edtTieuDe.setError("");
                }else{
                    input_edtTieuDe.setErrorEnabled(true);
                    input_edtTieuDe.setError("Bạn chưa nhập tiêu đề !");
                }

                if(noidung.trim().length() > 0){
                    input_edtNoiDung.setErrorEnabled(false);
                    input_edtNoiDung.setError("");
                }else{
                    input_edtNoiDung.setErrorEnabled(true);
                    input_edtNoiDung.setError("Bạn chưa nhập nội dung!");
                }

                if(!input_edtTieuDe.isErrorEnabled() && !input_edtNoiDung.isErrorEnabled()){
                    DanhGia danhGia = new DanhGia();
                    //danhGia.setMADG(madg);
                    danhGia.setMASP(masp);
                    danhGia.setTIEUDE(tieude);
                    danhGia.setNOIDUNG(noidung);
                    danhGia.setTENTHIETBI(tenthietbi);
                    danhGia.setSOSAO(sosao);

                    presenterLogicDanhGia.ThemDanhGia(danhGia);
                    finish();
                }
            }
        });
    }

    private void Anhxa() {
        input_edtNoiDung = findViewById(R.id.input_edtNoiDungDG);
        input_edtTieuDe = findViewById(R.id.input_edtTieuDeDG);
        edtNoiDung = findViewById(R.id.edtNoiDungDG);
        edtTieuDe = findViewById(R.id.edtTieuDeDG);
        btnDanhgia = findViewById(R.id.btnDanhGia);
        rbDanhGia = findViewById(R.id.rbDanhGia);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
        sosao = (int)rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công ^^", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Đánh giá thất bại :(", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void HienThiDSDanhGiaSP(List<DanhGia> danhGiaList) {

    }
}
