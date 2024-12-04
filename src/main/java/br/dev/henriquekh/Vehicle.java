package br.dev.henriquekh;

public class Vehicle {
    private String make;
    private String model;
    private String plate;

    public Vehicle(String make, String model, String plate) {
        setMake(make);
        setModel(model);
        setPlate(plate);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Veiculo" +
                "\n\tMarca: " + getMake() +
                "\n\tModelo: " + getModel() +
                "\n\tPlaca: " + getPlate();
    }
}
