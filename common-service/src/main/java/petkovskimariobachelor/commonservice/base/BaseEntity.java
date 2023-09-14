package petkovskimariobachelor.commonservice.base;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.util.ProxyUtils;

import java.util.Objects;

@Getter
@MappedSuperclass
public abstract class BaseEntity<ID extends IdClass> {
    @EmbeddedId
    private ID id;
    protected BaseEntity(){

    }
    protected BaseEntity(@NonNull BaseEntity<ID> source) {
        Objects.requireNonNull(source, "source must not be null");
        this.id = source.id;
    }

    protected BaseEntity(@NonNull ID id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var other = (BaseEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }
}
