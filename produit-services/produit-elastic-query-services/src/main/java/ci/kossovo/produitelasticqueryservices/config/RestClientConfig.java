// package ci.kossovo.produitelasticqueryservices.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.elasticsearch.client.ClientConfiguration;
// import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
// import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

// @Configuration
// @EnableElasticsearchRepositories(
//   basePackages = "ci.kossovo.produitelasticqueryservices.repository"
// )
// public class RestClientConfig extends ElasticsearchConfiguration {

//   @Bean
//   @Override
//   public ClientConfiguration clientConfiguration() {
//     return ClientConfiguration.builder().connectedTo("localhost:9200").build();
//   }
// }
