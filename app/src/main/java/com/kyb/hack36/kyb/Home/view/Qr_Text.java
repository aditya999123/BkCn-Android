package com.kyb.hack36.kyb.Home.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyb.hack36.kyb.Home.model.QrData;
import com.kyb.hack36.kyb.Home.presenter.Qr_Presenter;
import com.kyb.hack36.kyb.Home.model.Qr_Provider;
import com.kyb.hack36.kyb.R;
import com.kyb.hack36.kyb.helper.Keys;
import com.kyb.hack36.kyb.helper.SharedPrefs;


public class Qr_Text extends AppCompatActivity implements QrViewInterface{

    private ProgressBar progressBar;
    private TextView qrTextView,model_text,manufacturer_text,owner_text,user_id,user_mob;
    private String qr_details,prod_id,model_no;
    private Button transfer_btn,owner_btn,mk_txn;
    private RelativeLayout text_layout,analysis_layout,transfer_layout;
    SharedPrefs sharedPrefs;

    String receiver;
    Qr_Presenter qr_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__text);
        qr_details=getIntent().getStringExtra("qr_key");
        initialize();
        qrTextView.setText(qr_details);
        analysis_layout.setVisibility(View.GONE);
        transfer_layout.setVisibility(View.GONE);
//        qr_details="fvrgtrtgbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv";
        owner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qr_presenter.getProductData(prod_id,sharedPrefs.getAccessToken());
            }
        });

        transfer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qr_presenter.transfer(prod_id,sharedPrefs.getAccessToken(),receiver);
            }
        });
    }
    void initialize()
    {
        sharedPrefs=new SharedPrefs(this);
        qr_presenter=new Qr_Presenter(this,new Qr_Provider());
        progressBar=(ProgressBar)findViewById(R.id.qr_progress_bar);
        qrTextView=(TextView)findViewById(R.id.qr_text);
        transfer_btn=(Button)findViewById(R.id.transfer_btn);
        owner_btn=(Button)findViewById(R.id.owner_btn);
        manufacturer_text=(TextView)findViewById(R.id.manufacturer);
        owner_text=(TextView)findViewById(R.id.owner);
        model_text=(TextView)findViewById(R.id.model);
        prod_id=qr_details.substring(3,3+64);
        text_layout=(RelativeLayout)findViewById(R.id.text_layout);
        analysis_layout=(RelativeLayout)findViewById(R.id.analysis_layout);
        transfer_layout=(RelativeLayout)findViewById(R.id.transfer_layout);
        user_id=(TextView)findViewById(R.id.user_name);
        user_mob=(TextView)findViewById(R.id.user_mob);
        mk_txn=(Button)findViewById(R.id.mk_txn);
        model_no="Model123";
    }

    @Override
    public void showProgressBAr(Boolean bool) {
     if(bool)
     {
         progressBar.setVisibility(View.VISIBLE);
     }
     else
         progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void user_authenticated(QrData qrData) {
        manufacturer_text.setText("Manufacturer:\t\t"+qrData.getManufacturer());
        owner_text.setText("Owner:\t\t"+qrData.getOwner());
        model_text.setText(model_no);
        text_layout.setVisibility(View.GONE);
        transfer_layout.setVisibility(View.VISIBLE);
        analysis_layout.setVisibility(View.VISIBLE);
        mk_txn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name= (String) user_id.getText();
                String mob=(String) user_mob.getText();
                if(user_name.isEmpty())
                qr_presenter.make_txn(prod_id,sharedPrefs.getAccessToken(),mob);
                else
                    qr_presenter.make_txn(prod_id,sharedPrefs.getAccessToken(),user_name);
            }
        });

    }

    @Override
    public void viewer(QrData qrData) {
        manufacturer_text.setText("Manufacturer:\t\t"+qrData.getManufacturer());
        owner_text.setText("Owner:\t\t"+qrData.getOwner());
        model_text.setText(model_no);
        text_layout.setVisibility(View.GONE);
        transfer_layout.setVisibility(View.GONE);
    }

    @Override
    public void display(QrData qrData) {
        manufacturer_text.setText("Manufacturer:\t\t"+qrData.getManufacturer());
        owner_text.setText("Owner:\t\t"+qrData.getOwner());
        model_text.setText(model_no);
        text_layout.setVisibility(View.GONE);
        transfer_layout.setVisibility(View.GONE);
        showError("Invalid Request");
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
