package com.ord.orderscenter.data.localdatabase



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ord.orderscenter.model.GeneralOrdersEntity
import com.ord.orderscenter.model.IndividualItemEntity


@Database(entities = [GeneralOrdersEntity::class, IndividualItemEntity::class], version = 1, exportSchema = false)
abstract class OrderDatabase : RoomDatabase() {

    abstract fun generalDao(): GeneralDao
    abstract fun individualDao(): IndividualDao


    companion object {
        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getDatabase(context: Context): OrderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    "orders_data"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}