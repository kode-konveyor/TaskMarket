package com.kodekonveyor.market.register;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class LoginController {

  @GetMapping(UrlMapConstants.LOGIN_PATH)
  public RedirectView call(final String next) {
    return new RedirectView(next);
  }

}
