package br.com.microservices.productcatalog.config;

import java.io.IOException;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.empresa.productcatalog.repository")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
		try (var restClients = RestClients.create(clientConfiguration);) {
			return restClients.rest();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Bean
	@Override
	public EntityMapper entityMapper() {
		ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
				new DefaultConversionService());
		entityMapper.setConversions(elasticsearchCustomConversions());
		return entityMapper;
	}
}