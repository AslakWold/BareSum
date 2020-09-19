package com.example.s331378_s331389_mappe1baresum;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private DialogClickListener callback;

    public interface DialogClickListener{
        public void btnAvbryt();
        public void btnAvslutt();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            callback=(DialogClickListener)getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException("Klassen implementerer ikke interfacet");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.Avbryt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.btnAvbryt();
                    }
                }).setNegativeButton(R.string.Avslutt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.btnAvslutt();
                    }
                }).setView(R.layout.activity_dialog)
                .create();
    }
} //MyDialog - slutt
