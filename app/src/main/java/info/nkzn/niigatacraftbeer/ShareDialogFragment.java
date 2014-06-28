package info.nkzn.niigatacraftbeer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import info.nkzn.niigatacraftbeer.core.Beer;
import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class ShareDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @FragmentArg
    Brewery brewery;

    @FragmentArg
    Beer beer;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("シェア");

        builder.setMessage(String.format("%s の %s を飲みました！ #新潟クラフトビールの陣", brewery.getName(), beer.getName()));

        builder.setNegativeButton("閉じる", this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
        }
    }
}
