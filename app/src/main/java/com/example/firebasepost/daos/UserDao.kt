package com.example.firebasepost.daos

import com.google.firebase.firestore.FirebaseFirestore
import com.example.firebasepost.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")
    fun addUser(user: User?) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {

                userCollection.document(user.uid).set(it)
            }
        }
    }
    fun getUserById(uId: String): Task<DocumentSnapshot> {
        return userCollection.document(uId).get()

    }
}
