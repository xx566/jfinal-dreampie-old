package cn.dreampie.common.util;


import cn.dreampie.common.config.AppConstants;
import cn.dreampie.common.util.security.EncriptionUtils;
import cn.dreampie.common.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;

/**
 * Created by wangrenhui on 14-4-24.
 */
public class SubjectUtils {

  private static SubjectUtils subjectUtils = new SubjectUtils();

  private static String[] baseRole = new String[]{"R_ADMIN", "R_MANAGER", "R_MEMBER", "R_USER"};

  private SubjectUtils() {
  }

  public static SubjectUtils me() {
    return subjectUtils;
  }

  public Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  public Session getSession() {
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    if (session == null) {
      throw new UnknownSessionException("Unable found required Session");
    } else {
      return session;
    }
  }

  /**
   * 获取用户对象
   *
   * @param <T> User
   * @return T User
   */
  public <T extends User> T getUser() {
    Session session = getSession();
    Object user = session.getAttribute(AppConstants.CURRENT_USER);
    if (ValidateUtils.me().isNullOrEmpty(user))
      return null;
    else
      return (T) user;
  }

  /**
   * login user
   *
   * @param username 用户名
   * @param password 密码
   * @param user     完整用户对象
   * @param <T>      User
   * @return bolean
   */
  public <T extends User> boolean login(String username, String password, T user) {
    return login(username, password, false, user);
  }

  public <T extends User> boolean login(String username, String password, boolean rememberMe, T user) {
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    try {
      token.setRememberMe(rememberMe);
      SecurityUtils.getSubject().login(token);
      Session session = getSession();
      session.setAttribute(AppConstants.CURRENT_USER, user);
      return true;
    } catch (AuthenticationException e) {
      return false;
    }
  }

  /**
   * 验证验证码
   *
   * @param captchaToken token
   * @return boolean
   */
  public boolean doCaptcha(String captchaToken) {
    Session session = getSession();
    if (session.getAttribute(AppConstants.CAPTCHA_NAME) != null) {
      String captcha = session.getAttribute(AppConstants.CAPTCHA_NAME).toString();
      if (captchaToken != null &&
          captcha.equalsIgnoreCase(EncriptionUtils.encrypt(captchaToken))) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断是否已经登录
   *
   * @return boolean
   */
  public boolean wasLogin() {
    Subject subject = getSubject();
    if (subject != null && subject.getPrincipal() != null && subject.isAuthenticated()) {
      return true;
    }
    return false;
  }

  public boolean wasBaseRole(String roleValue) {

    if (ArrayUtils.contains(baseRole, roleValue)) {
      return true;
    }
    return false;
  }

  public static String[] getBaseRole() {
    return baseRole;
  }

  public static void setBaseRole(String[] baseRole) {
    SubjectUtils.baseRole = baseRole;
  }
}
