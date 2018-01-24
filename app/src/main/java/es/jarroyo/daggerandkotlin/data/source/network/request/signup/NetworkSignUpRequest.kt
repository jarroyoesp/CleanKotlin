package es.jarroyo.daggerandkotlin.data.source.network.request.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkError
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.domain.usecase.signUp.SignUpRequest


class NetworkSignUpRequest(private val signUpRequest: SignUpRequest,
                           networkClientManager: NetworkClientManager)  {

    private val auth: FirebaseAuth? = FirebaseAuth.getInstance()


    fun run(): NetworkResponse<NetworkSignUpResponse> {

        var response: Task<AuthResult>? = auth?.createUserWithEmailAndPassword(signUpRequest.email,
                signUpRequest.password)
        if (response!!.isSuccessful) {

            var networkData: NetworkData = NetworkData("User")
            var networkSignUpResponse : NetworkSignUpResponse = NetworkSignUpResponse(networkData)
            var networkResponse : NetworkResponse<NetworkSignUpResponse> = NetworkResponse<NetworkSignUpResponse>(networkSignUpResponse)

            return networkResponse
        } else {
            val networkError: NetworkError = NetworkError()
            val networkResponse : NetworkResponse<NetworkSignUpResponse> = NetworkResponse<NetworkSignUpResponse>( null, networkError)

            return networkResponse

        }
    }
}