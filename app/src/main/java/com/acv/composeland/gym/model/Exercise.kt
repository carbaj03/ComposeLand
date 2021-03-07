package com.acv.composeland.gym.model

data class MuscleGroup(
    val name: String,
    val bodyParts: List<BodyPart>,
)

data class BodyPart(
    val name: String
)

data class Exercise(
    val name: String,
)

val exercises = listOf(
    Exercise("Bench Press"),
    Exercise("Row"),
    Exercise("Squat"),
)