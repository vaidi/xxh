package com.erlong.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionFactory;

public class ShutDownHook implements ExtensionFactory {


    /**
     * Get extension.
     *
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    @Override
    public <T> T getExtension(Class<T> type, String name) {
        return null;
    }
}
