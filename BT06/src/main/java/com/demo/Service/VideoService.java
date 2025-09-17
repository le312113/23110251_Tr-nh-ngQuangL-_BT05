package com.demo.Service;

import com.demo.Entity.Video;

import java.util.List;

public interface VideoService {
    void  insertVideo(Video video);
    void  deleteVideo(int video_id);
    void editVideo(Video video);
    List<Video> getAllVideo();
    Video findById(int video_id);
}
