package com.medical.autism;

import android.app.Dialog;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    Dialog dialog;
    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    public void showMessage(String message){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show();
    }

    public void loading(){
        dialog= new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading);
        dialog.show();
    }

    public void stopLoading(){
        dialog.dismiss();
    }
}
