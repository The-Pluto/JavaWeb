package src.house;

public class House {
    String owner;
    float price;
    float size;
    int housesCount;

    String houseNumber;

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public House() {
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setHousesCount(int housesCount) {
        this.housesCount = housesCount;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "House{" +
                "owner='" + owner + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", housesCount=" + housesCount +
                '}';
    }

    public float getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public int getHousesCount() {
        return housesCount;
    }
}
