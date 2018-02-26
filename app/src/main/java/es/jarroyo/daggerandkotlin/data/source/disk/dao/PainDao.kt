package es.jarroyo.daggerandkotlin.data.source.disk.dao

import android.arch.persistence.room.*
import es.jarroyo.daggerandkotlin.data.entity.PainEntity

@Dao
interface PainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPain(pain: PainEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListPain(painList: List<PainEntity>)

    @Delete
    fun deletePain(pain: PainEntity)

    @Query("DELETE FROM pain")
    fun deleteAllPain()

    @Query("SELECT * FROM pain WHERE userId == :userId")
    fun getPainListUser(userId: String):  List<PainEntity>

}