package com.demo.Repository;

import com.demo.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository  extends JpaRepository<Video,Integer> {
    List<Video> findByTitleContainingIgnoreCase(String title);
}
