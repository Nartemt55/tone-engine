package ru.nartemt.tone_engine_ver2.model.entity.album;

public enum Genre {

    // ROCK
    CLASSIC_ROCK("Classic Rock"),
    GRUNGE("Grunge"),
    PUNK_ROCK("Punk Rock"),
    HARD_ROCK("Hard Rock"),
    PROGRESSIVE_ROCK("Progressive Rock"),
    PSYCHEDELIC_ROCK("Psychedelic Rock"),
    ALTERNATIVE_ROCK("Alternative Rock"),

    // OLD SCHOOL METAL
    HEAVY_METAL("Heavy Metal"),
    THRASH_METAL("Thrash Metal"),
    SPEED_METAL("Speed Metal"),
    GROOVE_METAL("Groove Metal"),

    // EXTREME METAL
    DEATH_METAL("Death Metal"),
    MELODIC_DEATH_METAL("Melodic Death Metal"),
    BLACK_METAL("Black Metal"),
    DOOM_METAL("Doom Metal"),

    // MODERN METAL
    NU_METAL("Nu-Metal"),
    POWER_METAL("Power Metal"),
    INDUSTRIAL_METAL("Industrial Metal");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
