import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    /**
     * Sign in user with email and password.
     */
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        home: (Boolean) -> Unit
    ) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "SignIn Success: ${task.result}")
                        home(true)
                    } else {
                        Log.d("FB", "SignIn Failed: ${task.exception?.message}")
                        home(false)
                    }
                }
        } catch (ex: Exception) {
            Log.e("FB", "SignIn Exception: ${ex.message}")
            home(false)
        }
    }

    /**
     * Create new user with email and password.
     */
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        childName: String,
        caregiverName: String,
        caregiverPhone: String,
        userGroup: String,
        language: String,
        childAge: String,
        home: (Boolean) -> Unit
    ) {

        if (_loading.value == false) {
            _loading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        val uid = auth.currentUser?.uid ?: return@addOnCompleteListener

                        val userMap = hashMapOf(
                            "email" to email,
                            "childName" to childName,
                            "caregiverName" to caregiverName,
                            "caregiverPhone" to caregiverPhone,
                            "userGroup" to userGroup,
                            "language" to language,
                            "childAge" to childAge
                        )

                        firestore.collection("users")
                            .document(uid)
                            .set(userMap)
                            .addOnSuccessListener {
                                Log.d("FB", "User document created!")
                                home(true)
                            }
                            .addOnFailureListener {
                                Log.e("FB", "Firestore error: ${it.message}")
                                home(false)
                            }

                    } else {
                        Log.d("FB", "Registration Failed: ${task.exception?.message}")
                        home(false)
                    }

                    _loading.value = false
                }
        }
    }


    /**
     * Check if the user is already logged in.
     */
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}

/**
 * Data class for user model.
 */
data class MUser(
    val id: String?,
    val userId: String,
    val displayName: String,
    val avatarUrl: String,
    val quote: String,
    val profession: String
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "quote" to this.quote,
            "profession" to this.profession,
            "avatar_url" to this.avatarUrl
        )
    }
}
