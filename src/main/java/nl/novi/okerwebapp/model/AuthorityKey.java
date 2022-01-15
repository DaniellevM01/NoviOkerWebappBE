package nl.novi.okerwebapp.model;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityKey implements Serializable {
    private Integer user_id;
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityKey that = (AuthorityKey) o;
        return user_id.equals(that.user_id) &&
                authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, authority);
    }

}
