package cn.dreampie.function.common;

import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * Created by wangrenhui on 14-4-16.
 */
@TableBind(tableName = "com_state")
public class State extends cn.dreampie.common.web.model.Model<State> {
  public static State dao = new State();

}
