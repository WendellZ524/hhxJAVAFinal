package ethicalengine;

public class Animal extends Persona{
    public Animal(int age, Gender gender, BodyType bodytype){
        super(age,gender,bodytype);
    }
    public Animal(){
        super();
    }

    public Animal(Animal otherAnimal){
        this.setAge(otherAnimal.getAge());
        this.setBodyType(otherAnimal.getBodyType());
        this.setGender(otherAnimal.getGender());
    }

}
