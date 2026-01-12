package users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Creds {
    private String email;
    private String password;

    private static final Faker faker = new Faker();

    public static Creds getCreds(Users one) {
        return new Creds(one.getEmail(), one.getPassword());
    }

}
