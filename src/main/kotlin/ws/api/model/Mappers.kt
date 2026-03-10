package org.workout.ws.api.model

import org.workout.ws.exercises.Exercise
import org.workout.ws.plan.WorkoutPlan
import org.workout.ws.plan.WorkoutPlanItem
import org.workout.ws.user.User

fun Exercise.toResponse(): ExerciseResponse =
    ExerciseResponse(
        id = requireNotNull(this.id) { "Exercise id is null (not saved?)" },
        name = this.name,
        description = this.description,
        primaryMuscleGroup = this.primaryMuscleGroup,
        secondaryMuscleGroups = this.secondaryMuscleGroups,
        equipment = this.equipment,
        createdByUserId = this.createdByUserId,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )

fun User.toResponse(): UserResponse =
    UserResponse(
        id = requireNotNull(this.id) { "User id is null (not saved?)" },
        userNumber = requireNotNull(this.userNumber) { "User number is null (not saved?)" },
        email = this.email,
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        birthDate = this.birthDate,
        units = this.units,
        heightCm = this.heightCm,
        weightKg = this.weightKg,
        weeklyWorkoutGoal = this.weeklyWorkoutGoal,
        experienceLevel = this.experienceLevel,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )

fun WorkoutPlan.toResponse(): WorkoutPlanResponse =
    WorkoutPlanResponse(
        id = requireNotNull(this.id) { "Workout plan id is null (not saved?)" },
        name = this.name,
        description = this.description,
        workoutPlanItems = this.workoutPlanItems.map { it.toResponse() },
        createdByUserId = this.createdByUserId,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )

fun WorkoutPlanItem.toResponse(): WorkoutPlanItemResponse =
    WorkoutPlanItemResponse(
        exerciseId = this.exerciseId,
        sets = this.sets,
        reps = this.reps,
        restTimeSeconds = this.restTimeSeconds,
        notes = this.notes,
    )