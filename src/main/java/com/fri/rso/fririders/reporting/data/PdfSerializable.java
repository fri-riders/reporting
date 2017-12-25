package com.fri.rso.fririders.reporting.data;

import java.util.Map;
import java.util.function.Function;

public interface PdfSerializable {
    public <T> Map<String, Function<T,String>> serializationData();
}
