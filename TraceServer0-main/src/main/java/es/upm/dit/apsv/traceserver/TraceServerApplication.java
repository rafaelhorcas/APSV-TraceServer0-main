package es.upm.dit.apsv.traceserver;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.apsv.traceserver.model.Trace;
//import es.upm.dit.apsv.traceserver.model.TransportationOrder;
import es.upm.dit.apsv.traceserver.repository.TraceRepository;

@SpringBootApplication
public class TraceServerApplication {

	@Autowired
	private Environment env;
	
	public static final Logger log = LoggerFactory.getLogger(TraceServerApplication.class);

	private final TraceRepository tr;

	public TraceServerApplication(TraceRepository tr) {
		this.tr = tr;
	}

	public static void main(String[] args) {
		SpringApplication.run(TraceServerApplication.class, args);
		log.info("Prueba consumer arrancando...");
	}

	@Bean("consumer")
	public Consumer<Trace> checkTrace() {
		return t -> {
                          log.info("Order: "+ t);
			}
		};
	}
}
