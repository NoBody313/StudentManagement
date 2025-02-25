package com.anomali.studentmanagement.data.mapper

import com.anomali.studentmanagement.data.model.Classes
import com.anomali.studentmanagement.data.model.User
import com.anomali.studentmanagement.data.model.student.Father
import com.anomali.studentmanagement.data.model.student.Mother
import com.anomali.studentmanagement.data.model.student.Student
import com.anomali.studentmanagement.data.remote.dto.response.StudentResponseDTO

fun StudentResponseDTO.toModel(): Student {
    return Student(
        id = id,
        nis = nis,
        nisn = nisn,
        dateOfBirth = dateOfBirth,
        placeOfBirth = placeOfBirth,
        classId = classes?.toModel() ?: Classes(0, "Unknown", "", ""),
        gender = gender,
        user = User(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        ),
        father = father.let {
            Father(
                id = it.id,
                name = it.name,
                phoneNumber = it.phoneNumber,
                bornPlace = it.bornPlace,
                bornDate = it.bornDate,
                occupation = it.occupation,
                address = it.address,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        },
        mother = mother.let {
            Mother(
                id = it.id,
                name = it.name,
                phoneNumber = it.phoneNumber,
                bornPlace = it.bornPlace,
                bornDate = it.bornDate,
                occupation = it.occupation,
                address = it.address,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        },

        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}


//fun StudentEntity.toModel(): Student {
//    return Student(
//        id = id,
//        userId = userId,
//        nis = nis,
//        nisn = nisn,
//        dateOfBirth = dateOfBirth,
//        placeOfBirth = placeOfBirth,
//        gender = gender,
//        father = Father(
//            id = 0,
//            name = "",
//            phoneNumber = "",
//            bornPlace = "",
//            bornDate = "",
//            occupation = "",
//            address = "",
//            createdAt = "",
//            updatedAt = ""
//        ),
//        mother = Mother(
//            id = 0,
//            name = "",
//            phoneNumber = "",
//            bornPlace = "",
//            bornDate = "",
//            occupation = "",
//            address = "",
//            createdAt = "",
//            updatedAt = ""
//        ),
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}

//fun StudentResponseDTO.toEntity(): StudentEntity {
//    return StudentEntity(
//        id = id,
//        nis = nis,
//        nisn = nisn,
//        email = email,
//        phoneNumber = phone,
//        classLevel = classLevel,
//        homeroomTeacher = homeroomTeacher,
//        dateOfBirth = dateOfBirth,
//        placeOfBirth = placeOfBirth,
//        gender = gender,
//        createdAt = createdAt,
//        updatedAt = updatedAt,
//        isFavorite = false
//    )
//}

//fun StudentResponseDTO.toFatherEntity(studentId: Int): FatherEntity {
//    return FatherEntity(
//        id = father.id,
//        studentId = studentId,
//        name = father.name,
//        phoneNumber = father.phoneNumber,
//        bornPlace = father.bornPlace,
//        bornDate = father.bornDate,
//        occupation = father.occupation,
//        address = father.address
//    )
//}
//
//fun StudentResponseDTO.toMotherEntity(studentId: Int): MotherEntity {
//    return MotherEntity(
//        id = mother.id,
//        studentId = studentId,
//        name = mother.name,
//        phoneNumber = mother.phoneNumber,
//        bornPlace = mother.bornPlace,
//        bornDate = mother.bornDate,
//        occupation = mother.occupation,
//        address = mother.address
//    )
//}