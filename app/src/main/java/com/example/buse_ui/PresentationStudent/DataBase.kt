package com.example.buse_ui.PresentationStudent

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface inAppNotifDao {
    @Query("SELECT * FROM inAppNotif ORDER BY id DESC")
    fun getAll(): Flow<List<InAppNotifContent>>

    @Upsert
    suspend fun upsert(notifContent: InAppNotifContent)

    @Delete
    suspend fun delete(notification: InAppNotifContent)
}
@Database(entities = [InAppNotifContent::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): inAppNotifDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notification_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
class inAppNotifRepository(private val dao: inAppNotifDao) {
    val allNotifications: Flow<List<InAppNotifContent>> = dao.getAll()

    suspend fun addNotification(notification: InAppNotifContent) {
        dao.upsert(notification)
    }

    suspend fun removeNotification(notification: InAppNotifContent) {
        dao.delete(notification)
    }
}