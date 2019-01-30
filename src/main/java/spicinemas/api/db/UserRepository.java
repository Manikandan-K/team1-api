package spicinemas.api.db;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spicinemas.api.model.Users;


@Repository
@Transactional
public class UserRepository {

    @Autowired
    private DSLContext dsl;


    public Users createUser(Users user) {
        dsl.insertInto(DSL.table("USERS"), DSL.field("NAME"), DSL.field("EMAIL"), DSL.field("ENCODEDPASSWORD"))
                .values(user.getName(), user.getEmail(), user.getEncodedPassword()).execute();
        return dsl.select(DSL.field("NAME"), DSL.field("EMAIL"), DSL.field("ENCODEDPASSWORD"))
                .from(DSL.table("USERS"))
                .where(DSL.field("USERS.EMAIL").eq(user.getEmail()))
                .fetchAny()
                .into(Users.class);
    }




}