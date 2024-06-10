package upc.edu.LoggyAPI.user.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.user.dto.ProfileRequest;
import upc.edu.LoggyAPI.user.dto.ProfileResponseToUser;
import upc.edu.LoggyAPI.user.model.Profile;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
    Profile profileRequestToProfile(ProfileRequest profileRequest);
}
