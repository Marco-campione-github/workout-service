package org.workout.ws.api.model

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.workout.ws.plan.WorkoutPlanNotFoundException
import org.workout.ws.user.UserAlreadyExistsException
import org.workout.ws.user.UserNotFoundException

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class, WorkoutPlanNotFoundException::class)
    fun handleNotFound(ex: RuntimeException): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            mapOf("error" to "not_found", "message" to ex.message)
        )

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleConflict(ex: UserAlreadyExistsException): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(
            mapOf("error" to "conflict", "message" to ex.message)
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any?>> {
        val errors = ex.bindingResult.allErrors.mapNotNull { err ->
            val fe = err as? FieldError
            fe?.field?.let { field ->
                mapOf(
                    "field" to field,
                    "message" to (fe.defaultMessage ?: "Invalid value")
                )
            }
        }
        return ResponseEntity.badRequest().body(
            mapOf("error" to "validation_error", "errors" to errors)
        )
    }
}