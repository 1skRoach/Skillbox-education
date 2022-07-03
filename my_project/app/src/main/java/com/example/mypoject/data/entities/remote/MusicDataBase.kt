package com.example.mypoject.data.entities.remote

import com.example.mypoject.data.entities.Audio
import com.example.mypoject.other.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDataBase {
    private val fireStore = FirebaseFirestore.getInstance()
    private val songCollection = fireStore.collection(SONG_COLLECTION)

    //запускаем корутину для получения всех аудио в качестве листа. Делаем это,
        //чтобы загрузка не была в основном потоке. поэтмоу  suspend
    suspend fun getAllAudio(): List<Audio> {
        return try {
            songCollection.get().await().toObjects(Audio::class.java)
        } catch (e: Exception) {
            return emptyList()
        }
    }
}