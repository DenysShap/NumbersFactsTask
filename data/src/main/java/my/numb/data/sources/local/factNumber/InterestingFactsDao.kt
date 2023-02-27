package my.numb.data.sources.local.factNumber

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import my.numb.data.sources.local.factNumber.entity.FactAboutNumberEntity

@Dao
interface InterestingFactsDao {

    @Query("SELECT * FROM interesting_facts ORDER BY id")
    fun getAllFacts(): Flow<List<FactAboutNumberEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFact(fact: FactAboutNumberEntity)

    @Query("DELETE FROM interesting_facts")
    suspend fun deleteAllFacts()
}