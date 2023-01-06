package ci.kossovo.customerqueryservicce.config;

// @Configuration
public class SerialisationConfig {

    // @Bean
    // @Primary
    // public JacksonSerializer axonJacksonSerializer(ObjectMapper objectMapper) {
    //     return JacksonSerializer.builder()
    //                             .objectMapper(objectMapper)
    //                             .defaultTyping()
    //                             .build();
    // }


    //  // This ensures the XStream instance used is allowed to de-/serializer this demo's classes
    //  @Bean
    //  public XStream xStream() {
    //      XStream xStream = new XStream();
    //      xStream.allowTypesByWildcard(new String[]{"io.axoniq.demo.giftcard.**"});
    //      return xStream;
    //  }
}
