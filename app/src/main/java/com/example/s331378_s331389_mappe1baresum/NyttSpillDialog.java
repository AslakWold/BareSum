package com.example.s331378_s331389_mappe1baresum;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class NyttSpillDialog extends DialogFragment {

    private DialogListener callback;
    private String title;

    public interface DialogListener {
        public void btnStartNytt();
        public void btnTilbake();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            callback=(DialogListener)getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException("Klassen implementerer ikke interfacet");
        }
    }
    public NyttSpillDialog(String title){
        this.title=title;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity()).setTitle(title)
                .setPositiveButton(R.string.startNytt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.btnStartNytt();
                    }
                }).setNegativeButton(R.string.tilbake, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.btnTilbake();
                    }
                }).create();
    }
}

