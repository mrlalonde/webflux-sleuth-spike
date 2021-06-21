package github.mrlalonde.webfluxsleuthspike;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
class WebfluxSleuthSpikeApplicationTests {

    @MockBean
    Reporter<Span> zipkinReporter;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void spanCustomizationWorks() {
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk();

        var spanCaptor = ArgumentCaptor.forClass(Span.class);
        Mockito.verify(zipkinReporter).report(spanCaptor.capture());

        var span = spanCaptor.getValue();
        assertThat(span.tags().get("hello"))
                .isEqualTo("world");
    }
}
