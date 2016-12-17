package com.example.a.testproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by A on 6/20/2016.
 */
public class InformationAdapter2 extends RecyclerView.Adapter<InformationAdapter2.InformationViewHolder> {

    ArrayList<Information> informations=new ArrayList<Information>();

    Context ctx;
    public InformationAdapter2(ArrayList<Information> informations, Context ctx){
        this.ctx=ctx;
        this.informations=informations;
    }


    @Override
    public InformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        InformationViewHolder informationViewHolder=new InformationViewHolder(view,ctx,informations);
        return informationViewHolder;

    }

    @Override
    public void onBindViewHolder(InformationViewHolder holder, int position) {

        Information info=informations.get(position);
        holder.city1.setText(info.getInfo_city());
        holder.street1.setText(info.getInfo_street());
        holder.area1.setText(info.getInfo_area());

    }

    @Override
    public int getItemCount() {
        return informations.size();
    }

    public static class InformationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView city1;
        public TextView street1;
        public TextView area1;
        ArrayList<Information> informations=new ArrayList<Information>();
        Context ctx;

        public InformationViewHolder(View itemView, Context ctx, ArrayList<Information> informations) {
            super(itemView);

            this.ctx=ctx;
            this.informations=informations;
            itemView.setOnClickListener(this);
            city1=(TextView)itemView.findViewById(R.id.tvCity1);
            street1=(TextView)itemView.findViewById(R.id.tvStreet1);
            area1=(TextView)itemView.findViewById(R.id.tvArea1);
        }

        @Override
        public void onClick(View v) {

        int position=getAdapterPosition();
        Information information=this.informations.get(position);

        Intent intent=new Intent(this.ctx,YourInfoDetails.class);

        intent.putExtra("info_id", information.getInfo_id());
        intent.putExtra("City",information.getInfo_city());
        intent.putExtra("Street",information.getInfo_street());
        intent.putExtra("Area",information.getInfo_area());
        intent.putExtra("House_number",information.getInfo_house_no());
        intent.putExtra("Price",information.getInfo_price());
        intent.putExtra("Contact",information.getInfo_contact());
        intent.putExtra("Type",information.getInfo_type());
        intent.putExtra("path",information.getInfo_path());
        this.ctx.startActivity(intent);

        }
    }
}
