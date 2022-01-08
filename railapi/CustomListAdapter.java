package com.sachi.railapi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<CollegeDataClass> {

    Activity context;
    ArrayList<CollegeDataClass> collegeDataClasses;

    public CustomListAdapter(@NonNull Activity context, ArrayList<CollegeDataClass> collegeDataClasses) {
        super(context, R.layout.customelist,collegeDataClasses);
        this.context = context;
        this.collegeDataClasses = collegeDataClasses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View collegeView =layoutInflater.inflate(R.layout.customelist,null,true);
        CollegeDataClass collegeDataClass = (CollegeDataClass) getItem(position);

        TextView country = collegeView.findViewById(R.id.txtCountry);
        TextView name = collegeView.findViewById(R.id.txtName);
        TextView state = collegeView.findViewById(R.id.txtState);

        country.setText(collegeDataClass.country);
        name.setText(collegeDataClass.name);
        state.setText(collegeDataClass.state);

        return collegeView;
    }
}
