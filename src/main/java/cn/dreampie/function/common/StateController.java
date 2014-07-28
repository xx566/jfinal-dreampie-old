package cn.dreampie.function.common;

import com.jfinal.plugin.ehcache.CacheName;

/**
 * Created by wangrenhui on 14-1-3.
 */
public class StateController extends cn.dreampie.common.web.controller.Controller {

  public void index() {
    dynaRender("/view/index.ftl");
  }

  @CacheName(cn.dreampie.common.config.AppConstants.DEFAULT_CACHENAME)
  public void own() {
    setAttr("states", State.dao.findBy("`state`.deleted_at is NULL"));
    dynaRender("/view/index.ftl");
  }

  @CacheName(cn.dreampie.common.config.AppConstants.DEFAULT_CACHENAME)
  public void one() {
    String type = getPara("state.type");
    String value = getPara("state.value");
    if (!cn.dreampie.common.util.ValidateUtils.me().isNullOrEmpty(type) && !cn.dreampie.common.util.ValidateUtils.me().isNullOrEmpty(value) && cn.dreampie.common.util.ValidateUtils.me().isPositiveNumber(value)) {
      setAttr("state", State.dao.findFirstBy("`state`.type=? AND `state`.value=?", type, value));
    }
    dynaRender("/view/index.ftl");
  }
}
