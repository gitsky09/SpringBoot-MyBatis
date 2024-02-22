package com.deploy.demo;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class CustomKeyGenerator implements KeyGenerator{

	/**
	 * [自訂實作基本的keygenerator]
	 * {例如，根據不同的參數生成不同的快取鍵，或者使用特定的邏輯進行鍵的組合}
	 */
	@Override
	public Object generate(Object target, Method method, Object... params) {
		 // 這裡示範使用方法的參數作為快取鍵
        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getSimpleName()).append(".");
        key.append(method.getName()).append("[");
        for (Object param : params) {
            key.append(param.toString()).append(",");
        }
        key.append("]");
        return key.toString();
	}

}
