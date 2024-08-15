public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private double balance;
    public User() {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public double getBalance(){return balance;}
    public void setId(int Id){

        this.id=Id;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public void setName(String name){

        this.name=name;
    }
    public void setPassword(String password){

        this.password=password;
    }
    public void setEmail(String email){

        this.email=email;
    }




}

