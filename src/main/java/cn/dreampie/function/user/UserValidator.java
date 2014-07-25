package cn.dreampie.function.user;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by wangrenhui on 2014/6/10.
 */
public class UserValidator {

    public static class addFollowingValidator extends Validator {

        @Override
        protected void validate(Controller c) {

            boolean idEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("follower.id"));
            if (idEmpty) addError("idMsg", "联系人参数异常");
            if (!idEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPositiveNumber(c.getPara("follower.id")))
                addError("idMsg", "联系人参数异常");
            if (!idEmpty) {
                Follower con = Follower.dao.findById(c.getPara("follower.id"));
                if (cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(con))
                    addError("idMsg", "联系人不存在");
            }
            boolean introEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("follower.intro"));
            if (introEmpty) addError("introMsg", "备注不能为空");
            if (!introEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isLength(c.getPara("follower.intro"), 3, 240))
                addError("introMsg", "备注长度为3-240个字符");

        }

        @Override
        protected void handleError(Controller c) {
            c.keepModel(Follower.class);
            c.keepPara();
            c.setAttr("state", "failure");
            if (cn.dreampie.common.web.thread.ThreadLocalUtil.isJson())
                c.renderJson();
            else
                c.forwardAction("/user/follower?" + c.getRequest().getQueryString());
        }
    }

    public static class deleteFollowingValidator extends Validator {

        @Override
        protected void validate(Controller c) {

            boolean idEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("follower.id"));
            if (idEmpty) addError("idMsg", "联系人参数异常");
            if (!idEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPositiveNumber(c.getPara("follower.id")))
                addError("idMsg", "联系人参数异常");
            if (!idEmpty) {
                Follower con = Follower.dao.findById(c.getPara("follower.id"));
                if (cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(con))
                    addError("idMsg", "联系人不存在");
            }

        }

        @Override
        protected void handleError(Controller c) {
            c.keepModel(Follower.class);
            c.keepPara();
            c.setAttr("state", "failure");
            if (cn.dreampie.common.web.thread.ThreadLocalUtil.isJson())
                c.renderJson();
            else
                c.forwardAction("/user/following?" + c.getRequest().getQueryString());
        }
    }

    public static class UpdateIntroValidator extends Validator {

        @Override
        protected void validate(Controller c) {

            boolean idEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("follower.id"));
            if (idEmpty) addError("idMsg", "联系人参数异常");
            if (!idEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPositiveNumber(c.getPara("follower.id")))
                addError("idMsg", "联系人参数异常");
            if (!idEmpty) {
                Follower con = Follower.dao.findById(c.getPara("follower.id"));
                if (cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(con))
                    addError("idMsg", "联系人不存在");
            }
            boolean introEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("follower.intro"));
            if (introEmpty) addError("introMsg", "备注不能为空");
            if (!introEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isLength(c.getPara("follower.intro"), 3, 240))
                addError("introMsg", "备注长度为3-240个字符");

        }

        @Override
        protected void handleError(Controller c) {
            c.keepModel(Follower.class);
            c.keepPara();
            c.setAttr("state", "failure");
            if (cn.dreampie.common.web.thread.ThreadLocalUtil.isJson())
                c.renderJson();
            else
                c.forwardAction("/user/following?" + c.getRequest().getQueryString());
        }
    }


    public static class UpdatePwdValidator extends Validator {

        @Override
        protected void validate(Controller c) {

            boolean idEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("user.id"));
            if (idEmpty) addError("user_idMsg", "账户编号丢失");
            if (!idEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPositiveNumber(c.getPara("user.id")))
                addError("user_idMsg", "账户编号必须为数字");

            if (cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(User.dao.findBy("`user`.id=" + c.getPara("user.id"))))
                addError("user_idMsg", "账户不存在");

            boolean userEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("user.username"));
            if (userEmpty) addError("user_usernameMsg", "账户丢失");
            if (!userEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isUsername(c.getPara("user.username")))
                addError("user_usernameMsg", "账户为英文字母 、数字和下划线长度为5-18");

            boolean passwordEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("user.password"));
            if (passwordEmpty) addError("user_passwordMsg", "密码不能为空");
            if (!passwordEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPassword(c.getPara("user.password")))
                addError("user_passwordMsg", "密码为英文字母 、数字和下划线长度为5-18");

            if (!passwordEmpty && !c.getPara("user.password").equals(c.getPara("repassword")))
                addError("repasswordMsg", "重复密码不匹配");


            boolean oldpasswordEmpty = cn.dreampie.common.utils.ValidateUtils.me().isNullOrEmpty(c.getPara("oldpassword"));
            if (oldpasswordEmpty) addError("user_oldpasswordMsg", "原始密码不能为空");

            if (!oldpasswordEmpty && !cn.dreampie.common.utils.ValidateUtils.me().isPassword(c.getPara("oldpassword")))
                addError("user_oldpasswordMsg", "密码为英文字母 、数字和下划线长度为5-18");

            if (!oldpasswordEmpty) {
                User user = cn.dreampie.common.utils.SubjectUtils.me().getUser();

                if (user.getStr("hasher").equals(cn.dreampie.common.plugin.shiro.hasher.Hasher.DEFAULT.value())) {
                    boolean match = cn.dreampie.common.plugin.shiro.hasher.HasherUtils.me().match(c.getPara("oldpassword"), user.getStr("password"), cn.dreampie.common.plugin.shiro.hasher.Hasher.DEFAULT);

                    if (!match) {
                        addError("user_oldpasswordMsg", "原始密码不匹配");
                    }
                } else {
                    addError("user_oldpasswordMsg", "不支持的加密方式");
                }
            }
        }

        @Override
        protected void handleError(Controller c) {
            c.keepModel(User.class);
            c.keepPara();
            c.setAttr("state", "failure");
            if (cn.dreampie.common.web.thread.ThreadLocalUtil.isJson())
                c.renderJson();
            else
                c.forwardAction("/user/center");
        }
    }
}
