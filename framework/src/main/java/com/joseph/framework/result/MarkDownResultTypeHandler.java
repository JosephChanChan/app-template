package com.joseph.framework.result;

import com.joseph.framework.annotation.Json;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MarkDownResultTypeHandler implements HandlerMethodReturnValueHandler, Ordered {

    private ResultJsonSerializer jsonSerializer = new ResultJsonSerializer();

    private final Map<String, FilterProvider> filterProviderMap = new ConcurrentHashMap<>();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
            return returnType.getMethod().getReturnType() == MarkDownPicResult.class;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        MarkDownPicResult result = (MarkDownPicResult) returnValue;
        if(result.getUrl() == null && MarkDownPicResult.SUCCESS == result.getSuccess()){
            Type genericParameterType = returnType.getGenericParameterType();
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType type = (ParameterizedType) genericParameterType;
                Type actualType = type.getActualTypeArguments()[0];
                Class<?> rawType;
                if(actualType instanceof ParameterizedType){
                    rawType = (Class<?>) ((ParameterizedType) actualType).getRawType();
                }else{
                    rawType = (Class<?>) actualType;
                }
                if(Collection.class.isAssignableFrom(rawType)){
                    result.setUrl(null);
                }
            }
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Json[] jsonFilters = returnType.getMethod().getAnnotationsByType(Json.class);
        String resultJson;
        if(jsonFilters != null && jsonFilters.length > 0){
            resultJson = jsonSerializer.writer(getFilterProvider(request.getRequestURI(), jsonFilters))
                    .writeValueAsString(returnValue);
        }else{
            resultJson = jsonSerializer.writeValueAsString(returnValue);
        }
        response.getWriter().write(resultJson);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 构建FilterProvider
     * @param key
     * @param jsonFilters
     * @return
     */
    private FilterProvider getFilterProvider(String key, Json[] jsonFilters){
        FilterProvider provider = filterProviderMap.get(key);
        if(provider != null){
            return provider;
        }
        synchronized (this){
            if(provider == null){
                provider = new DynamicFilterProvider(new DynamicBeanPropertyFilter(jsonFilters));
                filterProviderMap.put(key, provider);
            }
        }
        return provider;
    }

}

