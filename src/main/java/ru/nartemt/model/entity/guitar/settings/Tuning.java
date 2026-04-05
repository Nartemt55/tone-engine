package ru.nartemt.model.entity.guitar.settings;

public enum Tuning {
    E_STANDARD(0),
    D_SHARP_STANDARD(1),
    D_STANDARD(2),
    C_SHARP_STANDARD(3),
    C_STANDARD(4),
    B_STANDARD(5),

    DROP_D(2),
    DROP_C_SHARP(3),
    DROP_C(4),
    DROP_B(5),
    DROP_A(6);

    private final int pitchOffset;

    Tuning(int pitchOffset) {
        this.pitchOffset = pitchOffset;
    }

    public int getPitchOffset() {
        return pitchOffset;
    }
}
