package sample;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty bucket;
    private SimpleStringProperty number;

    public Person(String bucket,String number){
        this.bucket=new SimpleStringProperty(bucket);
        this.number=new SimpleStringProperty(number);
    }
    public void setBucket(String order){
        this.bucket.set(order);
    }
    public void setNumber(String step){
        this.number.set(step);
    }
    public String getBucket(){
        return bucket.get();
    }
    public String getNumber(){
        return number.get();
    }

}