package com.aradevs.guia4_1.db

import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert
    suspend fun savePerson(person: PersonEntity)

    @Update
    suspend fun updatePerson(person: PersonEntity)

    @Query("select * from person where id=:id")
    suspend fun getPerson(id: Int) : PersonEntity?

    @Delete
    suspend fun deletePerson(person: PersonEntity)
}