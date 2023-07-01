package pl;


import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.awt.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest(classes = JobAttributes.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@Testcontainers
public class BaseIntegrationTest {
    public static final String WIRE_MOCK_HOST = "http://localhost";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public MockMvc mockMvc;

    @Container
    public static final MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));


    @RegisterExtension
    public static WireMockExtension wireMockExtension = WireMockExtension
            .newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


}
