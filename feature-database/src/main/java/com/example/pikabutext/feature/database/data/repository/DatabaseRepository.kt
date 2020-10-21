package com.example.pikabutext.feature.database.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pikabutext.feature.database.data.model.FavoriteDto
import com.example.pikabutext.feature.database.data.model.FavoriteEntryDto
import com.example.pikabutext.feature.database.data.model.FavoriteImageDto

interface DatabaseRepository {
    val favorites: LiveData<List<FavoriteDto>>
    suspend fun getFavorites(): List<FavoriteDto>
    suspend fun insert(favoriteDto: FavoriteDto)
    suspend fun delete(id: Int)
}

class DatabaseRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : DatabaseRepository {
    override val favorites: LiveData<List<FavoriteDto>> = favoriteDao.getFavoritesLiveData()

    @WorkerThread
    override suspend fun getFavorites(): List<FavoriteDto> = favoriteDao.getFavorites()

    @WorkerThread
    override suspend fun insert(favoriteDto: FavoriteDto) {
        favoriteDao.insertFavorite(favoriteDto)
    }

    @WorkerThread
    override suspend fun delete(id: Int) {
        favoriteDao.deleteFavorite(id)
    }
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteEntryDto")
    fun getFavoritesLiveData(): LiveData<List<FavoriteDto>>

    @Query("SELECT * FROM FavoriteEntryDto")
    suspend fun getFavorites(): List<FavoriteDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(favoriteEntry: FavoriteEntryDto): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<FavoriteImageDto>): List<Long>

    @Transaction
    suspend fun insertFavorite(favoriteDto: FavoriteDto) {
        with(favoriteDto) {
            insertEntry(entry)
            images?.let { images ->
                insertImages(images.map { FavoriteImageDto(it, entry.id) })
            }
        }
    }

    @Query("DELETE FROM FavoriteEntryDto WHERE id = :id")
    suspend fun deleteEntry(id: Int)

    @Query("DELETE FROM FavoriteImageDto WHERE entryId = :entryId")
    suspend fun deleteImage(entryId: Int)

    @Transaction
    suspend fun deleteFavorite(id: Int) {
        deleteEntry(id)
        deleteImage(id)
    }
}

@Database(entities = [FavoriteEntryDto::class, FavoriteImageDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}