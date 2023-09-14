package petkovskimariobachelor.userservice.entity;

import lombok.NonNull;
import petkovskimariobachelor.commonservice.base.IdClass;

public class UserId extends IdClass {
    private UserId(){
        super(UserId.randomId(UserId.class).getId());
    }

    public UserId(@NonNull String uuid){
        super(uuid);
    }

    public static UserId of(String uuid){
        return new UserId(uuid);
    }

}