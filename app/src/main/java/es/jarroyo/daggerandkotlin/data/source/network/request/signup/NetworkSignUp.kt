package es.jarroyo.daggerandkotlin.data.source.network.request.signup

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest


class NetworkSignUp(private val loginRequest: LoginRequest,
                            networkClientManager: NetworkClientManager)  {

    private val auth: FirebaseAuth? = FirebaseAuth.getInstance()


    fun run(){//: NetworkResponse<NetworkSignUpResponse> {

        auth?.createUserWithEmailAndPassword(loginRequest.email,
                loginRequest.password)?.addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
            } else {

            }
        })
    }
}