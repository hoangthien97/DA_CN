package com.thienthien97.thstore.View.ThongTinDonHang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thienthien97.thstore.Model.ObjectClass.ThongTinHoaDon;
import com.thienthien97.thstore.Presenter.ThongTinDonHang.PresenterThongTinDonHang;
import com.thienthien97.thstore.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ThongTinDonHangActivity extends AppCompatActivity implements ViewThongTinDonHang {

    TextView txtTensp, txtGiasp, txtSoluong, txtTennguoinhan, txtSoDT, txtDiachi, txtNgaymua, txtNgaygiao, txtTongtien;
    EditText edtMaHD;
    Button btnOK;
    PresenterThongTinDonHang presenterThongTinHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_hoa_don);

        Anhxa();

        final ThongTinHoaDon[] thongTinHoaDon = {new ThongTinHoaDon()};
        presenterThongTinHoaDon = new PresenterThongTinDonHang(this);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = edtMaHD.getText().toString();
                int mahd = Integer.parseInt(ma);
                thongTinHoaDon[0] = presenterThongTinHoaDon.LayThongTinHoaDonTheoMaHD(mahd);

                txtTensp.setText(thongTinHoaDon[0].getTENSP());

                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(thongTinHoaDon[0].getGIA());
                txtGiasp.setText(gia + " VND");

                txtSoluong.setText(thongTinHoaDon[0].getSOLUONG() + " ");
                txtTennguoinhan.setText(thongTinHoaDon[0].getTEBNGUOINHAN());
                txtSoDT.setText(thongTinHoaDon[0].getSODT());
                txtDiachi.setText(thongTinHoaDon[0].getDIACHI());
                txtNgaymua.setText(thongTinHoaDon[0].getNGAYMUA());
                txtNgaygiao.setText(thongTinHoaDon[0].getNGAYGIAO());

                String tongtien = numberFormat.format(thongTinHoaDon[0].getSOLUONG() * thongTinHoaDon[0].getGIA());
                txtTongtien.setText(tongtien + " VND");
            }
        });

    }

    @Override
    public void TimThayThongTinHoaDon() {

    }

    @Override
    public void KhongTimThayThongTinHoaDon() {
        Toast.makeText(this, "Không tìm thấy đơn hàng này !", Toast.LENGTH_SHORT).show();
    }

    private void Anhxa() {
        txtTensp = findViewById(R.id.hd_txtTenSP);
        txtGiasp = findViewById(R.id.hd_txtGiaSP);
        txtSoluong = findViewById(R.id.hd_txtSoluongSP);
        txtTennguoinhan = findViewById(R.id.hd_txtTennguoinhan);
        txtSoDT = findViewById(R.id.hd_txtSoDT);
        txtDiachi = findViewById(R.id.hd_txtDiachi);
        txtNgaymua = findViewById(R.id.hd_txtNgaymua);
        txtNgaygiao = findViewById(R.id.hd_txtNgaygiao);
        txtTongtien = findViewById(R.id.hd_Tongtien);
        edtMaHD = findViewById(R.id.edt_mahd);
        btnOK = findViewById(R.id.btn_OK);
    }

}
