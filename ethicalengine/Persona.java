package ethicalengine;

public abstract class Persona {

    private int age=18;
    private Gender gender;
    private BodyType bodyType;

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

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodytype) {
        this.bodyType = bodytype;
    }


    // empty constructor
    //give default values
    public Persona(){
        // age is the invariant which always age>=0
    }

    public Persona(int age, Gender gender, BodyType bodytype){
        // age is the invariant which always age>=0
        this.age=age;
        this.gender=gender;
        this.bodyType=bodytype;
    }

    //copy constructor
    public Persona(Persona otherPersona){
        this.age= otherPersona.age;
        this.gender= otherPersona.gender;
        this.bodyType= otherPersona.bodyType;
    }

    public enum  AgeCategory {
        BABY, CHILD, ADULT, SENIOR
    }
    
    
    public enum Profession {
        DOCTOR,CEO,CRIMINAL,HOMELESS,UNEMPLOYED,NONE, TEACHER, CLEANER
    }

    public enum Gender {
        MALE,FEMALE,UNKNOWN;
    }

    public enum BodyType{
        AVERAGE,ATHLETIC,OVERWEIGHT,UNSPECIFIED
    }

    public abstract String toString();
    }




