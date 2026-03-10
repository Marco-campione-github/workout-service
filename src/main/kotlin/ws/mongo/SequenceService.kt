package org.workout.ws.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class SequenceService(
    private val mongoTemplate: MongoTemplate,
) {
    fun nextUserNumber(): Long = nextSequence("userNumber")

    private fun nextSequence(sequenceName: String): Long {
        val query = Query(Criteria.where("_id").`is`(sequenceName))
        val update = Update().inc("seq", 1)
        val options = FindAndModifyOptions.options().returnNew(true).upsert(true)
        val counter = mongoTemplate.findAndModify(query, update, options, SequenceCounter::class.java)
        return counter?.seq ?: 1L
    }
}

data class SequenceCounter(
    @Id
    val id: String? = null,
    val seq: Long = 0,
)
