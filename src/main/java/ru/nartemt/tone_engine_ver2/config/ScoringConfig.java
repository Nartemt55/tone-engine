package ru.nartemt.tone_engine_ver2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tone-advisor")
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
        private Balance balance = new Balance();
        private double warmth;
        private double outputPower;

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

        public Balance getBalance() {
            return balance;
        }

        public void setBalance(Balance balance) {
            this.balance = balance;
        }

        public double getWarmth() {
            return warmth;
        }

        public void setWarmth(double warmth) {
            this.warmth = warmth;
        }

        public double getOutputPower() {
            return outputPower;
        }

        public void setOutputPower(double outputPower) {
            this.outputPower = outputPower;
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

        public static class Balance {
            private double guitar;
            private double amplifier;
            private double pedal;

            public double getGuitar() {
                return guitar;
            }

            public void setGuitar(double guitar) {
                this.guitar = guitar;
            }

            public double getAmplifier() {
                return amplifier;
            }

            public void setAmplifier(double amplifier) {
                this.amplifier = amplifier;
            }

            public double getPedal() {
                return pedal;
            }

            public void setPedal(double pedal) {
                this.pedal = pedal;
            }
        }
    }
}
