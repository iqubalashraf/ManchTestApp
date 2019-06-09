package com.app.manchpostapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import com.app.manchpostapp.service.PostInsertHandler;
import com.app.manchpostapp.service.PostServiceImpl;

public class AddNewPostActivity extends AppCompatActivity {

    public static final String KEY_UID = "com.app.manchpostapp.KEY_UID";

    Button btn_post;
    TextInputLayout til_input_title, til_input_desc;
    TextInputEditText et_input_title, et_input_desc;
    String title, description;
    int uid = 0;
    private PostServiceImpl postService;

    public static Intent getIntent(Context context, int uid) {
        Intent intent = new Intent(context.getApplicationContext(), AddNewPostActivity.class);
        intent.putExtra(KEY_UID, uid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        postService = new PostServiceImpl(this);
        initializeData(getIntent().getExtras());
        initializeView();
        initializeListener();
    }

    public void initializeData(Bundle args) {
        if (args != null) {
            uid = args.getInt(KEY_UID, 0);
        }
    }

    private void initializeView() {
        btn_post = findViewById(R.id.btn_post);
        til_input_title = findViewById(R.id.til_input_title);
        til_input_desc = findViewById(R.id.til_input_desc);
        et_input_title = findViewById(R.id.et_input_title);
        et_input_desc = findViewById(R.id.et_input_desc);

    }

    private void initializeListener() {
        btn_post.setOnClickListener(v -> {
            if (validateData()) {
                new PostInsertHandler(uid++, title, description, postService, () -> {
                    Toast.makeText(getApplicationContext(), getString(R.string.added_successfully)
                            , Toast.LENGTH_SHORT).show();
                    finish();
                }).execute((Void) null);
            }
        });

        et_input_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    til_input_title.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_input_desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    til_input_desc.setError(null);
                }
            }
        });
    }

    private boolean validateData() {
        boolean status = true;

        if (TextUtils.isEmpty(et_input_title.getText().toString().trim())) {
            til_input_title.setError(getString(R.string.invalid_input));
            status = false;
        } else {
            title = et_input_title.getText().toString().trim();
            til_input_title.setError(null);
        }

        if (TextUtils.isEmpty(et_input_desc.getText().toString().trim())) {
            til_input_desc.setError(getString(R.string.invalid_input));
            status = false;
        } else {
            description = et_input_desc.getText().toString().trim();
            til_input_desc.setError(null);
        }
        return status;
    }
}
