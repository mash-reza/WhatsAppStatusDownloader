package com.example.whatsappstatusdownloader.util;

import com.example.whatsappstatusdownloader.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static List<Status> getStatus(){
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status(0,Status.TYPE_IMAGE,"/WhatsApp/Media/.Statuses/img01.jpg"));
        statuses.add(new Status(1,Status.TYPE_IMAGE,"/WhatsApp/Media/.Statuses/img02.jpg"));
        statuses.add(new Status(2,Status.TYPE_IMAGE,"/WhatsApp/Media/.Statuses/img03.jpg"));
        statuses.add(new Status(3,Status.TYPE_VIDEO,"/WhatsApp/Media/.Statuses/vid01.mp4"));
        statuses.add(new Status(4,Status.TYPE_IMAGE,"/WhatsApp/Media/.Statuses/img02.jpg"));
        statuses.add(new Status(5,Status.TYPE_IMAGE,"/WhatsApp/Media/.Statuses/img01.jpg"));
        statuses.add(new Status(6,Status.TYPE_VIDEO,"/WhatsApp/Media/.Statuses/vid02.mp4"));
        return statuses;
    }
}
