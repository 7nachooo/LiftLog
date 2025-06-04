package com.ignacio.liftlog.models

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.ignacio.liftlog.models.Usuario

class PerfilViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _usuario = MutableStateFlow(Usuario())
    val usuario: StateFlow<Usuario> = _usuario.asStateFlow()

    init {
        cargarUsuario()
    }

    fun cargarUsuario() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("usuarios").document(uid)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null && snapshot.exists()) {
                    _usuario.value = snapshot.toObject(Usuario::class.java) ?: Usuario()
                }
            }
    }

    fun actualizarUsuario(nombre: String, email: String, peso: Int, altura: Int) {
        val uid = auth.currentUser?.uid ?: return
        db.collection("usuarios").document(uid).update(
            mapOf(
                "nombre" to nombre,
                "email" to email,
                "peso" to peso,
                "altura" to altura
            )
        )
    }
}
