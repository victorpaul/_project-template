package com.projecttemplategroup.projecttemplateartifact.utils;

import java.math.BigDecimal;
import java.util.List;

public interface JavaUtil {

    List<Long> proxy(List<Long> value);

    List<Long> getArray(int size);

    BigDecimal proxy(BigDecimal value);

    BigDecimal getBigDecimal(float value);

}
