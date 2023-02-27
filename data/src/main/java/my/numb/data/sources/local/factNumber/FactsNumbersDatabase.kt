package my.numb.data.sources.local.factNumber

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.numb.data.sources.local.factNumber.entity.FactAboutNumberEntity

@Database(
    version = 1,
    entities = [
        FactAboutNumberEntity::class
    ]
)
abstract class FactsNumbersDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "facts"

        fun getDatabase(context: Context) =
            Room.databaseBuilder(context, FactsNumbersDatabase::class.java, DATABASE_NAME).build()
    }

    abstract fun getInterestingFactDao(): InterestingFactsDao
}