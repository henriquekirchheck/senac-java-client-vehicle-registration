package br.dev.henriquekh;

public class Client {
    private String name;
    private String phone;
    private String email;

    public Client(String name, String phone, String email) {
        setEmail(email);
        setName(name);
        setPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cliente" +
                "\n\tNome: " + name +
                "\n\tTelefone: " + phone +
                "\n\tEmail: " + email ;
    }
}
