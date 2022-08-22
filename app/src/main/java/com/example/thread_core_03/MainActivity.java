package com.example.thread_core_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.thread_core_03.databinding.ActivityMainBinding;

public class MainActivity extends base_Act<ActivityMainBinding> {


    private AsyncTask<Integer,Integer,Boolean> task ;

    @Override
    protected void initViews() {
        binding.btStart.setOnClickListener(v -> startCounting());
        binding.btStop.setOnClickListener(v -> stopCounting());
    }

    private void stopCounting() {
                if(task == null ) return ;
                task.cancel(true)  ;
    }
@SuppressWarnings("StaticFieldLeak")
    private void startCounting() {
        if (task != null ) return ;
        task = new AsyncTask<Integer, Integer, Boolean>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                binding.tvCounting.setText("00");
            }

            @Override
            protected Boolean doInBackground(Integer... integers) {
                int Start = integers[0] ;
                 int End = integers[1] ;
                 for(int i = Start; i<= End ;i++)
                 {
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                         return false ;
                     }
                     publishProgress(i);
                 }
                return true ;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                binding.tvCounting.setText(String.format("%s",values));
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                binding.tvCounting.setText("Counting done!");
            }

            @Override
            protected void onCancelled() {
                binding.tvCounting.setText("Counting has interrupted");
                task = null ;
            }
        };
            task.execute(1,10) ;
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}