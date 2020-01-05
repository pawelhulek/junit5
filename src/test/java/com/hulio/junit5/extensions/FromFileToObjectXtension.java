package com.hulio.junit5.extensions;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;

public class FromFileToObjectXtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ReflectionSupport.findFields(
                context.getParent().get().getElement().get().getClass(),
                t -> true,
                HierarchyTraversalMode.TOP_DOWN);
    }
}
