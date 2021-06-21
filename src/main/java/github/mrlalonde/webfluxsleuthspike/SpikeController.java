package github.mrlalonde.webfluxsleuthspike;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
class SpikeController {
    private final SpikeService spikeService;

    @GetMapping("/hello")
    Mono<String> hello() {
        return spikeService.hello();
    }
}
