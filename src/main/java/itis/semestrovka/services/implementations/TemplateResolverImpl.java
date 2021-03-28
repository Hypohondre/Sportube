package itis.semestrovka.services.implementations;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import itis.semestrovka.services.interfaces.TemplateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateResolverImpl implements TemplateResolver {
    private final Configuration configuration;

    @Override
    public String process(String name, Map<String, String> root) {
        try {
            Template t = configuration.getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}

