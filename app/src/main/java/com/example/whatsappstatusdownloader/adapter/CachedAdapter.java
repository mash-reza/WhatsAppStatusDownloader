package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.model.Status;
import com.example.whatsappstatusdownloader.util.Constants;
import com.example.whatsappstatusdownloader.util.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public class CachedAdapter extends RecyclerView.Adapter<CachedAdapter.MyHolder>
{
    private static final String TAG = "CachedAdapter";


    private Context context;
    private List<Status> statuses;

    public CachedAdapter(Context context, List<Status> statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public CachedAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.cached_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CachedAdapter.MyHolder myHolder, int i) {
        switch (myHolder.getItemViewType()) {
            case Constants.STATUS_TYPE_IMAGE: {
                myHolder.foreground.setVisibility(View.GONE);
                myHolder.playIcon.setVisibility(View.GONE);
                Glide.with(context).load(new File(statuses.get(i).getAddress())).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent.putExtra("type", Constants.STATUS_TYPE_IMAGE);
                    intent.putExtra("path", statuses.get(i).getAddress());
                    context.startActivity(intent);
                });
                break;
            }
            case Constants.STATUS_TYPE_VIDEO: {
                //set thumbnail
//                Glide.with(context).load(ThumbnailUtils.createVideoThumbnail(statuses.get(i).getAddress(), MediaStore.Video.Thumbnails.MICRO_KIND)).into(myHolder.image);
                Glide.with(context).load(Uri.fromFile(new File(statuses.get(i).getAddress()))).into(myHolder.image);
                myHolder.foreground.setOnClickListener(v -> {
                    Intent intent = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent.putExtra("type", Constants.STATUS_TYPE_VIDEO);
                    intent.putExtra("path", statuses.get(i).getAddress());
                    context.startActivity(intent);
                });
                break;
            }
        }

        myHolder.imageButton.setOnClickListener(v -> {
            Repository repository = new Repository();
            int size = repository.getStatusFromPhone().size();
            Toast.makeText(context, "cliked", Toast.LENGTH_SHORT).show();
            //context.startActivity(new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class));
            //make pictures dir in gallery
            File folderRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Constants.MEDIA_FOLDER_GALLERY_NAME);
            folderRoot.mkdirs();

            //retrieve image from whatsapp
            InputStream in = null;
            try {
                in = new FileInputStream(statuses.get(i).getAddress());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            //write to gallery
            Date date = new Date();
            long name = date.getTime();
            OutputStream out = null;
            try {
//                    out = new FileOutputStream(new File(folderRoot, "image00" + recyclerView.getChildAdapterPosition(itemView) + ".jpg"));
                if (myHolder.getItemViewType() == Constants.STATUS_TYPE_IMAGE)
                    out = new FileOutputStream(new File(folderRoot, name + ".jpg"));
                else if (myHolder.getItemViewType() == Constants.STATUS_TYPE_VIDEO)
                    out = new FileOutputStream(new File(folderRoot, name + ".mp4"));

                int count;
                byte[] buffer = new byte[1024];
                while ((count = in.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                Log.e(TAG, "onBindViewHolder: writing successful", null);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "onBindViewHolder: ", e);
            } catch (IOException e) {
                Log.e(TAG, "onBindViewHolder: ", e);
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                        //scanning images
                        String fileName = "";
                        if (myHolder.getItemViewType() == Constants.STATUS_TYPE_IMAGE)
                            fileName = name + ".jpg";
                        else if (myHolder.getItemViewType() == Constants.STATUS_TYPE_VIDEO)
                            fileName = name + ".mp4";
                        MediaScannerConnection.scanFile(context,
                                new String[]{new File(folderRoot, fileName).getPath()}, null,
                                (path, uri) -> {
                                    Log.i(TAG, "onBindViewHolder: scanned " + path);
                                }
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }


    @Override
    public int getItemViewType(int position) {
        return statuses.get(position).getType();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView foreground;
        ImageView playIcon;
        ImageButton imageButton;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cached_imageView_item);
            foreground = itemView.findViewById(R.id.cached_item_foreground);
            playIcon = itemView.findViewById(R.id.play_icon_imageView);
            imageButton = itemView.findViewById(R.id.cached_download_imageButton);
        }
    }

}
