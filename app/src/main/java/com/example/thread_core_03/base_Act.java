package com.example.thread_core_03;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class base_Act <T extends ViewBinding> extends AppCompatActivity implements View.OnClickListener {
    T binding  ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding() ;
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    @Override
    public void onClick(View v) {
        // do nothing
    }

    protected abstract T initViewBinding();
}
