package com.axionlabs.meldora.controller;

import com.axionlabs.meldora.dto.music.TrackDto;
import com.axionlabs.meldora.service.impl.MusicServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MusicController {
    @Autowired
    private MusicServiceImpl IMusicService;
    @QueryMapping
    public TrackDto fetchTracks(@Argument String q){
        return IMusicService.getTracks(q);
    }
}
