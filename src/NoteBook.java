public class NoteBook {
    private String model;
    private int operativeMemory;
    private int hardDisk;
    private String operationSystem;
    private String color;

    NoteBook(String model, int operativeMemory, int hardDisk, String operationSystem, String color) {
        this.model = model;
        this.operativeMemory = operativeMemory;
        this.hardDisk = hardDisk;
        this.operationSystem = operationSystem;
        this.color = color;
    }

    String getModel() {
        return this.model;
    }

    int getOperativeMemory() {
        return this.operativeMemory;
    }

    int getHardDisk() {
        return this.hardDisk;
    }

    String getOperationSystem() {
        return this.operationSystem;
    }

    String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        String toString = "Model: " + model + ", OM: " + operativeMemory + ", HD: " + hardDisk + ", OS: "
                + operationSystem + ", Color: " + color;
        return toString;
    }
}
