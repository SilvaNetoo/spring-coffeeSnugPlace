package br.com.spring.back.coffeSnugPlace.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * A classe SwaggerConfig criada no pacote
 * br.com.spring.back.coffeSnugPlace.swagger para o projeto CoffeeSnugPlace
 * 
 * @author Criada por Silva Neto
 */
@Configuration
@EnableSwagger2
public class SwaggerCoffeeSnugPlace {

	@Bean
	public Docket postsApi() {

		return new Docket(DocumentationType.SPRING_WEB).groupName("public-api").apiInfo(apiInfo()).select().paths(Predicates.not(PathSelectors.regex("/error"))).build();
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("CoffeeSnugPlace").description("Api projeto CoffeeSnugPlace")
				.contact("Silva Neto").version("1.0").build();
	}

}