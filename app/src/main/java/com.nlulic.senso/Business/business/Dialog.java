package business;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog {

    public static void Show(String message, Context ctxt) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctxt);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
