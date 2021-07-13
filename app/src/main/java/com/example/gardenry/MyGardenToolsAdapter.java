package com.example.gardenry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyGardenToolsAdapter extends BaseAdapter {
    Context context;
    ArrayList<GTool> gTools;

    public MyGardenToolsAdapter(Context context, ArrayList<GTool> gTools) {
        this.context = context;
        this.gTools = gTools;
    }

    @Override
    public int getCount() {
        return gTools.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.g_tool_item, parent, false);

        ImageView imgTool = view.findViewById(R.id.img_g_tool);
        TextView txtName = view.findViewById(R.id.txt_name_g_tool);
        TextView txtDesc = view.findViewById(R.id.txt_desc_g_tool);

        GTool gTool = gTools.get(position);

        imgTool.setImageResource(gTool.getImage());
        txtName.setText(gTool.getName());
        txtDesc.setText(gTool.getDesc());

        return view;
    }
}
