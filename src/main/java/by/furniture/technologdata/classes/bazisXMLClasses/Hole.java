package by.furniture.technologdata.classes.bazisXMLClasses;

/**
 * класс для хранения данных об отверстиях
 */
public class Hole {
    private Float positionX;
    private Float positionY;
    private Float positionZ;
    private Float diameter;
    private String type;
    private Float depth;
    private Float directionX;
    private Float directionY;
    private Float directionZ;

    public Hole() {
    }

    public Float getPositionX() {
        return positionX;
    }

    public void setPositionX(Float positionX) {
        this.positionX = positionX;
    }

    public Float getPositionY() {
        return positionY;
    }

    public void setPositionY(Float positionY) {
        this.positionY = positionY;
    }

    public Float getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(Float positionZ) {
        this.positionZ = positionZ;
    }

    public Float getDiameter() {
        return diameter;
    }

    public void setDiameter(Float diameter) {
        this.diameter = diameter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getDirectionX() {
        return directionX;
    }

    public void setDirectionX(Float directionX) {
        this.directionX = directionX;
    }

    public Float getDirectionY() {
        return directionY;
    }

    public void setDirectionY(Float directionY) {
        this.directionY = directionY;
    }

    public Float getDirectionZ() {
        return directionZ;
    }

    public void setDirectionZ(Float directionZ) {
        this.directionZ = directionZ;
    }

    @Override
    public String toString() {
        return "Hole{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", positionZ=" + positionZ +
                ", diameter=" + diameter +
                ", type='" + type + '\'' +
                ", depth=" + depth +
                ", directionX=" + directionX +
                ", directionY=" + directionY +
                ", directionZ=" + directionZ +
                '}';
    }
}
