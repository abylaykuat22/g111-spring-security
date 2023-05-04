package bitlab.g111.springsecurity.controllers;

import bitlab.g111.springsecurity.models.User;
import bitlab.g111.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/")
  public String homePage() {
    return "home";
  }

  @PreAuthorize("isAnonymous()")
  @GetMapping("/signin")
  public String signinPage() {
    return "signin";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/profile")
  public String profilePage(Model model) {
    User user = userService.getCurrentUser();
    model.addAttribute("currentUser", user);
    return "profile";
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @GetMapping("/adminpanel")
  public String adminPanelPage() {
    return "adminpanel";
  }
}
