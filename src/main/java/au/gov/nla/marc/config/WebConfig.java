package au.gov.nla.marc.config;

import au.gov.nla.marc.viewResolver.CsvViewResolver;
import au.gov.nla.marc.viewResolver.ExcelViewResolver;
import au.gov.nla.marc.viewResolver.PdfViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

    public void configureContentNegotiation (ContentNegotiationConfigurer configurer){
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .favorPathExtension(true);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<>();

        resolvers.add(csvViewResolver());
        resolvers.add(excelViewResolver());
        resolvers.add(pdfViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;

    }

    private ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

    private ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }

    private ViewResolver csvViewResolver() {
        return new CsvViewResolver();
    }
}
