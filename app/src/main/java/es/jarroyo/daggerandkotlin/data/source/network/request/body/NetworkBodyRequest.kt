package es.jarroyo.daggerandkotlin.data.source.network.request.body

import com.google.firebase.database.FirebaseDatabase
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.domain.usecase.body.SavePainRequest

/**
 * Created by javierarroyo on 21/2/18.
 */
class NetworkBodyRequest(private val savePainRequest: SavePainRequest,
                         networkClientManager: NetworkClientManager) {

    val mDatabase = FirebaseDatabase.getInstance().getReference("BodyParts")
    fun run(): NetworkResponse<NetworkBodyResponse> {

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        val bodyPartId = mDatabase.push().key

        mDatabase.child(bodyPartId).setValue(savePainRequest.bodypart)

        var networkBodyResponse = NetworkBodyResponse("Hola")
        var response: NetworkResponse<NetworkBodyResponse> = NetworkResponse(networkBodyResponse)
        return response
    }

}