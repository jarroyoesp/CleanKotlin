package es.jarroyo.daggerandkotlin.data.source.network.request.body

import com.google.firebase.database.FirebaseDatabase
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest


/**
 * Created by javierarroyo on 21/2/18.
 */
class NetworkBodyRequest(private val savePainRequest: SavePainRequest,
                         networkClientManager: NetworkClientManager) {

    var mDatabase = FirebaseDatabase.getInstance().getReference()
    fun run(): NetworkResponse<NetworkBodyResponse> {
        mDatabase = FirebaseDatabase.getInstance().getReference("BodyPartsPain/"+savePainRequest.bodypart.userId)

        mDatabase.child(savePainRequest.bodypart.name).setValue(savePainRequest.bodypart)


        /*val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val menu: MutableList<BodyPart> = mutableListOf()
                dataSnapshot.children.mapNotNullTo(menu) { it.getValue<BodyPart>(BodyPart::class.java) }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)*/

        var networkBodyResponse = NetworkBodyResponse("Hola")
        var response: NetworkResponse<NetworkBodyResponse> = NetworkResponse(networkBodyResponse)
        return response
    }

}