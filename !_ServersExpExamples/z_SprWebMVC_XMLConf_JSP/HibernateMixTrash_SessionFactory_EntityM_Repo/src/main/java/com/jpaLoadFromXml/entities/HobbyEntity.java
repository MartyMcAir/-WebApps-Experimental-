package com.jpaLoadFromXml.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 04.10.2015.
 */
@Entity
@Table(name = "hobby", schema = "", catalog = "javastudy")
public class HobbyEntity {
    private String hobbyId;

    @Id
    @Column(name = "hobby_id", nullable = false, insertable = true, updatable = true, length = 20)
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    private Set<ContactEntity> contacts = new HashSet<ContactEntity>();

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
            //foreign key for HobbyEntity in contact_hobby_detail table
            joinColumns = @JoinColumn(name = "hobby_id"),
            //key for other side - contact table
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    public Set<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HobbyEntity that = (HobbyEntity) o;

        if (hobbyId != null ? !hobbyId.equals(that.hobbyId) : that.hobbyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hobbyId != null ? hobbyId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HobbyEntity{" +
                "hobbyId='" + hobbyId + '\'' +
                '}';
    }
}
