package ethicalengine;

public abstract class Persona {

    private int age=18;
    private Gender gender="MALE";
    private Bodytype bodytype="AVERAGE";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Bodytype getBodytype() {
        return bodytype;
    }

    public void setBodytype(Bodytype bodytype) {
        this.bodytype = bodytype;
    }



    // empty constructor
    //give default values
    public Persona(){
        // age is the invariant which always age>=0
    }

    public Persona(int age, Gender gender, Bodytype bodytype){
        // age is the invariant which always age>=0
        this.age=age;
        this.gender=gender;
        this.bodytype=bodytype;
    }

    //copy constructor
    public Persona(Persona otherPersona){
        this.age= otherPersona.age;
        this.gender= otherPersona.gender;
        this.bodytype= otherPersona.bodytype;
    }




}



