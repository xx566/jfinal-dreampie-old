/**
 * Copyright (c) 2011-2013, kidzhou 周磊 (zhouleib1412@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dreampie.common.plugin.sqlinxml;

import com.jfinal.plugin.IPlugin;

public class SqlInXmlPlugin implements IPlugin {

  private String[] paths = null;

  /**
   * default dir is /sql
   */
  public SqlInXmlPlugin() {
  }

  /**
   * set sql file dir
   * @param paths dir
   */
  public SqlInXmlPlugin(String... paths) {
    this.paths = paths;
  }

  @Override
  public boolean start() {
    if (paths != null)
      SqlKit.init(paths);
    else
      SqlKit.init();
    return true;
  }

  @Override
  public boolean stop() {
    SqlKit.clearSqlMap();
    return true;
  }

}
