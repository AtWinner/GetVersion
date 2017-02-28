package com.demo.getversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editVersion1, editVersion2;
    private Button btnConfirm;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editVersion1 = (EditText) findViewById(R.id.editVersion1);
        editVersion2 = (EditText) findViewById(R.id.editVersion2);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                String version1 = editVersion1.getEditableText().toString().trim();
                String version2 = editVersion2.getEditableText().toString().trim();
                try {
                    int result = MyUtil.versionCompare(version1, version2);
                    switch (result) {
                        case 0:
                            showShortToast("版本号1和版本号2相同");
                            break;
                        case 1:
                            showShortToast("版本号1高于版本号2");
                            break;
                        case 2:
                            showShortToast("版本号2高于版本号1");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showShortToast(e.getMessage());
                }
                break;
        }
    }

    /**
     * 显示信息
     *
     * @param content
     */
    private void showShortToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
