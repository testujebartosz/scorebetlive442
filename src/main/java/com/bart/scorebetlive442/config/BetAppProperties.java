package com.bart.scorebetlive442.config;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties(prefix = "bet-app")
@Data
@Validated
public class BetAppProperties {
    @Positive private Long matchLength;
    private String drugiProperty;
    @NotNull private Duration duration;

    @NestedConfigurationProperty private MyNestedProperties myNestedProperties;

    public BetAppProperties(@DefaultValue("60") Long matchLength,
                            String drugiProperty,
                            @DefaultValue("45s") Duration duration,
                            @DefaultValue MyNestedProperties myNestedProperties) {
        this.matchLength = matchLength;
        this.drugiProperty = drugiProperty;
        this.duration = duration;
        this.myNestedProperties = myNestedProperties;
        System.out.println("jestem tutaj");
    }

    @Data
    public static class MyNestedProperties {
        private String a;
        private String b;
    }
}
