package ru.ddsurok.datamodel;

/**
 *
 * @author d.duritskij
 */
import java.io.Serializable;
//import javax.persistence.*;

//@Entity(name="User")
//@Table(name = "m_user")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private int Id;
//    @Column(name = "nick", nullable = false, unique = true)
    private String NickName;
//    @Column(name = "hashpswd", nullable = false)
    private String HashPswd;
//    @Column(name = "family")
    private String Family;
//    @Column(name = "name")
    private String Name;
//    @Column(name = "email", nullable = false, unique = true)
    private String Email;
    
    public User () {
    }
    
    public User (int id, String nickName, String hashPswd, String family, String name, String email) {
        Id = id;
        NickName = nickName;
        HashPswd = hashPswd;
        Family = family;
        Name = name;
        Email = email;
    }

    public void setId(int value) {
        Id = value;
    }

    public int getId() {
        return Id;
    }

    public void setNickName(String value) {
        NickName = value;
    }

    public String getNickName() {
        return NickName;
    }

    public void setHashPswd(String value) {
        HashPswd = value;
    }

    public String getHashPswd() {
        return HashPswd;
    }

    public void setFamily(String value) {
        Family = value;
    }

    public String getFamily() {
        return Family;
    }

    public void setName(String value) {
        Name = value;
    }

    public String getName() {
        return Name;
    }

    public void setEmail(String value) {
        Email = value;
    }

    public String getEmail() {
        return Email;
    }
}
