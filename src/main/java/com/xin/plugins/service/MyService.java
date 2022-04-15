package com.xin.plugins.service;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.Converter;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.thoughtworks.xstream.converters.time.LocalDateTimeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Storage(StoragePathMacros.WORKSPACE_FILE)
class MyService implements PersistentStateComponent<MyService> {

    private static final MyService myService = new MyService();

    public static MyService getInstance() {
        // implementation according to Application/Project level service
        return myService;
    }

    public String stateValue;

    public MyService getState() {
        return this;
    }

    public void loadState(MyService state) {
//        System.out.println("init Service");
        XmlSerializerUtil.copyBean(state, this);
    }

    class State {
        @OptionTag(converter = LocalDateTimeConverter.class)
        public LocalDateTime dateTime;
    }

    class LocalDateTimeConverter extends Converter<LocalDateTime> {
        public LocalDateTime fromString(String value) {
            long epochMilli = Long.parseLong(value);
            ZoneId zoneId = ZoneId.systemDefault();
            return Instant.ofEpochMilli(epochMilli).atZone(zoneId).toLocalDateTime();
        }

        public String toString(LocalDateTime value) {
            ZoneId zoneId = ZoneId.systemDefault();
            long toEpochMilli = value.atZone(zoneId).toInstant().toEpochMilli();
            return Long.toString(toEpochMilli);
        }
    }
}