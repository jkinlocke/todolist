package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Tasks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "to_Do_item")
    private String to_Do_Item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToDoItem() {
        return to_Do_Item;
    }

    public void setToDoItem(String to_Do_Item) {
        this.to_Do_Item = to_Do_Item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tasks tasks = (Tasks) o;

        if (id != tasks.id) return false;
        if (!Objects.equals(to_Do_Item, tasks.to_Do_Item)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (to_Do_Item != null ? to_Do_Item.hashCode() : 0);
        return result;
    }
}
