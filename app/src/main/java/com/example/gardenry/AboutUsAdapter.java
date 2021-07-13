package com.example.gardenry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AboutUsAdapter extends BaseAdapter {

    Context context;
    ArrayList<GroupMember> groupMembers;

    public AboutUsAdapter(Context context, ArrayList<GroupMember> groupMembers) {
        this.context = context;
        this.groupMembers = groupMembers;
    }

    @Override
    public int getCount() {
        return groupMembers.size();
    }

    @Override
    public Object getItem(int position) {
        return groupMembers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupMember groupMember = groupMembers.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.group_member, parent, false);
        ImageView imgDp = view.findViewById(R.id.img_dp_group_member);
        TextView txtName = view.findViewById(R.id.txt_name_group_member);
        TextView txtDesignation = view.findViewById(R.id.txt_designation_group_member);
        TextView txtEmail = view.findViewById(R.id.txt_email_group_member);
        LinearLayout linearLayout = view.findViewById(R.id.ll_card_group_member);
        imgDp.setImageResource(groupMember.getDp());
        txtName.setText(groupMember.getName());
        txtEmail.setText(groupMember.getEmail());
        if(position==0)
            txtDesignation.setVisibility(View.VISIBLE);
        if(position%2==0){
            linearLayout.setBackground(view.getResources().getDrawable(R.drawable.rounded_corners));
        }
        else linearLayout.setBackground(view.getResources().getDrawable(R.drawable.rounded_corners_white));
        return view;
    }
}
