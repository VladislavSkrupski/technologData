package by.furniture.technologdata.classes.techClasses;

public class TechnologData {
    private String Name;
    private String Amount;
    private String Unit;

    public TechnologData(String name, String amount, String unit) {
        Name = name;
        Amount = amount;
        Unit = unit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    @Override
    public String toString() {
        return "TechnologData{" +
                "Name='" + Name + '\'' +
                ", Amount='" + Amount + '\'' +
                ", Unit='" + Unit + '\'' +
                '}';
    }
}
