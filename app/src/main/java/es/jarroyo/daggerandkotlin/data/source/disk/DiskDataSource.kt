package es.jarroyo.daggerandkotlin.data.source.disk

import es.jarroyo.daggerandkotlin.data.entity.PainEntity
import es.jarroyo.daggerandkotlin.data.entity.UserEntity
import es.jarroyo.daggerandkotlin.ui.App


class DiskDataSource(appContext: App) {

    companion object {
        var database: Database? = null
    }

    init {
        database = Database.createInstance(appContext)
    }

    /***********************************************************************************************
     * USERS
     **********************************************************************************************/
    fun insertUser(user: UserEntity) = database?.userModel()?.insertUser(user)

    fun deleteAllUsers() = database?.userModel()?.deleteAllUsers()

    fun deleteUser(user: UserEntity) = database?.userModel()?.deleteUser(user)

    fun getUser() = database?.userModel()?.getFirstUser()


    /***********************************************************************************************
     * PAIN
     **********************************************************************************************/
    fun insertPain(pain: PainEntity) = database?.painModel()?.insertPain(pain)

    fun insertPainList(painList: List<PainEntity>) = database?.painModel()?.insertListPain(painList)

    fun getPainList(userId: String) = database?.painModel()?.getPainListUser(userId)

    fun deletePain(pain: PainEntity) = database?.painModel()?.deletePain(pain)

    fun deleteAllPain() = database?.painModel()?.deleteAllPain()

    /***********************************************************************************************
     * COMMON
     **********************************************************************************************/
    fun deleteAllTables() {
        database?.userModel()?.deleteAllUsers()
    }
}
