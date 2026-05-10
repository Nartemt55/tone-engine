package ru.nartemt.tone_engine_ver2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "tone-advisor")
public class ScoringConfig {

    private Scoring scoring = new Scoring();

    @Getter
    @Setter
    public static class Scoring {

        private Frequency frequency = new Frequency();
        private Bonus bonus = new Bonus();
        private Balance balance = new Balance();
        private double warmth;
        private double outputPower;

        @Getter
        @Setter
        public static class Frequency {
            private double high;
            private double mid;
            private double low;
        }

        @Getter
        @Setter
        public static class Bonus {
            private double gainMatch;
            private double pickupMatch;
        }

        @Getter
        @Setter
        public static class Balance {
            private double guitar;
            private double amplifier;
            private double pedal;
        }
    }
}
