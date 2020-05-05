package com.lidia.ddbuilder.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import com.lidia.ddbuilder.ListaPersonajesActivity;
import com.lidia.ddbuilder.R;

public class HomeDialog {

    private Dialog dialog;
    private Button btnYes, btnNo;

    public void showDialog(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_home);

        btnYes = dialog.findViewById(R.id.btnYesHomeDialog);
        btnNo = dialog.findViewById(R.id.btnNoHomeDialog);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ListaPersonajesActivity.class));
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
