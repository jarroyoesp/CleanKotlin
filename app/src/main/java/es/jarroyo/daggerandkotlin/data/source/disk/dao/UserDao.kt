package es.jarroyo.daggerandkotlin.data.source.disk.dao

import android.arch.persistence.room.*
import es.jarroyo.daggerandkotlin.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    @Query("SELECT * FROM user LIMIT 1")
    fun getFirstUser(): UserEntity
}