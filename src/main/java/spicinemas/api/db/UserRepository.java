package spicinemas.api.db;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spicinemas.api.model.User;


@Repository
@Transactional
public class UserRepository {

    @Autowired
    private DSLContext dsl;


    //TODO: Breaking command query pattern. Consider fix
    public User createUser(User user) {
        dsl.insertInto(DSL.table("USERS"), DSL.field("NAME"), DSL.field("EMAIL"), DSL.field("ENCODEDPASSWORD"))
                .values(user.getName(), user.getEmail(), user.getEncodedPassword()).execute();
        return dsl.select(DSL.field("NAME"), DSL.field("EMAIL"), DSL.field("ENCODEDPASSWORD"))
                .from(DSL.table("USERS"))
                .where(DSL.field("USERS.EMAIL").eq(user.getEmail()))
                .fetchAny()
                .into(User.class);
    }

    //TODO:Handle no data scenario well
    public User getUserByEmail(String email) {
        return dsl.select(DSL.field("NAME"), DSL.field("EMAIL"), DSL.field("ENCODEDPASSWORD"))
                .from(DSL.table("USERS"))
                .where(DSL.field("USERS.EMAIL").eq(email))
                .fetchAny()
                .into(User.class);
    }
}