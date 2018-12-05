package com.thienthien97.thstore.View.ThanhToan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietHoaDon;
import com.thienthien97.thstore.Model.ObjectClass.HoaDon;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements ViewThanhToan{

    Toolbar toolbarThanhtoan;
    EditText edtTennguoinhan, edtDiachi, edtSoDT;
    ImageButton img_btnCOD, img_btnChuyenkhoan;
    Button btnThanhtoan;
    TextView txtChuyenkhoan, txtThanhtoankhinhanhang;
    CheckBox ckbCamket;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
    int chonHinhThuc = 0;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("Ad7GpwUJlfPZC56XD5FNM5BOF_n_Gb6duL6QSzEB34nqCpQFZibp8vF-2Wv9pJIEm92p7pZxd-ACAoRA")
            .merchantName("TH Store")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        Anhxa();

        setSupportActionBar(toolbarThanhtoan);
        getSupportActionBar().setTitle("Thanh toán");

        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDSSPTrongGioHang();


        //start PaypalmentService
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tennguoinhan = edtTennguoinhan.getText().toString();
                String sodt = edtSoDT.getText().toString();
                String diachi = edtDiachi.getText().toString();

                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0){
                    if(ckbCamket.isChecked()){
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setSoDT(sodt);
                        hoaDon.setDiaChi(diachi);
                        hoaDon.setChuyenKhoan(chonHinhThuc);

                        if(chonHinhThuc == 0) {
                            hoaDon.setChiTietHoaDonList(chiTietHoaDons);
                            presenterLogicThanhToan.ThemHoaDon(hoaDon);
                        }else if(chonHinhThuc == 1){
                            processPayment();
                        }

                    }else{
                        Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhấn chọn ô cam kết thông tin !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhập đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_btnCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonHinhThucThanhToan(txtThanhtoankhinhanhang, txtChuyenkhoan);
                chonHinhThuc = 0;
            }
        });

        img_btnChuyenkhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonHinhThucThanhToan(txtChuyenkhoan, txtThanhtoankhinhanhang);
                chonHinhThuc = 1;
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

   private void processPayment(){
       PayPalPayment payment = new PayPalPayment(new BigDecimal("0"), "USD", "TH Store",
               PayPalPayment.PAYMENT_INTENT_SALE);

       Intent intent = new Intent(this, PaymentActivity.class);
       // send the same configuration for restart resiliency
       intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
       intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
       startActivityForResult(intent, 0);
   }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }

    private int getIdColor(int idcolor){
        int color = 0;

        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    private void ChonHinhThucThanhToan(TextView txtHinhThucChon , TextView txtHuyChon){
        txtHinhThucChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorGray));
    }

    private void Anhxa() {
        toolbarThanhtoan = findViewById(R.id.toolbarThanhToan);
        edtTennguoinhan = findViewById(R.id.edtTenNguoiNhan);
        edtDiachi = findViewById(R.id.edtDiaChi);
        edtSoDT = findViewById(R.id.edtSoDT);
        img_btnChuyenkhoan = findViewById(R.id.img_btnChuyenKhoan);
        img_btnCOD = findViewById(R.id.img_btnCOD);
        btnThanhtoan = findViewById(R.id.btn_ThanhToan);
        ckbCamket = findViewById(R.id.ckbCamKet);
        txtChuyenkhoan = findViewById(R.id.txtChuyenKhoan);
        txtThanhtoankhinhanhang = findViewById(R.id.txtThanhToanKhiNhanHang);
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Đặt hàng thành công ^^", Toast.LENGTH_SHORT).show();
        edtTennguoinhan.setText("");
        edtDiachi.setText("");
        edtSoDT.setText("");
        Intent intentTrangChu = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(intentTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại !!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDSSPTrongGioHang(List<SanPham> sanPhamList) {

        for(int i=0; i<sanPhamList.size(); i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}
