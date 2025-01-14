package com.xdozhao.iptv.module.live.configuration;

import com.xdozhao.iptv.common.core.constant.TokenConstants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 14:43
 */
@Slf4j
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .components(components());
    }

    private Info info() {
        return new Info()
                .title("module-live")
                .description("live")
                .contact(new Contact().name("xdozhao").email("xdozhao@163.com"))
                .version("v1.0.0");
    }

    private Components components() {
        return new Components().securitySchemes(securitySchemeList());
    }

    /**
     * BasicAuth: type: http scheme: basic BearerAuth: type: http scheme: bearer
     * <p></p>
     * ApiKeyAuth: type: apiKey in: header name: X-API-Key
     * <p></p>
     * OpenID: type: openIdConnect openIdConnectUrl: https://example.com/.well-known/openid-configuration
     * <p></p>
     * OAuth2: type: oauth2 flows: authorization Code: authorization Url: https://example.com/oauth/authorize tokenUrl:
     * https://example.com/oauth/token scopes: read: Grants read access write: Grants write access admin: Grants access
     * to admin operations
     *
     * @return
     */
    private Map<String, SecurityScheme> securitySchemeList() {
        Map<String, SecurityScheme> securitySchemeList = new HashMap<>();
        SecurityScheme authentication = new SecurityScheme()
                .name(TokenConstants.AUTHENTICATION)
                .description("Bearer Authentication")
                /**
                 * apiKey           key: SecurityScheme.name    value: value            eg: In: key: value
                 * http             key: Authorization          value: Bearer/Basic     eg: Header: Authorization: Bearer value.
                 * oauth2
                 * openIdConnect
                 * mutualTLS
                 */
                .type(SecurityScheme.Type.HTTP)
                /**
                 * bearer: Bearer value;
                 * basic: Basic Base64(username:password)
                 */
                .scheme("bearer")
                .bearerFormat("JWT");
        securitySchemeList.put(TokenConstants.AUTHENTICATION, authentication);
        log.debug("SecurityScheme Bearer Authentication:{}", authentication);
        return securitySchemeList;
    }
}
