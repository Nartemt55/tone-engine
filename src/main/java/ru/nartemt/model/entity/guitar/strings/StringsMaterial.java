package ru.nartemt.model.entity.guitar.strings;

public enum StringsMaterial {
    STAINLESS_STEEL(10),
    PURE_NICKEL(4),
    NICKEL_PLATED_STEEL(7),
    COBALT(9);

    private final int trebleResponse;

    StringsMaterial(int trebleResponse) {
        this.trebleResponse = trebleResponse;
    }

    public int getTrebleResponse() {
        return trebleResponse;
    }
}
