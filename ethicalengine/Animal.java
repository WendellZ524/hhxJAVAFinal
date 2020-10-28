package ethicalengine;

public class Animal extends Persona{
    public Animal(int age, Gender gender, Bodytype bodytype){
        super(age,gender,bodytype);
    }
    public Animal(){
        super();
    }

    public Animal(Animal otherAnimal){
        this.setAge(otherAnimal.getAge());
        this.setBodytype(otherAnimal.getBodytype());
        this.setGender(otherAnimal.getGender());
    }

}
