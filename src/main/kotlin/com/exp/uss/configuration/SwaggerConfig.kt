package com.exp.uss.configuration

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.Contact
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:14
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .securitySchemes(ArrayList(Arrays.asList(ApiKey("Bearer %token", "Authorization", "Header"))))
                .tags(Tag("users", "Operations about users"))
                .tags(Tag("documents", "Operations about documents"))
                .tags(Tag("ping", "Just a ping"))
                .genericModelSubstitutes(Optional::class.java)
    }

    private fun metadata(): ApiInfo? {
        return ApiInfoBuilder()
                .title("Example User API")
                .description("Example User Management API")
                .version("1.0.0")
                .contact(Contact(null, null, "m.taweechai@gmail.com"))
                .build()
    }

}