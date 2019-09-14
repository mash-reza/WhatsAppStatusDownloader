package com.example.whatsappstatusdownloader.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.model.Status;
import com.example.whatsappstatusdownloader.util.Constants;
import com.example.whatsappstatusdownloader.view.activity.Splash;
import com.example.whatsappstatusdownloader.view.activity.StatusTab;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyHolder> {
    private static final String TAG = "GalleryAdapter";

    private Context context;
    private List<Status> statusList;


    public GalleryAdapter(Context context, List<Status> statusList) {
        this.context = context;
        this.statusList = statusList;
    }

    @NonNull
    @Override
    public GalleryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.gallery_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        switch (getItemViewType(i)) {
            case Constants.STATUS_TYPE_IMAGE:
                myHolder.playIcon.setVisibility(View.GONE);
                Glide.with(context).load(statusList.get(i).getAddress()).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent.putExtra("path", statusList.get(i).getAddress());
                    intent.putExtra("type", Constants.STATUS_TYPE_IMAGE);
                    intent.putExtra("starter", Constants.GALLERY_TARTER_INTENT);
                    context.startActivity(intent);
                });
                myHolder.shareButton.setOnClickListener(v -> {
                    Log.i(TAG, "onCreate: share button clicked");
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,
                            context.getApplicationContext().getPackageName() + ".provider"
                            , new File(statusList.get(i).getAddress())));
                    shareIntent.setType("image/jpg");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.share_intent_title)));
                });
                break;
            case Constants.STATUS_TYPE_VIDEO:
                Glide.with(context).load(Uri.fromFile(new File(statusList.get(i).getAddress()))).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
                    Intent intent2 = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent2.putExtra("path", statusList.get(i).getAddress());
                    intent2.putExtra("type", Constants.STATUS_TYPE_VIDEO);
                    intent2.putExtra("starter", Constants.GALLERY_TARTER_INTENT);
                    context.startActivity(intent2);
                });
                myHolder.shareButton.setOnClickListener(v -> {
                    Log.i(TAG, "onCreate: share button clicked");
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,
                            context.getApplicationContext().getPackageName() + ".provider"
                            , new File(statusList.get(i).getAddress())));
                    shareIntent.setType("video/mp4");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.share_intent_title)));
                });
                break;
        }

        myHolder.deleteButton.setOnClickListener(v -> {
            File file = new File(statusList.get(i).getAddress());
            try {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.delete_dialog_messege)
                        .setPositiveButton(R.string.delete_accepted, (dialog, id) -> {
                            boolean isDeleted = file.delete();
                            Log.i(TAG, "onCreate: deleting file" + isDeleted);
                            statusList.remove(i);
//                            notifyItemRemoved(i);
                            notifyItemRangeRemoved(i,1);
                            new Handler().postDelayed(this::notifyDataSetChanged,500);
                        })
                        .setNegativeButton(R.string.delete_rejected, (dialog, id) -> {
                        }).create().show();

            } catch (Exception e) {
                Log.e(TAG, "onCreate: ", e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return statusList.get(position).getType();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView playIcon;
        ImageButton deleteButton;
        ImageButton shareButton;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gallery_imageView_item);
            playIcon = itemView.findViewById(R.id.gallery_play_icon_imageView);
            deleteButton = itemView.findViewById(R.id.delete_button);
            shareButton = itemView.findViewById(R.id.share_button);
        }
    }
}
