package ru.nartemt.tone_engine_ver2.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public Long getCurrentUserId() throws AccessDeniedException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails)
            return customUserDetails.user().getId();

        throw new AccessDeniedException("User is not authorized");
    }
}
