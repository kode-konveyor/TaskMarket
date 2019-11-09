package com.kodekonveyor.market.register;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

  @GetMapping("/member/login")
  public RedirectView call(final String next) {
    return new RedirectView(next);
  }

}
