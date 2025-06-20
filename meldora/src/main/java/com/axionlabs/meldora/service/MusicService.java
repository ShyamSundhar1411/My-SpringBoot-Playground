package com.axionlabs.meldora.service;


import com.axionlabs.meldora.dto.music.ItemDto;
import com.axionlabs.meldora.dto.music.TrackDto;

public interface MusicService {
    TrackDto getTracks(String q);
    ItemDto getTrackById(String trackId);
}
