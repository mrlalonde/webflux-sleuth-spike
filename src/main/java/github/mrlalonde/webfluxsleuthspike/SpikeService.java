package github.mrlalonde.webfluxsleuthspike;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
class SpikeService {
    private final Tracer tracer;

    SpikeService(Tracer tracer) {
        this.tracer = tracer;
    }

    public Mono<String> hello() {
        var currentSpanCustomizer = tracer.currentSpanCustomizer();
        if (currentSpanCustomizer == null) {
            log.error("No currentSpanCustomizer!");
            return Mono.empty();
        }
        currentSpanCustomizer.tag("hello", "world");
        return Mono.just("world");
    }
}
