package org.workout.ws.user

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserDao : MongoRepository<User, String> {

    fun findByEmail(email: String): Optional<User>

    fun existsByEmail(email: String): Boolean
}
