package cp.csv.parses.cvsparses.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="application")
@Getter
@Setter
public class AppProperties {
    private String inputFile;
    private String output;
}
