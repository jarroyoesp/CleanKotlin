package es.jarroyo.daggerandkotlin.data.source.disk

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import es.jarroyo.daggerandkotlin.data.entity.PainEntity
import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.data.source.disk.dao.PainDao
import es.jarroyo.daggerandkotlin.data.source.disk.dao.UserDao
import es.jarroyo.daggerandkotlin.ui.App

@Database(entities = arrayOf(
        UserEntity::class,
        PainEntity::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun userModel(): UserDao
    abstract fun painModel(): PainDao

    companion object {
        private val DATABASE_NAME: String = "app_db"

        fun createInstance(appContext: App):
                es.jarroyo.daggerandkotlin.data.source.disk.Database
                = Room.databaseBuilder(appContext,
                es.jarroyo.daggerandkotlin.data.source.disk.Database::class.java, DATABASE_NAME)
                .build()
    }
}
