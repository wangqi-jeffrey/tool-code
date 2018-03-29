package com.tyiti.order.web.converter;

import com.alibaba.fastjson.serializer.NameFilter;

public class MyNameFilter implements NameFilter {

	@Override
	public String process(Object source, String name, Object value) {
		if (source.getClass().getPackage().getName().startsWith("com.tyiti"))
			return PropertyUtil.camelToUnderline(name);
		else
			return name;
	}

}
