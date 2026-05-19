package org.workout.ws.api

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.workout.ws.api.model.ApiExerciseRequest
import org.workout.ws.api.model.CreateUserRequest
import org.workout.ws.api.model.CreateWorkoutPlanRequest
import org.workout.ws.api.model.CreateWorkoutSessionRequest
import org.workout.ws.api.model.ExerciseResponse
import org.workout.ws.api.model.UpdateUserRequest
import org.workout.ws.api.model.UpdateWorkoutPlanRequest
import org.workout.ws.api.model.UpdateWorkoutSessionRequest
import org.workout.ws.api.model.UserResponse
import org.workout.ws.api.model.WorkoutPlanResponse
import org.workout.ws.api.model.WorkoutSessionResponse
import org.workout.ws.api.model.toResponse
import org.workout.ws.exercises.ExerciseService
import org.workout.ws.plan.WorkoutPlanService
import org.workout.ws.session.WorkoutSessionService
import org.workout.ws.user.UserService

@RestController
@RequestMapping("/v1")
class RestGateway(
    private val exerciseService: ExerciseService,
    private val userService: UserService,
    private val workoutPlanService: WorkoutPlanService,
    private val workoutSessionService: WorkoutSessionService,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("exercise/{id}")
    fun getExerciseById(@PathVariable id: String): ExerciseResponse? {
        logger.info { "Received GET request for exercise with id: $id" }
        return exerciseService.getExerciseById(id)?.toResponse()
    }

    @GetMapping("exercise")
    fun getAllExercises(@RequestParam(required = false) limit: Int?): List<ExerciseResponse> {
        logger.info { "Received GET request for all exercises with limit: $limit" }
        return exerciseService.getAllExercises(limit).map { it.toResponse() }
    }

    @PostMapping("exercise")
    @ResponseStatus(HttpStatus.CREATED)
    fun createExercise(@Valid @RequestBody exerciseRequest: ApiExerciseRequest): ExerciseResponse {
        logger.info { "Received POST request to create a new exercise: ${exerciseRequest.name}" }
        return exerciseService.createExercise(exerciseRequest).toResponse()
    }

    @GetMapping("plan/{id}")
    fun getPlanById(@PathVariable id: String): WorkoutPlanResponse? {
        logger.info { "Received GET request for workout plan with id: $id" }
        return workoutPlanService.getWorkoutPlanById(id)?.toResponse()
    }

    @GetMapping("plan")
    fun getAllPlans(@RequestParam(required = false) limit: Int?): List<WorkoutPlanResponse> {
        logger.info { "Received GET request for all workout plans with limit: $limit" }
        return workoutPlanService.getAllWorkoutPlans(limit).map { it.toResponse() }
    }

    @PostMapping("plan")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlan(@Valid @RequestBody req: CreateWorkoutPlanRequest): WorkoutPlanResponse {
        logger.info { "Received POST request to create workout plan: ${req.name}" }
        return workoutPlanService.createWorkoutPlan(req).toResponse()
    }

    @PatchMapping("plan/{id}")
    fun updatePlan(@PathVariable id: String, @Valid @RequestBody req: UpdateWorkoutPlanRequest): WorkoutPlanResponse {
        logger.info { "Received PATCH request to update workout plan: $id" }
        return workoutPlanService.updateWorkoutPlan(id, req).toResponse()
    }

    @DeleteMapping("plan/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlan(@PathVariable id: String) {
        logger.info { "Received DELETE request for workout plan: $id" }
        workoutPlanService.deleteWorkoutPlan(id)
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody req: CreateUserRequest): UserResponse {
        logger.info { "Received POST request to create a new user with username: ${req.username}" }
        return userService.createUser(req).toResponse()
    }

    @GetMapping("user/{id}")
    fun getUserById(@PathVariable id: String): UserResponse {
        logger.info { "Received GET request for user with id: $id" }
        return userService.getUserById(id).toResponse()
    }

    @GetMapping("user")
    fun getAllUsers(@RequestParam(required = false) limit: Int?): List<UserResponse> {
        logger.info { "Received GET request for all users with limit: $limit" }
        return userService.listUsers(limit).map { it.toResponse() }
    }

    @PatchMapping("user/{id}")
    fun updateUser(@PathVariable id: String, @Valid @RequestBody req: UpdateUserRequest): UserResponse {
        logger.info { "Received PATCH request to update user with id: $id" }
        return userService.updateUser(id, req).toResponse()
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: String) {
        logger.info { "Received DELETE request for user with id: $id" }
        userService.deleteUser(id)
    }

    @PostMapping("session")
    @ResponseStatus(HttpStatus.CREATED)
    fun createWorkoutSession(@Valid @RequestBody req: CreateWorkoutSessionRequest): WorkoutSessionResponse {
        logger.info { "Received POST request to create a new workout session for user: ${req.userId}" }
        return workoutSessionService.createWorkoutSession(req).toResponse()
    }

    @GetMapping("session/{id}")
    fun getWorkoutSessionById(@PathVariable id: String): WorkoutSessionResponse? {
        logger.info { "Received GET request for workout session with id: $id" }
        return workoutSessionService.getWorkoutSessionById(id)?.toResponse()
    }

    @GetMapping("session")
    fun getAllWorkoutSessions(@RequestParam(required = false) limit: Int?): List<WorkoutSessionResponse> {
        logger.info { "Received GET request for all workout sessions with limit: $limit" }
        return workoutSessionService.getAllWorkoutSessions(limit).map { it.toResponse() }
    }

    @GetMapping("session/user/{userId}")
    fun getWorkoutSessionsByUserId(
        @PathVariable userId: String,
        @RequestParam(required = false) limit: Int?
    ): List<WorkoutSessionResponse> {
        logger.info { "Received GET request for workout sessions of user: $userId with limit: $limit" }
        return workoutSessionService.getWorkoutSessionsByUserId(userId, limit).map { it.toResponse() }
    }

    @GetMapping("session/plan/{workoutPlanId}")
    fun getWorkoutSessionsByPlanId(
        @PathVariable workoutPlanId: String,
        @RequestParam(required = false) limit: Int?
    ): List<WorkoutSessionResponse> {
        logger.info { "Received GET request for workout sessions of plan: $workoutPlanId with limit: $limit" }
        return workoutSessionService.getWorkoutSessionsByWorkoutPlanId(workoutPlanId, limit).map { it.toResponse() }
    }

    @PatchMapping("session/{id}")
    fun updateWorkoutSession(
        @PathVariable id: String,
        @Valid @RequestBody req: UpdateWorkoutSessionRequest
    ): WorkoutSessionResponse {
        logger.info { "Received PATCH request to update workout session: $id" }
        return workoutSessionService.updateWorkoutSession(id, req).toResponse()
    }

    @DeleteMapping("session/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteWorkoutSession(@PathVariable id: String) {
        logger.info { "Received DELETE request for workout session: $id" }
        workoutSessionService.deleteWorkoutSession(id)
    }
}