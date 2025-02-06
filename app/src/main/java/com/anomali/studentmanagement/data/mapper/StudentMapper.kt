package com.anomali.studentmanagement.data.mapper

import com.anomali.studentmanagement.data.local.entity.FatherEntity
import com.anomali.studentmanagement.data.local.entity.MotherEntity
import com.anomali.studentmanagement.data.local.entity.StudentEntity
import com.anomali.studentmanagement.data.model.Father
import com.anomali.studentmanagement.data.model.Mother
import com.anomali.studentmanagement.data.model.Student
import com.anomali.studentmanagement.data.remote.dto.response.Father as FatherDto
import com.anomali.studentmanagement.data.remote.dto.response.Mother as MotherDto
import com.anomali.studentmanagement.data.remote.dto.response.StudentResponseDTO

fun StudentResponseDTO.toModel(): Student {
    return Student(
        id = id,
        name = name,
        nis = nis,
        nisn = nisn,
        email = email,
        phoneNumber = phone,
        classLevel = classLevel,
        homeroomTeacher = homeroomTeacher,
        dateOfBirth = dateOfBirth,
        placeOfBirth = placeOfBirth,
        gender = gender,
        father = father.toModel(),
        mother = mother.toModel(),
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun StudentResponseDTO.toEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        name = name,
        nis = nis,
        nisn = nisn,
        email = email,
        phoneNumber = phone,
        classLevel = classLevel,
        homeroomTeacher = homeroomTeacher,
        dateOfBirth = dateOfBirth,
        placeOfBirth = placeOfBirth,
        gender = gender,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isFavorite = false
    )
}

fun StudentResponseDTO.toFatherEntity(studentId: Int): FatherEntity {
    return FatherEntity(
        id = father.id,
        studentId = studentId,
        name = father.name,
        phoneNumber = father.phoneNumber,
        bornPlace = father.bornPlace,
        bornDate = father.bornDate,
        occupation = father.occupation,
        address = father.address
    )
}

fun StudentResponseDTO.toMotherEntity(studentId: Int): MotherEntity {
    return MotherEntity(
        id = mother.id,
        studentId = studentId,
        name = mother.name,
        phoneNumber = mother.phoneNumber,
        bornPlace = mother.bornPlace,
        bornDate = mother.bornDate,
        occupation = mother.occupation,
        address = mother.address
    )
}

fun FatherDto.toModel(): Father {
    return Father(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        bornPlace = bornPlace,
        bornDate = bornDate,
        occupation = occupation,
        address = address,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun MotherDto.toModel(): Mother {
    return Mother(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        bornPlace = bornPlace,
        bornDate = bornDate,
        occupation = occupation,
        address = address,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun StudentEntity.toModel(): Student {
    return Student(
        id = id,
        name = name,
        nis = nis,
        nisn = nisn,
        email = email,
        phoneNumber = phoneNumber,
        classLevel = classLevel,
        homeroomTeacher = homeroomTeacher,
        dateOfBirth = dateOfBirth,
        placeOfBirth = placeOfBirth,
        gender = gender,
        father = Father(
            id = 0,
            name = "",
            phoneNumber = "",
            bornPlace = "",
            bornDate = "",
            occupation = "",
            address = "",
            createdAt = "",
            updatedAt = ""
        ),
        mother = Mother(
            id = 0,
            name = "",
            phoneNumber = "",
            bornPlace = "",
            bornDate = "",
            occupation = "",
            address = "",
            createdAt = "",
            updatedAt = ""
        ),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}