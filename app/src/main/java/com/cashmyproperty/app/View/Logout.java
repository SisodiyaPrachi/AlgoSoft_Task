package com.cashmyproperty.app.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.MainActivity;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Repository.DataRepository;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Logout extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.logout,container,false);

        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
        {  ((Navigation_Seller) getActivity()).setDrawer_Locked();
            ((Navigation_Seller) getActivity()).setbottom_Locked();
        }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_Locked();
            ((Navigation_Activity)getActivity()).setbottom_Locked();
        }

        show_alert();


        return view;

    }

    private void show_alert() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        //builder.setTitle("Permission Denied");
        builder.setMessage("Are you sure you want to logout?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
                {
                    Intent intent=new Intent(getActivity(),Navigation_Seller.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getActivity(),Navigation_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataRepository.setLogout(getActivity());

                PreferenceUtils.setBoolValue(getActivity(), PreferenceUtils.Login, false);
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
        {
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
            ((Navigation_Seller) getActivity()).setbottom_Locked();
        }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_Locked();
            ((Navigation_Activity)getActivity()).setbottom_Locked();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller")) {
            ((Navigation_Seller) getActivity()).setDrawer_UnLocked();
            ((Navigation_Seller) getActivity()).setbottom_UnLocked();
        }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_UnLocked();
            ((Navigation_Activity)getActivity()).setbottom_UnLocked();
        }
    }
}
