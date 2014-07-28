package cn.dreampie.function.user;

import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * Created by wangrenhui on 14-4-17.
 */
@TableBind(tableName = "sec_token", pkName = "uuid")
public class Token extends cn.dreampie.common.web.model.Model<Token> {
  public static Token dao = new Token();

}