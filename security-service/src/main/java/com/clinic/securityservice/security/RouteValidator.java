package com.clinic.securityservice.security;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.clinic.securityservice.dto.RequestDto;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {
	private List<RequestDto> paths;
    public List<RequestDto> getPaths(){
        return paths;
    }

    public void setPaths(List<RequestDto> paths) {
        this.paths = paths;
    }

    public boolean isAdminPath(RequestDto requestDto){
        return paths.stream().anyMatch(p -> Pattern.matches(
                p.getUri(), requestDto.getUri())
                        && p.getMethod().equals(requestDto.getMethod()));
    }
}
