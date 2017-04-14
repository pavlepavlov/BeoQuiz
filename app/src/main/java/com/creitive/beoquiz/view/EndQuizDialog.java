package com.creitive.beoquiz.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.creitive.beoquiz.R;

/**
 * Displays end of quiz text
 */

public class EndQuizDialog extends DialogFragment {

    public static int sCurrentScore = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.end_game)
                .setMessage(getString(R.string.current_score)+sCurrentScore)
                .setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity) getActivity()).handleRestart();
                    }
                })
                .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity) getActivity()).handleQuit();
                    }
                })
                .setCancelable(false);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

}
