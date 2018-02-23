package es.jarroyo.daggerandkotlin.data.source.disk

import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.ui.App


class DiskDataSource(appContext: App) {

    companion object {
        var database: Database? = null
    }

    init {
        database = Database.createInstance(appContext)
    }

    fun insertUser(user: UserEntity) = database?.userModel()?.insertUser(user)

    fun deleteAllUsers() = database?.userModel()?.deleteAllUsers()

    fun deleteUser(user: UserEntity) = database?.userModel()?.deleteUser(user)

    fun getUser() = database?.userModel()?.getFirstUser()


    fun deleteAllTables() {
        database?.userModel()?.deleteAllUsers()
    }
}
