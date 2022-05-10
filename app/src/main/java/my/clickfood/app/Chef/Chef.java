package my.clickfood.app.Chef;

public class Chef {
    private String Suburban,City,Confirmpassword,Emailid,fname,House,Lname,Mobile,Password,Postcode,State;

    public Chef(String suburban, String city, String confirmpassword, String emailid, String fname, String house, String lname, String mobile, String password, String postcode, String state) {
        Suburban = suburban;
        City = city;
        Confirmpassword = confirmpassword;
        Emailid = emailid;
        this.fname = fname;
        House = house;
        Lname = lname;
        Mobile = mobile;
        Password = password;
        Postcode = postcode;
        State = state;
    }
    public Chef() {
    }

    public String getSuburban() {
        return Suburban;
    }

    public String getCity() {
        return City;
    }

    public String getConfirmpassword() {
        return Confirmpassword;
    }

    public String getEmailid() {
        return Emailid;
    }

    public String getFname() {
        return fname;
    }

    public String getHouse() {
        return House;
    }

    public String getLname() {
        return Lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getState() {
        return State;
    }
}
