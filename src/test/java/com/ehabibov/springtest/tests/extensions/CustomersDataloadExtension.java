package com.ehabibov.springtest.tests.extensions;

import com.ehabibov.springtest.config.PropertiesStorage;
import com.ehabibov.springtest.context.ApplicationContextSingleton;
import com.ehabibov.springtest.resource.RestClient;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomersDataloadExtension extends RestClient implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        PropertiesStorage storage = ApplicationContextSingleton.getContext().getBean(PropertiesStorage.class);
        getExchangeAsSingle(storage.getCustomersJobUrl(),null, String.class);
    }
}
