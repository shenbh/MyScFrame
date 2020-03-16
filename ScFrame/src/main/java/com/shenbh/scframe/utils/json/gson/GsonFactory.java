package com.shenbh.scframe.utils.json.gson;


import com.shenbh.scframe.utils.json.IJson;
import com.shenbh.scframe.utils.json.IJsonFactory;

/**
 * Gson解析工厂
 *
 * @author shenbh
 * @date 2018/1/18
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class GsonFactory implements IJsonFactory {
    @Override
    public IJson getJson() {
        return new GsonUtils();
    }
}
