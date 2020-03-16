package app.shenbh.myscframe.ui.qrcode;

import com.google.zxing.EncodeHintType;

import java.util.Map;

/**
 * @author shenbh
 * @date 2018/5/1
 * @e-mail 93234929@qq.com
 * 维护者
 */
public interface IEncodeHint {

    Map<EncodeHintType, Object> getEncodeHintMap();
}
