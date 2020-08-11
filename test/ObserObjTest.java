import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;

import java.util.Objects;

public class ObserObjTest {
    @Test
    public void testEqual() {
        OBJ1 obj1 = new OBJ1();
        obj1.setName("张三");
        System.out.println(obj1.getName());


        OBJ1 obj2 = new OBJ1();
        obj2.setName("张三");

        System.out.println(obj1.equals(obj2));
    }
}

class OBJ1 extends RecursiveTreeObject<OBJ1> implements Comparable<OBJ1> {
    private StringProperty name = new SimpleStringProperty();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OBJ1 obj1 = (OBJ1) o;
        return Objects.equals(name.getValue(), obj1.name.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public OBJ1(StringProperty name) {
        this.name = name;
    }

    public OBJ1() {
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public int compareTo(OBJ1 o) {
        return o.getName().compareTo(getName());
    }
}
