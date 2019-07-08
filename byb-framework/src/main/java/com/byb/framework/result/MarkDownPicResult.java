package com.byb.framework.result;

import com.byb.framework.utils.stomp.JsonUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author chenzeting
 * @Date 2019-01-11
 * @Description:
 **/
@Getter
@Setter
@Accessors(chain = true)
@Slf4j
@NoArgsConstructor
public class MarkDownPicResult<T> implements Serializable  {

    private static final long serialVersionUID = -1344661712443844501L;
    /**
     * 请求成功状态码
     */
    public static final int SUCCESS = 1;

    /**
     * 错误码
     */
    private int success;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回数据
     */
    private String url;

    public MarkDownPicResult(int success, String msg){
        this.success = success;
        this.message = msg;
    }

    public static <T> MarkDownPicResult<Object> success(){
        return new MarkDownPicResult<>(SUCCESS, "request success");
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.toJson(this);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "request error";
        }
    }
}
