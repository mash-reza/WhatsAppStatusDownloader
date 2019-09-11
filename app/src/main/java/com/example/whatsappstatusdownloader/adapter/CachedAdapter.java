package com.example.whatsappstatusdownloader.adapter;

import android.content.ClipData;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.model.Status;
import com.example.whatsappstatusdownloader.util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CachedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CachedAdapter";


    private Context context;
    private List<Status> statuses;
    private RecyclerView recyclerView;

    public CachedAdapter(Context context, List<Status> statuses, RecyclerView recyclerView) {
        this.context = context;
        this.statuses = statuses;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (i) {
            case Constants.STATUS_TYPE_IMAGE:
                return new ImageHolder(LayoutInflater.from(context).inflate(R.layout.cached_list_item_image_view, viewGroup, false));
            case Constants.STATUS_TYPE_VIDEO:
                return new VideoHolder(LayoutInflater.from(context).inflate(R.layout.cached_list_item_video_view, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myHolder, int i) {
        switch (myHolder.getItemViewType()) {
            case Constants.STATUS_TYPE_IMAGE: {
                ImageHolder holder = (ImageHolder) myHolder;
                Glide.with(context).load(new File(statuses.get(i).getAddress())).centerCrop().into(holder.imageView);
                break;
            }
            case Constants.STATUS_TYPE_VIDEO: {
                VideoHolder holder = (VideoHolder) myHolder;
                Uri uri = Uri.parse(statuses.get(i).getAddress());
                holder.videoView.setVideoURI(uri);
                holder.videoView.seekTo(1);
                break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return statuses.get(position).getType();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton imageButton;

        ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cached_imageView_item);
            imageButton = itemView.findViewById(R.id.cached_download_imageButton);
            imageButton.setOnClickListener(v -> {
                Log.i(TAG, "ImageHolder: " + recyclerView.getChildAdapterPosition(itemView));
                Toast.makeText(context, "cliked", Toast.LENGTH_SHORT).show();
                //context.startActivity(new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class));

                //make pictures dir in gallery
                File folderRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Constants.MEDIA_FOLDER_GALLERY_NAME);
                folderRoot.mkdirs();

                //retrieve image from whatsapp
                InputStream in = null;
                try {
//                    in = new FileInputStream(Environment.getExternalStorageDirectory().getAbsoluteFile() + statuses.get(i).getAddress());
                    in = new FileInputStream(statuses.get(recyclerView.getChildAdapterPosition(itemView)).getAddress());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                //write to gallery
                OutputStream out = null;
                try {
                    out = new FileOutputStream(new File(folderRoot, "image00" + recyclerView.getChildAdapterPosition(itemView) + ".jpg"));
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
                            MediaScannerConnection.scanFile(context,
                                    new String[]{new File(folderRoot, "image00" + recyclerView.getChildAdapterPosition(itemView) + ".jpg").getPath()}, null,
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
    }

    class VideoHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageButton imageButton;

        VideoHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.cached_videoView_item);
            imageButton = itemView.findViewById(R.id.cached_download_imageButton);
            videoView.setOnClickListener(v -> {
                MediaController mc = new MediaController(context);
                mc.setAnchorView(videoView);
                mc.setMediaPlayer(videoView);
                videoView.setMediaController(mc);
                mc.show();
            });
            imageButton.setOnClickListener(v -> {
                Toast.makeText(context, "cliked", Toast.LENGTH_SHORT).show();
                //context.startActivity(new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class));

                //make pictures dir in gallery
                File folderRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Constants.MEDIA_FOLDER_GALLERY_NAME);
                folderRoot.mkdirs();

                //retrieve image from whatsapp
                InputStream in = null;
                try {
                    in = new FileInputStream(statuses.get(recyclerView.getChildAdapterPosition(itemView)).getAddress());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                //write to gallery
                OutputStream out = null;
                try {
                    out = new FileOutputStream(new File(folderRoot, "video" + recyclerView.getChildAdapterPosition(itemView) + ".mp4"));
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
                            MediaScannerConnection.scanFile(context,
                                    new String[]{new File(folderRoot, "video" + recyclerView.getChildAdapterPosition(itemView) + ".mp4").getPath()}, null,
                                    (path, uri1) -> {
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
    }

}
