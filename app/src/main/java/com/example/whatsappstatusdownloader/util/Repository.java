package com.example.whatsappstatusdownloader.util;

import android.os.Environment;

import com.example.whatsappstatusdownloader.model.Status;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {

    public static List<Status> getStatus() {
        List<Status> statuses = new ArrayList<>();
        String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.WHATSAPP_STATUSES_FOLDER_NAME;
        File folderFile = new File(folderPath);
        File[] fileList = folderFile.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getAbsolutePath().contains(".jpg"))
                statuses.add(new Status(i, Constants.STATUS_TYPE_IMAGE, fileList[i].getAbsolutePath()));
            else if (fileList[i].getAbsolutePath().contains(".mp4"))
                statuses.add(new Status(i, Constants.STATUS_TYPE_VIDEO, fileList[i].getAbsolutePath()));
        }

        return statuses;
    }


    public static List<Status> getStatusFromPhone() {
        List<Status> statuses = new ArrayList<>();
        String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constants.MEDIA_FOLDER_GALLERY_NAME;
        File folderFile = new File(folderPath);
        folderFile.mkdirs();
        File[] fileList = folderFile.listFiles();

        //Arrays.sort(fileList);
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getAbsolutePath().contains(".jpg"))
                statuses.add(new Status(i, Constants.STATUS_TYPE_IMAGE, fileList[i].getAbsolutePath()));
            else if (fileList[i].getAbsolutePath().contains(".mp4"))
                statuses.add(new Status(i, Constants.STATUS_TYPE_VIDEO, fileList[i].getAbsolutePath()));
        }

        return statuses;
    }

    //        statuses.add(new Status(0, Constants.STATUS_TYPE_IMAGE, "/WhatsApp/Media/.Statuses/img01.jpg"));
//        statuses.add(new Status(1, Constants.STATUS_TYPE_IMAGE, "/WhatsApp/Media/.Statuses/img02.jpg"));
//        statuses.add(new Status(2, Constants.STATUS_TYPE_IMAGE, "/WhatsApp/Media/.Statuses/img03.jpg"));
//        statuses.add(new Status(3, Constants.STATUS_TYPE_VIDEO, "/WhatsApp/Media/.Statuses/vid01.mp4"));
//        statuses.add(new Status(4, Constants.STATUS_TYPE_IMAGE, "/WhatsApp/Media/.Statuses/img02.jpg"));
//        statuses.add(new Status(5, Constants.STATUS_TYPE_IMAGE, "/WhatsApp/Media/.Statuses/img01.jpg"));
//        statuses.add(new Status(6, Constants.STATUS_TYPE_VIDEO, "/WhatsApp/Media/.Statuses/vid02.mp4"));
}