package com.demo.Service;

import com.demo.Entity.Video;
import com.demo.Repository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceImplement implements VideoService{
    @Autowired
    private VideoRepository videoRepository;
    public void  insertVideo(Video video){
        videoRepository.save(video);
    }
    @Transactional
    public void  deleteVideo(int video_id){
        videoRepository.deleteById(video_id);
        videoRepository.flush();
    }
    public void editVideo(Video video){
        videoRepository.save(video);
    }
    public List<Video> getAllVideo(){
        return videoRepository.findAll();
    }
    public Video findById(int videoId) {
        return videoRepository.findById(videoId).orElse(null);
    }
    public List<Video> searchByTitle(String keyword) {
        return videoRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
