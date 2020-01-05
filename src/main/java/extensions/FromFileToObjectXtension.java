package extensions;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FromFileToObjectXtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        Gson gson = new Gson();
        AnnotationSupport.findAnnotatedFields(context.getRequiredTestClass(), LoadFromJson.class).
                forEach(f -> {
                    String annotationValue = f.getAnnotation(LoadFromJson.class).value();
                    String content = loadFromJson(annotationValue);
                    Object fromJson = gson.fromJson(content, f.getType());
                    try {
                        f.setAccessible(true);
                        f.set(context.getRequiredTestInstance(), fromJson);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private String loadFromJson(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotatedFields(context.getRequiredTestClass(), LoadFromJson.class).
                forEach(f -> {
                    String annotationValue = f.getAnnotation(LoadFromJson.class).value();
                    try {
                        f.setAccessible(true);
                        f.set(context.getRequiredTestInstance(), null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
