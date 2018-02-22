package es.jarroyo.daggerandkotlin.data.source.network.request.login

import android.text.TextUtils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import es.jarroyo.daggerandkotlin.data.source.network.manager.NetworkClientManager
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkError
import es.jarroyo.daggerandkotlin.data.source.network.model.NetworkResponse
import es.jarroyo.daggerandkotlin.data.source.network.request.base.auth.NetworkUserAuthenticationResponse
import es.jarroyo.daggerandkotlin.domain.usecase.login.LoginRequest


/**
 * Created by javierarroyo on 22/1/18.
 */
class NetworkLoginRequest(private val loginRequest: LoginRequest,
                          networkClientManager: NetworkClientManager) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun run(): NetworkResponse<NetworkUserAuthenticationResponse> {

        // LOGIN NORMAL
        if (!TextUtils.isEmpty(loginRequest.email) && !TextUtils.isEmpty(loginRequest.password)) {
            var response: Task<AuthResult> = auth.signInWithEmailAndPassword(loginRequest.email,
                    loginRequest.password)

            if (response.isSuccessful) {

                var networkUserAuthenticationResponse = NetworkUserAuthenticationResponse(auth.uid!!)
                var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(networkUserAuthenticationResponse)

                return networkResponse
            } else {
                // Devolvemos error
                val networkError: NetworkError = NetworkError()
                networkError.error = response.exception.toString()
                var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(null, networkError)

                return networkResponse
            }
        }

        // Usuario anonimo ya logueado
        else if (TextUtils.isEmpty(loginRequest.email) && TextUtils.isEmpty(loginRequest.password) && auth.currentUser != null) {
            var networkUserAuthenticationResponse = NetworkUserAuthenticationResponse(auth.uid!!)
            var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(networkUserAuthenticationResponse)

            return networkResponse
        }

        // Tratamos de hacer login con las credenciales
        else {
            var response: Task<AuthResult> = auth.signInWithEmailAndPassword(loginRequest.email,
                    loginRequest.password)

            if (response.isSuccessful) {

                var networkUserAuthenticationResponse = NetworkUserAuthenticationResponse(auth.uid!!)
                var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(networkUserAuthenticationResponse)

                return networkResponse
            }

            // Creamos un usuario anonimo
            else {

                var responseAnonim: Task<AuthResult> = auth.signInAnonymously()
                if (responseAnonim.isSuccessful) {
                    var networkUserAuthenticationResponse = NetworkUserAuthenticationResponse(auth.uid!!)
                    var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(networkUserAuthenticationResponse)

                    return networkResponse
                } else {
                    // Devolvemos error
                    val networkError: NetworkError = NetworkError()
                    networkError.error = responseAnonim.exception.toString()
                    var networkResponse = NetworkResponse<NetworkUserAuthenticationResponse>(null, networkError)

                    return networkResponse
                }
            }
        }
    }
}