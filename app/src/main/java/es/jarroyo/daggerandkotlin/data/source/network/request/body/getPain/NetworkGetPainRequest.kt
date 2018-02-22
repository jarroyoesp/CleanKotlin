package es.jarroyo.daggerandkotlin.data.source.network.request.body.getPain

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainRequest
import java.util.concurrent.ExecutionException


/**
 * Created by javierarroyo on 21/2/18.
 */
class NetworkGetPainRequest(private val getPainRequest: GetPainRequest,
                            networkClientManager: NetworkClientManager) {

    var mDatabase = FirebaseDatabase.getInstance().getReference()
    fun run(): NetworkResponse<NetworkGetPainResponse> {
        mDatabase = FirebaseDatabase.getInstance().getReference("BodyPartsPain/"+getPainRequest.userId)

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

        mDatabase.addValueEventListener(postListener)

        var networkBodyResponse = NetworkBodyResponse("Hola")
        var response: NetworkResponse<NetworkBodyResponse> = NetworkResponse(networkBodyResponse)
        return response*/

        val tcs = TaskCompletionSource<List<BodyPart>>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val menu: MutableList<BodyPart> = mutableListOf()

                tcs.setResult(dataSnapshot.children.mapNotNullTo(menu) { it.getValue<BodyPart>(BodyPart::class.java) })
            }

            override fun onCancelled(databaseError: DatabaseError) {
                tcs.setException(databaseError.toException())
            }
        }

        mDatabase.addListenerForSingleValueEvent(postListener)

        var t: Task<List<BodyPart>> = tcs.task

        try {
            Tasks.await(t)
        } catch (e: ExecutionException) {
            t = Tasks.forException(e)
        } catch (e: InterruptedException) {
            t = Tasks.forException(e)
        }

        var response: NetworkResponse<NetworkGetPainResponse> = NetworkResponse()
        if (t.isSuccessful()) {
            val result = t.getResult()
            var networkBodyResponse = NetworkGetPainResponse(result)
            response = NetworkResponse(networkBodyResponse)
        }
        return response

    }

}