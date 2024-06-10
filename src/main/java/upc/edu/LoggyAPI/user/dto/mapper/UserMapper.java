package upc.edu.LoggyAPI.user.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.user.dto.UserRequest;
import upc.edu.LoggyAPI.user.dto.UserResponse;
import upc.edu.LoggyAPI.user.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User userRequestToUser(UserRequest userRequest);


    UserResponse userToUserResponse(User user);

    List<UserResponse> usersToUsersResponse(List<User> users);
}
