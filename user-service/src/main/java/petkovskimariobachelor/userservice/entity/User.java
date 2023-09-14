package petkovskimariobachelor.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import petkovskimariobachelor.commonservice.base.BaseEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users_system")
public class User extends BaseEntity<UserId> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @CreatedDate
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
    @LastModifiedDate
    @Column(name = "date_modified")
    private LocalDateTime dateModified;
    @Column(name = "role")
    private Role role;

    public User(){
        super(UserId.randomId(UserId.class));
    }

    public static class Builder{
        User user = new User();

        public Builder firstName(String firstName){
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            user.lastName = lastName;
            return this;
        }

        public Builder email(String email){
            user.email = email;
            return this;
        }

        public Builder password(String password){
            user.password = password;
            return this;
        }

        public Builder isEnabled(Boolean isEnabled){
            user.isEnabled = isEnabled;
            return this;
        }

        public Builder role(Role role){
            user.role = role;
            return this;
        }

        public User build(){
            return user;
        }
    }
}