package com.nacoda.cermaticom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nacoda.cermaticom.NetworkUtils.GsonGithub;
import com.nacoda.cermaticom.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by You on 9/1/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    public List<GsonGithub.Bio> bioData;
    public Context mContext;

    public MainAdapter(List<GsonGithub.Bio> bioData, Context mContext) {
        this.bioData = bioData;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtUserName.setText(bioData.get(position).login);
        Glide.with(mContext)
                .load(bioData.get(position).avatarUrl)
                .crossFade()
                .into(holder.imgUser);

    }


    @Override
    public int getItemCount() {
        return bioData.size();
    }

    static

    /**
     * Declaring widgets using Butterknife
     **/



    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.imgUser)
        ImageView imgUser;
        @InjectView(R.id.txtUserName)
        TextView txtUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);

        }
    }
}

