package itis.semestrovka.services.implementations;


import itis.semestrovka.services.interfaces.TemplateProcessor;
import itis.semestrovka.services.interfaces.TemplateResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateProcessorImpl implements TemplateProcessor {
    private TemplateResolver templateResolver;
    private Map<String,String> template;

    public TemplateProcessorImpl(TemplateResolver templateResolver,
                                 @Qualifier(value = "templateParameters") Map<String, String> templateParameters) {
        this.templateResolver = templateResolver;
        this.template = templateParameters;
    }

    @Override
    public String getProcessedTemplate(Map<String, String> params, String ftl) {
        template.putAll(params);
        return templateResolver.process(ftl, template);
    }
}
