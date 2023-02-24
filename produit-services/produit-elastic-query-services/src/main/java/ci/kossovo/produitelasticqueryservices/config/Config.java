package ci.kossovo.produitelasticqueryservices.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ci.kossovo.produitelasticqueryservices.repository")
@ComponentScan(basePackages = { "ci.kossovo.produitelasticqueryservices" })
public class Config extends AbstractElasticsearchConfiguration {

  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {
    ClientConfiguration clientConfiguration = ClientConfiguration
      .builder()
      .connectedTo("localhost:9200")
      .build();

    return RestClients.create(clientConfiguration).rest();
  }
  
}
