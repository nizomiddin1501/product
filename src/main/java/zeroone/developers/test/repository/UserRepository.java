package zeroone.developers.test.repository;

import zeroone.developers.test.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User,String>{
    Optional<User> findByPhoneNumberAndVisibleTrue(String phoneNumber);
}
