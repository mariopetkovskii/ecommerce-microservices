package petkovskimariobachelor.commonservice.base;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
@MappedSuperclass
public abstract class IdClass implements Serializable {
    private String id;
    protected IdClass(@NonNull String uuid){
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    @NonNull
    public static <ID extends IdClass> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }
}
