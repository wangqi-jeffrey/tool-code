package com.tyiti.order.web.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;

public class MyExtraProcessor implements ExtraProcessor, ExtraTypeProvider {

	@Override
	public void processExtra(Object obj, String key, Object value) {
		if (!obj.getClass().getPackage().getName().startsWith("com.tyiti"))
			return;

		String camelKey = PropertyUtil.underlineToCamel(key);
		PropertyDescriptor property = BeanUtils.getPropertyDescriptor(obj.getClass(), camelKey);
		if (property == null)
			return;
		PropertyUtil.setProperty(obj, camelKey, value);
	}

	@Override
	public Type getExtraType(Object obj, String key) {
		String camelKey = PropertyUtil.underlineToCamel(key);
		PropertyDescriptor property = BeanUtils.getPropertyDescriptor(obj.getClass(), camelKey);
		if (property == null)
			return null;
		else
			return property.getPropertyType();
	}

}
