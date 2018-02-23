package es.jarroyo.daggerandkotlin.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by javierarroyo on 23/2/18.
 */
@Entity(tableName = "pain")
class PainEntity(var id: String = "",
                 @PrimaryKey var name: String = "",
                 var painLevel: Int = 0,
                 var userId: String? = null)