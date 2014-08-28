package cn.dreampie.common.plugin.shiro;

import cn.dreampie.common.model.User;
import cn.dreampie.common.plugin.shiro.hasher.Hasher;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by wangrenhui on 14-1-3.
 */
public class MyPasswordMatcher extends PasswordMatcher {
  private static final Logger logger = LoggerFactory.getLogger(MyPasswordMatcher.class);

  //    public static String Md5Encoding(String rawPassword, String salt) {
//        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
//        md5PasswordEncoder.setEncodeHashAsBase64(false);
//        String result = md5PasswordEncoder.encodePassword(rawPassword, salt);
//        return result;
//    }
//
  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String infoCredentials = String.valueOf(getStoredPassword(info));
//        String tokenCredentials = Md5Encoding(String.valueOf(getSubmittedPassword(token)), ((UsernamePasswordToken) token).getUsername());
//        logger.debug("username:" + ((UsernamePasswordToken) token).getUsername() + ",password:" + getSubmittedPassword(token) + " - " + infoCredentials + " valid " + tokenCredentials);
//        return infoCredentials.equals(tokenCredentials);
    boolean match = false;
    String hasher = ((User<?>) info.getPrincipals().getPrimaryPrincipal()).get("hasher");

    String default_hasher = Hasher.DEFAULT.value();
    if (default_hasher.equals(hasher)) {
      match = super.doCredentialsMatch(token, info);
    }
    return match;
  }

  @Override
  protected Object getSubmittedPassword(AuthenticationToken token) {
    Object submit = super.getSubmittedPassword(token);
    if (submit instanceof char[]) {
      submit = String.valueOf((char[]) submit);
    }
    return submit;
  }

  @Override
  protected Object getStoredPassword(AuthenticationInfo storedUserInfo) {
    Object stored = super.getStoredPassword(storedUserInfo);

    if (stored instanceof char[]) {
      stored = String.valueOf((char[]) stored);
    }
    return stored;
  }
}