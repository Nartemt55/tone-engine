package ru.nartemt.model.entity.guitar.strings;

public enum StringsGauge {
    LIGHT("9-42", 0),
    MEDIUM("10-46", 1),
    HEAVY("11-52", 2),
    BARITONE("12-60", 5);

    private final String label;
    private final int tensionIndex;

    StringsGauge(String label, int tensionIndex) {
        this.label = label;
        this.tensionIndex = tensionIndex;
    }

    public String getLabel() {
        return label;
    }

    public int getTensionIndex() {
        return tensionIndex;
    }
}
