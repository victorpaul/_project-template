package com.projecttemplategroup.projecttemplateartifact.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaUtilImpl implements JavaUtil {

    @Override
    public List<Long> proxy(List<Long> value) {
        return value;
    }

    @Override
    public BigDecimal proxy(BigDecimal value) {
        return value;
    }

    @Override
    public List<Long> getArray(int size) {
        List<Long> array = new ArrayList<>(size);

        for (long i = 0; i < size; i++) {
            array.add(new java.lang.Long(i));
        }
        return proxy(array);
    }

    @Override
    public BigDecimal getBigDecimal(float value) {
        return proxy(BigDecimal.valueOf(value));
    }
}
