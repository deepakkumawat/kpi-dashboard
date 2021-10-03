package com.martech.analytics.dataprocessing.support;

import com.fasterxml.classmate.TypeResolver;
import com.martech.analytics.dataprocessing.controller.KPIDataController;
import com.martech.analytics.dataprocessing.model.enums.HeaderNames;
import com.martech.analytics.dataprocessing.model.error.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    /**
     * Controls the initialization of swagger plugin instance. Intended to toggle Swagger endpoint per environment.
     * Defaults to {@code true}.
     */
    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    private static Example createParameterExampleValue(Object value, String description) {
        return new Example(String.valueOf(System.currentTimeMillis()), "Summary not available", description, value, null, MediaType.TEXT_PLAIN_VALUE);
    }

    private static Response errorResponseModel() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }

    private static ApiInfo apiInfo() {
        return new ApiInfo("KPI-Data-Processing", "A service to provide data for KPI", "1.0",
                "Martech T&C URL", author(), "Martech License", null, Collections.emptyList());
    }

    private static Contact author() {
        return new Contact("deepakk@abc.app", null, "https://www.martech.com/");
    }

    @Bean
    public Docket apiDocket() {
        List<Response> commonResponses = Collections.singletonList(errorResponseModel());
        Tag[] tags = tags();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .tags(tags[0], Arrays.copyOfRange(tags, 1, tags.length))
                .select()
                .apis(RequestHandlerSelectors.basePackage(KPIDataController.class.getPackage().getName()))
                .paths(PathSelectors.any()).paths(PathSelectors.regex("/error.*").negate()).build()

                .useDefaultResponseMessages(false)

                .globalRequestParameters(Arrays.asList(uniqueId()))

                .globalResponses(HttpMethod.GET, commonResponses)
                .globalResponses(HttpMethod.POST, commonResponses)

                .additionalModels(new TypeResolver().resolve(ErrorResponseModel.class))
                .ignoredParameterTypes(File.class, Resource.class, InputStream.class);
    }

    private Tag[] tags() {
        return new Tag[]{
                new Tag("KPIController", "Maintains APIs responsible to provide and load the KPI data")
        };
    }

    private RequestParameter uniqueId() {
        return new RequestParameterBuilder()
                .name(HeaderNames.REQUEST_ID.getValue())
                .description("Unique request Id")
                .accepts(Collections.singleton(MediaType.TEXT_PLAIN))
                .in(ParameterType.HEADER)
                .required(false)
                .example(createParameterExampleValue(UUID.randomUUID().toString(), "Request push id. Client should pass-in unique value with every request"))
                .build();
    }

}
