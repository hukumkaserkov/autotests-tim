package models;

public class ModifiedBy {

    public String login;
    public String fio;

    public ModifiedBy(String login, String fio){
        this.login = login;
        this.fio = fio;
    }

    public static ModifiedBy setDefaultValues() {
        return new ModifiedBy("NIKTEST", "Tester Н.");
    }

    public String getLogin() {
        return login;
    }

    public String getFio() {
        return fio;
    }
}
