package bitlab.g111.springsecurity.services;

import bitlab.g111.springsecurity.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User getCurrentUser();
}
