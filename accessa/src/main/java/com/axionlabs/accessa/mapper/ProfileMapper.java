package com.axionlabs.accessa.mapper;

import com.axionlabs.accessa.dto.profile.ProfileDto;
import com.axionlabs.accessa.dto.profile.request.ProfileUpdateRequestDto;
import com.axionlabs.accessa.entity.Profile;

public class ProfileMapper {
    public static Profile mapToProfile(
            ProfileDto profileDto, Profile profile
    ){
        profile.setProfilePicture(profileDto.getProfilePicture());
        profile.setMobileNumber(profileDto.getMobileNumber());
        profile.setAbout(profileDto.getAbout());
        profile.setDateOfBirth(profileDto.getDateOfBirth());
        return profile;
    }

    public static ProfileDto mapToProfileDto(
            ProfileDto profileDto, Profile profile
    ){
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setAbout(profile.getAbout());
        profileDto.setMobileNumber(profile.getMobileNumber());
        profileDto.setProfilePicture(profile.getProfilePicture());
        return profileDto;
    }
    public static Profile mapToProfile(
            ProfileUpdateRequestDto profileData,Profile profile
    ){
        profile.setProfilePicture(profileData.getProfilePicture());
        profile.setMobileNumber(profileData.getMobileNumber());
        profile.setDateOfBirth(profileData.getDateOfBirth());
        profile.setAbout(profileData.getAbout());
        return profile;
    }
}
