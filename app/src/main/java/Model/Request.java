package Model;

import java.util.List;

public class Request {
    private String phone,address,total,name,status;
    private List<Order> foods;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request() {

    }

    public Request(String phone, String address, String total, String name, List<Order> foods) {
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.name = name;
        this.foods = foods;
        this.status="0";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
