package com.axionlabs.meldora.service.impl;

import com.axionlabs.meldora.dto.music.TrackDto;
import com.axionlabs.meldora.dto.music.response.TrackResponseDto;
import com.axionlabs.meldora.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    @Qualifier("spotifyWebClient")
    private WebClient spotifyWebClient;
    @Override
    public TrackDto getTracks(String q) {
        String formattedQ = q.replaceAll(" ","+");

        String uri = String.format("/search?q=%s&type=track",formattedQ);
        TrackResponseDto response =  spotifyWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(TrackResponseDto.class)
                .block();
        return response!=null?response.getTracks(): new TrackDto();

    }

}
