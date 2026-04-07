package ru.nartemt.tone_engine_ver2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tone-engine")
public class ScoringConfig {

    private Scoring scoring = new Scoring();

    public Scoring getScoring() {
        return scoring;
    }

    public void setScoring(Scoring scoring) {
        this.scoring = scoring;
    }

    public static class Scoring {

        private Frequency frequency = new Frequency();
        private Bonus bonus = new Bonus();
        private double warmth;

        public Frequency getFrequency() {
            return frequency;
        }

        public void setFrequency(Frequency frequency) {
            this.frequency = frequency;
        }

        public Bonus getBonus() {
            return bonus;
        }

        public void setBonus(Bonus bonus) {
            this.bonus = bonus;
        }

        public double getWarmth() {
            return warmth;
        }

        public void setWarmth(double warmth) {
            this.warmth = warmth;
        }

        public static class Frequency {
            private double high;
            private double mid;
            private double low;

            public double getHigh() {
                return high;
            }

            public void setHigh(double high) {
                this.high = high;
            }

            public double getMid() {
                return mid;
            }

            public void setMid(double mid) {
                this.mid = mid;
            }

            public double getLow() {
                return low;
            }

            public void setLow(double low) {
                this.low = low;
            }
        }


        public static class Bonus {
            private double gainMatch;
            private double pickupMatch;

            public double getGainMatch() {
                return gainMatch;
            }

            public void setGainMatch(double gainMatch) {
                this.gainMatch = gainMatch;
            }

            public double getPickupMatch() {
                return pickupMatch;
            }

            public void setPickupMatch(double pickupMatch) {
                this.pickupMatch = pickupMatch;
            }
        }
    }
}
