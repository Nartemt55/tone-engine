package ru.nartemt.tone_engine_ver2.model.entity.guitar.settings;

public enum PickupConfig {
    SS(3, 10),
    SSS(3, 10),
    HSS(7, 7),
    HSH(8, 6),
    HH(9, 4);

    private final int outputPower;
    private final int trebleResponse;

    PickupConfig(int outputPower, int trebleResponse) {
        this.outputPower = outputPower;
        this.trebleResponse = trebleResponse;
    }

    public int getOutputPower() {
        return outputPower;
    }

    public int getTrebleResponse() {
        return trebleResponse;
    }
}
