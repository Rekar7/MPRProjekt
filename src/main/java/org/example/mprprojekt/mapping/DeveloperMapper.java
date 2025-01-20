package org.example.mprprojekt.mapping;

import org.example.mprprojekt.models.developer.Developer;
import org.example.mprprojekt.models.developer.DeveloperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    DeveloperResponse mapToDeveloperResponse(Developer developer);
    @Mapping(target = "uuid", ignore = true)
    Developer mapToDeveloper(DeveloperResponse developerResponse);
}
